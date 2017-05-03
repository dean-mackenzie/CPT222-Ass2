package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.edu.rmit.cpt222.model.comms.operations.AddPlayerOperation;
import au.edu.rmit.cpt222.model.comms.operations.GetPlayerOperation;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineClientStub implements GameEngine {

	// this is effectively the controller (local version of model)
	// 

	// Process:
	//open a socket
	//open output/input streams to this socket
	//use the streams to read/write data to server
	//close streams
	//close socket
	
	private ObjectOutputStream requestStream;
	private ObjectInputStream responseStream;
	
	private ClientGameEngineCallbackServer callbackServer;
	
	private GameEngineCallback callback;
	
	public GameEngineClientStub(String hostName, int hostPort) {
		
		callbackServer = new ClientGameEngineCallbackServer(this);
		
		try {
			// Open server connection
			Socket clientSocket = new Socket(hostName, hostPort);
			
			// Open streams for client/server communication
			// doesn't happen here?
			this.requestStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			this.responseStream = new ObjectInputStream(
					clientSocket.getInputStream());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.registerGECallbackServer(new HostDetails(
				"localhost", this.callbackServer.getSocketPort()));
	}

	// Controller instantiates this
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.callback = gameEngineCallback; 
	}

	@Override
	public void addPlayer(Player player) {
		try {
			this.requestStream.writeObject(new AddPlayerOperation(player));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		// Prepare data for sending
//		Map<String, Player> playerData = new HashMap<String, Player>();
//		playerData.put("addPlayer", player);
	
		// Get response back (if anything)

	}

	@Override
	public void calculateResult() {
		// TODO Auto-generated method stub
		
		// Add operations that corresponds to each method (as above)

	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GameEngineCallback getCallback() {
		return callback;
	}

	@Override
	public Player getPlayer(String id) {
		Player player = null;
		try {
			this.requestStream.writeObject(new GetPlayerOperation(id));
			player = (Player) this.responseStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public void placeBet(Player player, int betPoints) throws InsufficientFundsException {
		// TODO Auto-generated method stub
		
		// So even though this is void, you need to get data back to show exception was thrown

	}
	
	// Use commands instead of host details
	public void registerGECallbackServer(HostDetails callbackServerDetails) {
		try {
			this.requestStream.writeObject(callbackServerDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// use ObjectOutputStream to send port number to server
		// callbackServer.getSocketPort() to server stub
		// other methods will work similarly
	}

	@Override
	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		//this.requestStream.writeObject(...);
		//this.responseStream.readObject(...);
		return false;
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub
		//this.requestStream.writeObject(...);

	}

	@Override
	public void setPlayerPoints(Player player, int totalPoints) {
		// TODO Auto-generated method stub

	}

}
