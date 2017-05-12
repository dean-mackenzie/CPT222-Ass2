package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import au.edu.rmit.cpt222.model.comms.callback.operations.PlayerRollOperation;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class ServerStubGameEngineCallback implements GameEngineCallback, Serializable {
	private ObjectOutputStream requestStream;
	//private ObjectInputStream responseStream;
	
	public ServerStubGameEngineCallback(HostDetails details) {

		try {
			// Open server connection
			@SuppressWarnings("resource")
			Socket clientSocket = new Socket(details.getHostName(), details.getPort());
			
			// Open streams for client/server communication
			this.requestStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			
			System.out.println("CB connection accepted on " + clientSocket.getPort());
			
//			this.responseStream = new ObjectInputStream(
//					clientSocket.getInputStream());

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
		System.out.println("Callback for player roll...");		
		// TODO: remove debug msg
		try {
			// TODO: Check is for debug only
			boolean bob = (new PlayerRollOperation(
					player, dicePair, engine) instanceof Serializable);
			this.requestStream.flush();
			this.requestStream.writeObject(new PlayerRollOperation(
					player, dicePair, engine));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

}
