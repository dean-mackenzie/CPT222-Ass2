package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class HouseRollOperation implements CallbackOperation, Serializable {
	
	private DicePair dicePair;
	private String methodName;

	public HouseRollOperation(DicePair dicePair) {
		this.dicePair = dicePair;
		this.methodName = "houseRoll";
	}
	
	@Override
	public void execute(GameEngineCallback callback) {
		//callback.houseRoll(this.dicePair, this.engine);
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
