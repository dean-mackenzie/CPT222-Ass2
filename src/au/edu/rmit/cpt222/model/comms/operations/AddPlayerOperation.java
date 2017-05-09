package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class AddPlayerOperation extends AbstractGameOperation {
	
	private Player player;

	public AddPlayerOperation(Player player) {
		this.player = player;
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		// addPlayer() is a void method, so nothing to write back
		serverStub.getEngine().addPlayer(this.player);
//		try {
//			requestStream.writeObject(true);
//					
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
