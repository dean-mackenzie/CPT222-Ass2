package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameResultOperation implements CallbackOperation, Serializable {
	
	private Player player;
	private GameStatus result;
	private GameEngine engine;

	public GameResultOperation(Player player, GameStatus result, GameEngine engine) {
		this.player = player;
		this.result = result;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callback) {
		callback.gameResult(this.player, this.result, this.engine);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
