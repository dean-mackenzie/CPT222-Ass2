package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameResultOperation implements CallbackOperation, Serializable {
	
	private Player player;
	private GameStatus result;
	private String methodName;


	public GameResultOperation(Player player, GameStatus result) {
		this.player = player;
		this.result = result;
		this.methodName = "gameResult";
	}
	
	@Override
	public void execute(GameEngineCallback callback) {
		//callback.gameResult(this.player, this.result, this.engine);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}

	public Player getPlayer() {
		return player;
	}

	public GameStatus getResult() {
		return result;
	}

	public String getMethodName() {
		return methodName;
	}
}
