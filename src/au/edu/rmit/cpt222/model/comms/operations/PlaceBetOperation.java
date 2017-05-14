package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class PlaceBetOperation extends AbstractGameOperation implements Serializable {
	
	private Player player;
	private int betPoints;

	public PlaceBetOperation(Player player, int betPoints) {
		this.player = player;
		this.betPoints = betPoints;
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		try {
			serverStub.getEngine().placeBet(this.player, this.betPoints);
			
			// Return bet to client
			requestStream.writeObject(
					serverStub.getEngine().getPlayer(
							player.getPlayerId()).getBet());
		} catch (InsufficientFundsException e) {
			; // TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
