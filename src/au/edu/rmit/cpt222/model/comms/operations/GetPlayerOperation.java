package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public class GetPlayerOperation extends AbstractGameOperation {

	private String playerId;
	
	public GetPlayerOperation(String playerId)
	{
		this.playerId = playerId;
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		try {
			requestStream.writeObject(
					serverStub.getEngine().getPlayer(playerId));
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
