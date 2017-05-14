package au.edu.rmit.cpt222.model.comms.callback.operations;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class PlayerRollOperation implements CallbackOperation, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4130548260943155511L;
	private Player player;
	private DicePair dicePair;
	//private GameEngine engine;
	String methodName;

	public PlayerRollOperation(Player player, DicePair dicePair) {//, GameEngine engine) {
		this.player = player;
		this.dicePair = dicePair;
		//this.engine = engine;
		methodName = "playerRoll";
	}
	
	@Override
	public void execute(GameEngineCallback callback) {
		
		//callback.playerRoll(player, dicePair, engine);
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
