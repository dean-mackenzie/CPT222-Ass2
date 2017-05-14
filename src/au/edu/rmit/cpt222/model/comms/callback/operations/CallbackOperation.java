package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;

public interface CallbackOperation extends Serializable {
		
	public void execute(GameEngineCallback callback);
	
	public String getMethodName();
}

