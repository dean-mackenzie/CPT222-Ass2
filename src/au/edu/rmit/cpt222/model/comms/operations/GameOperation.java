package au.edu.rmit.cpt222.model.comms.operations;

import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public interface GameOperation {
		
	public void execute(GameEngineServerStub serverStub, ObjectOutputStream responseStream);
}
