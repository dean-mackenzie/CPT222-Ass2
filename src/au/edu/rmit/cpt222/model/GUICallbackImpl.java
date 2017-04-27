package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GUICallbackImpl extends GameEngineCallbackImpl {
	MainController controller;
	
	public GUICallbackImpl(MainController controller) {
		this.controller = controller;
	}
	
	public void houseRoll(DicePair dicePair, GameEngine engine) {
		super.houseRoll(dicePair, engine);
		
		// Update the controller/view
		String rollType = "house";
		this.controller.updateRollArea(rollType, dicePair.getDice1().getFace(), dicePair.getDice2().getFace());
	}
	
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// Make the actual roll in callbacks
		super.playerRoll(player, dicePair, engine);
		
		// Update the controller/view
		String rollType = "player";
		this.controller.updateRollArea(rollType, dicePair.getDice1().getFace(), dicePair.getDice2().getFace());
	}
	
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		super.playerRollOutcome(player, result, engine);
		
		// Update the controller/view
		String rollType = "player";
		this.controller.updateRollArea(rollType, result.getDice1().getFace(), result.getDice2().getFace());
	}
	
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
	    super.gameResult(player, result, engine);
	    
	    // Update the controller/view
	    int bet = player.getBet();
	    int points = player.getPoints();
	    this.controller.updateStatusArea(result, bet, points);

	}

}
