package au.edu.rmit.cpt222.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;


public class GameEngineImpl implements GameEngine {
	public static int INITIAL_DELAY;
	public static int FINAL_DELAY;
	public static int DELAY_INCREMENT;
	
	private Map<String, Player> players = new HashMap<String, Player>(); 
	private DicePair playerDice;
	private DicePair houseDice;
	private Set<GameEngineCallback> callbacks = Collections.
			newSetFromMap(new HashMap<GameEngineCallback, Boolean>());

	public GameEngineImpl() {}
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback);
	}
	
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}
	
	public void calculateResult() {
		//Looping for multiple players (not needed for Ass 1)
		for (GameEngineCallback callback : callbacks) {
			// Roll for house
			this.rollHouse(INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);
			
			for (Player player : this.players.values()) {
				// Players who are playing must have a bet and a score
				// This conditional may not be required in Assignment 2
				if (player.getBet() > 0 && player.getRollResult()
						.getTotalScore() > 0) {
					// Compare rolls, set result and add/subtract points
					if (this.houseDice.getTotalScore() > player.
							getRollResult().getTotalScore()) {
						GameStatus status = GameEngine.GameStatus.LOST;					
						player.setGameResult(status);
						
						// Subtract bet from player points
						int points = player.getPoints() - player.getBet();
						this.setPlayerPoints(player, points);
					}
					else if (houseDice.getTotalScore() == player.
							getRollResult().getTotalScore()) {
						GameStatus status = GameEngine.GameStatus.DREW;					
						player.setGameResult(status);
						
						// No change to points on a draw
					}
					else {
						GameStatus status = GameEngine.GameStatus.WON;					
						player.setGameResult(status);
						
						// Add bet to player points
						int points = player.getPoints() + player.getBet();
						this.setPlayerPoints(player, points);
					}
					
					// Display result
					callback.gameResult(this.getPlayer(player.getPlayerId()), 
							this.getPlayer(player.getPlayerId()).getGameResult(), this);
				}
			}
		}
	}

	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(new ArrayList<Player>(
				this.players.values()));
	}
	
	public Player getPlayer(String id) {
		return this.players.get(id);
	}
	
	public void placeBet(Player player, int betPoints) 
			throws InsufficientFundsException {

		//Check if enough points to bet, then place bet
		if (betPoints > this.getPlayer(player.getPlayerId())
				.getPoints()) {
			throw new InsufficientFundsException();
		}
		else if (betPoints < 1) {
			throw new IllegalArgumentException();
		}
		else {
			this.getPlayer(player.getPlayerId()).
			placeBet(betPoints);
		}
	}

	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.remove(gameEngineCallback);
	}
	
	public void removeAllPlayers() {
		players.clear();
	}

	public boolean removePlayer(Player player) {
		try {
			players.remove(player.getPlayerId());
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	public void rollHouse(int initialDelay, int finalDelay, 
			int delayIncrement) {
		// As per rollPlayer (minus player)
		for (GameEngineCallback callback : this.callbacks) {
			for(int i = 0; i < FINAL_DELAY; i = i + DELAY_INCREMENT) {
				// Handles GUI animation
				this.houseDice = new DicePairImpl();
				callback.houseRoll(houseDice, this);
				this.delayRoll(DELAY_INCREMENT);
			}
			callback.houseRollOutcome(this.houseDice, this);
		}
	}
	
	public void rollPlayer(	Player player, int initialDelay, 
			int finalDelay, int delayIncrement) {
		// Sets delays for both player and house roll
		INITIAL_DELAY = initialDelay;
		FINAL_DELAY = finalDelay;
		DELAY_INCREMENT = delayIncrement;
		
		// Intermediate rolls
		for (GameEngineCallback callback : this.callbacks) {
			for(int i = 0; i <= FINAL_DELAY; i = i + DELAY_INCREMENT) {
				// Sets roll and handles console / GUI callbacks
				this.playerDice = new DicePairImpl();
				callback.playerRoll(player, playerDice, this);
				this.getPlayer(player.getPlayerId()).setRollResult(playerDice);
				this.delayRoll(DELAY_INCREMENT);
			}
			// Get final roll outcome
			callback.playerRollOutcome(this.getPlayer(player.getPlayerId()),
					this.getPlayer(player.getPlayerId()).getRollResult(), this);
		}
	}
	
	public void delayRoll(int delayPeriod) {
		try {
			Thread.sleep(delayPeriod);
		} catch (InterruptedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPlayerPoints(Player player, int totalPoints) {
		this.getPlayer(player.getPlayerId()).setPoints(totalPoints);
	}
}
