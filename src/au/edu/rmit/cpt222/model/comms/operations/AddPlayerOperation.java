package au.edu.rmit.cpt222.model.comms.operations;

import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class AddPlayerOperation extends AbstractGameOperation {
	
	private Player player;

	public AddPlayerOperation(Player player) {
		this.player = player;
	}

	@Override
	public void execute(GameEngineServerStub serverStub, ObjectOutputStream requestStream) {
		serverStub.getEngine().addPlayer(this.player);
	}
}
