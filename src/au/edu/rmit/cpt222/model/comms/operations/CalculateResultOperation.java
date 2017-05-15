package au.edu.rmit.cpt222.model.comms.operations;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public class CalculateResultOperation extends AbstractGameOperation implements Serializable {

	public CalculateResultOperation() {}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		
		// calculateResult() returns nothing
		serverStub.getEngine().calculateResult();
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
