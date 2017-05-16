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
			serverStub.getEngine().placeBet(player, this.betPoints);
		} catch (InsufficientFundsException e) {
			; // TODO: handle exception
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
