package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public class GetAllPlayersOperation extends AbstractGameOperation {

	public GetAllPlayersOperation()	{}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		try {
			requestStream.reset();
			requestStream.writeObject(
					serverStub.getEngine().getAllPlayers());
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
