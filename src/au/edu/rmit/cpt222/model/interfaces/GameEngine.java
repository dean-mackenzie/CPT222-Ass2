package au.edu.rmit.cpt222.model.interfaces;

import java.util.Collection;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;

/**
 * Assignment interface (facade) for SADI providing main model functionality
 *
 * @author Mikhail Perepletchikov
 *
 */
public interface GameEngine {

	public enum GameStatus {
		WON, LOST, DREW
	}

	public static final int NUM_OF_DICE = 2; // default

	/**
	 * Adds new GameEngineCallback to the GameEngine
	 * 
	 * @param gameEngineCallback
	 *            a client specific implementation of GameEngineCallback used to
	 *            perform display updates etc. You will use a different
	 *            implementation of the GameEngineCallback for GUI and console
	 *            versions
	 *
	 */
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback);

	/**
	 * Adds a Player to the game
	 * 
	 * @param player
	 *            Player object to add to the game
	 */
	public void addPlayer(Player player);

	/**
	 * This method rolls for the house and then goes through all players and
	 * applies win/loss/draw outcome to update betting points.
	 * {@link GameEngineCallback#gameResult(Player, GameStatus, GameEngine)}
	 * should also be called with final result for each player.
	 *
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngineCallback
	 */
	public void calculateResult();

	/**
	 * Returns all players participating in a game
	 * 
	 * @return an unmodifiable collection of all Players
	 * @see au.edu.rmit.cpt222.model.interfaces.Player
	 */
	public Collection<Player> getAllPlayers();

	/**
	 * Retrieves a Player indicated by the provided id
	 * 
	 * @param id
	 *            id of the Player to retrieve (should return null if not found)
	 * @return Player object
	 */
	public Player getPlayer(String id);

	/**
	 * The implementation should forward the call to the Player entity
	 * 
	 * @param player
	 *            betting Player
	 * @param betPoints
	 *            the bet in points
	 * @throws au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException
	 *             if the player has insufficient points and the bet cannot be
	 *             placed
	 * 
	 */
	public void placeBet(Player player, int betPoints) throws InsufficientFundsException;

	/**
	 * Removes a callback associated with the player (when a player quits the
	 * game) to remove no longer needed UI updates
	 * 
	 * TODO (no need to implement in Ass1)
	 * 
	 * @param gameEngineCallback
	 *            a client specific implementation of GameEngineCallback to be
	 *            removed from the game. NOTE: to be used in Assignment 2
	 * 
	 */
	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback);

	/**
	 * Removes a given Player from the game
	 * 
	 * @param player
	 *            reference to the Player to be removed
	 * @return true if the player existed
	 */
	public boolean removePlayer(Player player);

	/**
	 * Same as rollPlayer(), but rolls for the house and calls the house
	 * versions of the callback methods on GameEngineCallback. No player
	 * parameter is required
	 * 
	 * @param initialDelay
	 * @param finalDelay
	 * @param delayIncrement
	 * 
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngine#rollHouse(int, int,
	 *      int)
	 */
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement);

	/**
	 * Roll both dice progressing from the initialDelay to the finalDelay in
	 * increments of delayIncrement. Delays are in milliseconds (ms)
	 * 
	 * 1. start at initialDelay then increment by delayIncrement each time a new
	 * number is shown on the dice faces; 2. call
	 * GameEngineCallback.playerRoll(...) or houseRoll(...) each time a pair of
	 * new dice faces are shown until delay is equal or greater than finalDelay;
	 * 3. call GameEngineCallback.playerRollOutcome(...) or
	 * houseRollOutcome(...) with final result for player or house; 4. update
	 * the player with final result so it can be retrieved later
	 * 
	 * @param player
	 *            the player who is rolling and will have their rollResult set
	 *            at the end of the roll
	 * @param initialDelay
	 *            the starting delay in ms between updates (based on how fast
	 *            dice are rolling). default value should be set to 1
	 * @param finalDelay
	 *            the final delay in ms between updates when the dice stop
	 *            rolling. default value should be set to 100
	 * @param delayIncrement
	 *            how much the dice slow down (delay gets longer) after each
	 *            roll/tumble. default value should be set to 20
	 * 
	 * @see au.edu.rmit.cpt222.model.interfaces.GameEngineCallback
	 * 
	 */
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement);

	/**
	 * 
	 * Sets player's (credit) points to the provided value
	 * 
	 * @param player
	 *            reference to the Player to be updated with new points
	 * @param totalPoints
	 *            sets player points balance (total points)
	 * 
	 */
	public void setPlayerPoints(Player player, int totalPoints);

}