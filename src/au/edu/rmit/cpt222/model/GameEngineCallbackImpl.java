package au.edu.rmit.cpt222.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback {
	private DicePair result;

	protected Logger logger = Logger.getLogger("Test");

	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		this.logger.log(Level.INFO, "Game Result: " + player.getPlayerName()
				+ " has " + result);
	}
	
	// Returns house roll as a score
	public int getHouseRoll() {
		return result.getTotalScore();
	}

	public void houseRoll(DicePair dicePair, GameEngine engine) {
		// Set the result dice to parameter
		result = dicePair;
		
		//Log intermediate roll
		this.logger.log(Level.INFO, "Player: House, intermediate roll= Dice 1: " 
			+ dicePair.getDice1().getFace() + ", Dice 2: " + dicePair.getDice2().getFace()  
			+  " ... Total: " + dicePair.getTotalScore());
	}
	
	public void houseRollOutcome(DicePair result, GameEngine engine) {
		// Log result
		this.logger.log(Level.INFO, "Player: House, final roll state= Dice 1: "
			+ result.getDice1().getFace() + ", Dice 2: " + result.getDice2().getFace()
			+ " ... Total: " + result.getTotalScore());
	}
	
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// Log intermediate roll
		this.logger.log(Level.INFO, "Player: " + player.getPlayerName() + ", intermediate roll= Dice 1: " 
			+ dicePair.getDice1().getFace() + ", Dice 2: " + dicePair.getDice2().getFace()  
			+  " ... Total: " + dicePair.getTotalScore());
	}
	
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		// Log final roll outcome
		this.logger.log(Level.INFO, "Player: " + player.getPlayerName() 
			+ ", final roll state= Dice 1: " + result.getDice1().getFace() 
			+ ", Dice 2: " + result.getDice2().getFace()  
			+  " ... Total: " + result.getTotalScore());
	}
	
	public void test() {
		
	}
}
