package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class HouseRollOutcomeOperation implements CallbackOperation, Serializable {
	
	private DicePair dicePair;
	private GameEngine engine;
	String methodName;
	

	public HouseRollOutcomeOperation(DicePair dicePair) {
		this.dicePair = dicePair;
		this.methodName = "houseRollOutcome";
	}
	
	@Override
	public void execute(GameEngineCallback callback, GameEngine engine) {
		callback.houseRollOutcome(this.dicePair, this.engine);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public DicePair getDicePair() {
		return this.dicePair;
	}
}
