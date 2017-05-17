package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameResultOperation implements CallbackOperation, Serializable {
	
	private Player player;
	private GameStatus result;
	private GameEngine engine;


	public GameResultOperation(Player player, GameStatus result) {
		this.player = player;
		this.result = result;
	}
	
	@Override
	public void execute(GameEngineCallback callback, GameEngine engine) {
		callback.gameResult(this.player, this.result, engine);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
