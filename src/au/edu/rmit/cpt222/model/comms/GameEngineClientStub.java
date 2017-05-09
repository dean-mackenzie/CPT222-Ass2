package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import au.edu.rmit.cpt222.model.comms.operations.AddPlayerOperation;
import au.edu.rmit.cpt222.model.comms.operations.GetAllPlayersOperation;
import au.edu.rmit.cpt222.model.comms.operations.GetPlayerOperation;
import au.edu.rmit.cpt222.model.comms.operations.RemovePlayerOperation;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineClientStub implements GameEngine {

	// this is effectively the controller (local version of model)
	// 

	
	private ObjectOutputStream requestStream;
	private ObjectInputStream responseStream;
	
	private ClientGameEngineCallbackServer callbackServer;
	
	private GameEngineCallback callback;
	
	public GameEngineClientStub(String hostName, int hostPort) {
		
		callbackServer = new ClientGameEngineCallbackServer(this);
		
		try {
			// Open server connection
			@SuppressWarnings("resource")
			Socket clientSocket = new Socket(hostName, hostPort);
			
			// Open streams for client/server communication
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
		System.out.println("Trying to add player...");
		try {
			this.requestStream.writeObject(new AddPlayerOperation(player));
			this.responseStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void calculateResult() {
		// TODO Auto-generated method stub
		
		// Add operations that corresponds to each method (as above)

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> players = null;
		try {
			this.requestStream.writeObject(new GetAllPlayersOperation());
			players = (Collection<Player>) this.responseStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
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
		boolean removed = false;
		try {
			this.requestStream.writeObject(new RemovePlayerOperation(player));
			removed = (boolean) this.responseStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return removed;
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
