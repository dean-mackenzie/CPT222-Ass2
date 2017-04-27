
package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerID;
	private String playerName;
	private int points;
	private int bet;
	private DicePair playerDice;
	private GameStatus status;
			
	public SimplePlayer(String playerID, String playerName, int points) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.points = points;
	}
	
	public String getPlayerId() {
		return playerID;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public int getPoints() {
		return points;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void placeBet(int bet) throws InsufficientFundsException {
		//Throw exception if bet larger than points
		if (bet > points) {
			throw new InsufficientFundsException();
		}
		
		//Place bet
		this.bet = bet;
	}
	
	//TODO: Ignore for Assignment 1 (as per interface details)
	public void resetBet() throws InsufficientFundsException {
		;
	}

	public void setGameResult(GameStatus status) {
		this.status = status;
	}
	
	public GameStatus getGameResult() {
		return status;
	}
	
	public DicePair getRollResult() {
		return playerDice;
	}
	
	public void setRollResult(DicePair rollResult) {
		playerDice = rollResult;
	}
	
	@Override
	public String toString() {
		String playerDetails;
	
		// If no game result, don't call rolls
		// This will throw exception calling methods on null objects
		if (this.getGameResult() == null) {
			playerDetails = ("Player: id= " + this.getPlayerId() + 
				", name= " + this.getPlayerName() + ", bet amount= " + this.getBet() +
				", total credit points= " + this.getPoints());
			
		}
		else {
			playerDetails = ("Player: id= " + this.getPlayerId() + 
				", name= " + this.getPlayerName() + ", bet amount= " + this.getBet() +
				", roll result = Dice 1: " + String.valueOf(this.getRollResult().getDice2().getFace()) +
				", Dice 2: " + String.valueOf(this.getRollResult().getDice2().getFace()) +
				" ... Total: " + String.valueOf(this.getRollResult().getTotalScore()) + 
				", game outcome = " + this.getGameResult() + 
				", total credit points= " + this.getPoints());
		}
		return playerDetails;
	}
}
