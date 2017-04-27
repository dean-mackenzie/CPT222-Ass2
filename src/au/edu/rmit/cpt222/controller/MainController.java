package au.edu.rmit.cpt222.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import au.edu.rmit.cpt222.model.GUICallbackImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainWindow;

public class MainController {
	public final static int INITIAL_DELAY = 1;
	public final static int FINAL_DELAY = 1000;
	public final static int DELAY_INCREMENT = 200;
	
	private MainWindow mw;
	private GameEngine engine;
	
	public MainController(MainWindow mw) {
		this.mw = mw;
		this.engine = mw.getModel();
		this.engine.addGameEngineCallback(new GUICallbackImpl(this));
	}
	
	public boolean addPlayer(String playerId, String playerName, int points) {
		try {
			SimplePlayer player = new SimplePlayer(playerId, playerName, points);
			this.engine.addPlayer(player);
			this.mw.updatePoints(player.getBet(), player.getPoints());
		} catch (Exception e) {
			// Not sure what exceptions may be raised, so catch all
			return false;
		}
		return true;
	}
	
	public void betAndRoll(int bet) {
		//Place bet on a per player basis
		// NOTE: this loop isn't needed for Assignment 1,
		// but may be needed for Assignment 2
		for (Player player : this.engine.getAllPlayers()) {
			try {
				player.placeBet(bet);
				
				new Thread() {
					public void run() {
						MainController.this.engine.rollPlayer(
								player, INITIAL_DELAY, FINAL_DELAY,
								DELAY_INCREMENT);
						MainController.this.engine.calculateResult();
					}
				}.start();
			} catch (InsufficientFundsException e) {
				this.mw.displayWarning("Insufficient funds to place a " +
					"bet of that size.");
			}
		}
	}
	
	public boolean checkPlayerExists() {
		if (this.engine.getAllPlayers().size() < 1) {
			this.mw.displayWarning("Player must be added before playing.");
			return false;
		}
		return true;
	}
	
	public int getNumberPlayers() {
		return this.engine.getAllPlayers().size();
	}
	
	public List<String> getPlayerNames() {
		// Handle model logic
		List<String> playerNames = new ArrayList<String>();
		Collection<Player> players = this.engine.getAllPlayers();
		
		for (Player player : players) {
			playerNames.add(player.getPlayerName());
		}
		
		return playerNames;
	}
	
	// NOTE: This couples the controller to the view a little,
	// but the callback interfaces don't provide ways to do this
	public void reset() {
		// Get list of players
		for (Player player : this.engine.getAllPlayers()) {
			// Remove player from HashMap
			this.engine.removePlayer(player);
		}
		
		// Reset roll area
		this.mw.getRollArea().resetRollArea();
	}
		
	public void updateRollArea(String rollType, int dice1, int dice2) {
		this.mw.updateRollPanel(rollType, dice1, dice2);
	}
	
	public void updateStatusArea(GameStatus result, int bet, int points) {
		String resultText = String.valueOf(result);
		this.mw.getBottomBars().updateGameStatus(resultText, bet, points);
	}
}
