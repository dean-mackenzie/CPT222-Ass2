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
	String methodName;

	public PlayerRollOperation(Player player, DicePair dicePair) {
		this.player = player;
		this.dicePair = dicePair;
		methodName = "playerRoll";
	}
	
	@Override
	public void execute(GameEngineCallback callback, GameEngine engine) {
		
		callback.playerRoll(player, dicePair, engine);
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public DicePair getDicePair() {
		return this.dicePair;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
