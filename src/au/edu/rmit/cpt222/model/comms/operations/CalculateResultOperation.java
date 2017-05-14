package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class CalculateResultOperation extends AbstractGameOperation {

	public CalculateResultOperation() {}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		serverStub.getEngine().calculateResult();
		try {
			// calculateResult() returns nothing, so just do a simple write back
			requestStream.reset();
			requestStream.writeObject(true);
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
