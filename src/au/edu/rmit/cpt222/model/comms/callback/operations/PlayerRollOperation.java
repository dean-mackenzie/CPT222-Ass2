package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class PlayerRollOperation implements CallbackOperation, Serializable {
	
	private Player player;
	private DicePair dicePair;
	private GameEngine engine;

	public PlayerRollOperation(Player player, DicePair dicePair, GameEngine engine) {
		this.player = player;
		this.dicePair = dicePair;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callback) {
		callback.playerRoll(player, dicePair, engine);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
