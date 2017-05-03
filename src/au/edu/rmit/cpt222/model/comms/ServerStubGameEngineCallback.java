package au.edu.rmit.cpt222.model.comms;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class ServerStubGameEngineCallback implements GameEngineCallback {
	
	public ServerStubGameEngineCallback(HostDetails details) {
		// details needed to establish the send back to client callback
		// To do: open a socket to client callback server to send data
	}

	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void houseRoll(DicePair dicePair, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void houseRollOutcome(DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

}
