package au.edu.rmit.cpt222.model.comms.operations;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;

public abstract class AbstractGameOperation implements Serializable, GameOperation {
	
	static final Logger logger = Logger.getLogger(
			AbstractGameOperation.class.getName());
	
	public abstract void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream);
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
