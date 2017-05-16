package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.cpt222.model.comms.callback.operations.GameResultOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.HouseRollOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.HouseRollOutcomeOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.PlayerRollOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.PlayerRollOutcomeOperation;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class ServerStubGameEngineCallback implements GameEngineCallback {
	/**
	 * 
	 */
	private static final long serialVersionUID = 92496294419844285L;
	private ObjectOutputStream requestStream;
	
	public ServerStubGameEngineCallback(HostDetails details) {

		try {
			// Open server connection
			@SuppressWarnings("resource")
			Socket clientSocket = new Socket(details.getHostName(), details.getPort());
			
			// Open streams for client/server communication
			this.requestStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			
			System.out.println("CB connection accepted on " + clientSocket.getPort());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		System.out.println("Callback for game result...");		
		// TODO: remove debug msg
		try {
			this.requestStream.writeObject(new GameResultOperation(
					engine.getPlayer(player.getPlayerId()), result));
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	@Override
	public void houseRoll(DicePair dicePair, GameEngine engine) {
		System.out.println("Callback for house roll...");		
		// TODO: remove debug msg
		try {
			this.requestStream.writeObject(new HouseRollOperation(
					dicePair));
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	@Override
	public void houseRollOutcome(DicePair result, GameEngine engine) {
		System.out.println("Callback for house roll outcome...");		
		// TODO: remove debug msg
		try {
			// TODO: Check is for debug only
			this.requestStream.writeObject(new HouseRollOutcomeOperation(
					result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		System.out.println("Callback for player roll...");		
		// TODO: remove debug msg
		try {
			this.requestStream.writeObject(new PlayerRollOperation(
					engine.getPlayer(player.getPlayerId()), dicePair));
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	@Override
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		System.out.println("Callback for player roll outcome...");		
		// TODO: remove debug msg
		try {
			// TODO: Check is for debug only
			this.requestStream.writeObject(new PlayerRollOutcomeOperation(
					engine.getPlayer(player.getPlayerId()), result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
