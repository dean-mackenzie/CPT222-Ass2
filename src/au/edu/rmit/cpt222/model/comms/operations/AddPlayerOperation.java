package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class AddPlayerOperation extends AbstractGameOperation implements Serializable {
	
	private Player player;

	public AddPlayerOperation(Player player) {
		this.player = player;
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		// addPlayer() is a void method, so nothing to write back
		serverStub.getEngine().addPlayer(this.player);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
