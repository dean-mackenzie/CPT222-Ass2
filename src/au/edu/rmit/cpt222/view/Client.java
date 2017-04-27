package au.edu.rmit.cpt222.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.GameEngineCallbackImpl;
import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

/**
 * Simple console client for SADI Assignment 1, SP1, 2017
 * 
 * @author Mikhail Perepletchikov
 * 
 */
public class Client {
	private static Logger logger = Logger.getLogger("assignment1");
	public final static int INITIAL_DELAY = 1;
	public final static int FINAL_DELAY = 100;
	public final static int DELAY_INCREMENT = 20;
	public final static int SAMPLE_BET1 = 10;
	public final static int SAMPLE_BET2 = 20;
	public final static int SAMPLE_CREDIT_POINTS1 = 100;
	public final static int SAMPLE_CREDIT_POINTS2 = 150;

	public static void main(String args[]) {

		final GameEngine gameEngine = new GameEngineImpl();

		// create two test players
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", SAMPLE_CREDIT_POINTS1),
				new SimplePlayer("2", "The Roller", SAMPLE_CREDIT_POINTS2) };

		// attach GameEngineCallbackImpl to the GameEngine
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		// main loop to add players; place a bet; and roll dice
		for (Player player : players) {
			gameEngine.addPlayer(player);

			// set test bets
			try {
				players[0].placeBet(SAMPLE_BET1);
				players[1].placeBet(SAMPLE_BET2);
			}

			// test insufficient funds exception
			catch (InsufficientFundsException e) {
				logger.log(Level.INFO, e.getMessage());
			}

			// roll dice for this player
			gameEngine.rollPlayer(player, INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);
		}

		// all players have "rolled" so now house rolls and results are
		// calculated
		gameEngine.calculateResult();

		// log updated player state to check correct execution
		for (Player player : gameEngine.getAllPlayers())
			logger.log(Level.INFO, player.toString());
	}
}
