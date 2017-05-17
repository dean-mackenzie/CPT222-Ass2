package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Player;

// Does not extend AbstractOperation as I couldn't find a way to implement a 
// method that throws exception without having to apply to other classes too
public class PlaceBetOperation extends AbstractGameOperation implements Serializable {
	
	private Player player;
	private int betPoints;
	

	public PlaceBetOperation(Player player, int betPoints) {
		this.player = player;
		this.betPoints = betPoints;
	}

	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		try {
			serverStub.getEngine().placeBet(player, this.betPoints);
			try {
				requestStream.writeObject(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InsufficientFundsException e) {
			try {
				requestStream.writeObject(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
