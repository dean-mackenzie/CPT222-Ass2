package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class RemovePlayerOperation extends AbstractGameOperation {
	
	private Player player;

	public RemovePlayerOperation(Player player)	{
		this.player = player;
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		try {
			requestStream.reset();
			requestStream.writeObject(
					serverStub.getEngine().removePlayer(player));
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
