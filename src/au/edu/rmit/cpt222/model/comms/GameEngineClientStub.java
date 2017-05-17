package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import au.edu.rmit.cpt222.model.comms.operations.AddPlayerOperation;
import au.edu.rmit.cpt222.model.comms.operations.CalculateResultOperation;
import au.edu.rmit.cpt222.model.comms.operations.RegisterCBOperation;
import au.edu.rmit.cpt222.model.comms.operations.GetAllPlayersOperation;
import au.edu.rmit.cpt222.model.comms.operations.GetPlayerOperation;
import au.edu.rmit.cpt222.model.comms.operations.PlaceBetOperation;
import au.edu.rmit.cpt222.model.comms.operations.RemovePlayerOperation;
import au.edu.rmit.cpt222.model.comms.operations.RollPlayerOperation;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineClientStub implements GameEngine {
	
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
		
		RegisterCBOperation op = new RegisterCBOperation(
				"localhost", this.callbackServer.getSocketPort());
		this.registerGECallbackServer(op);
	}

	// Controller instantiates this
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.callback = gameEngineCallback; 
	}

	@Override
	public void addPlayer(Player player) {
		try {
			this.requestStream.reset();
			this.requestStream.writeObject(new AddPlayerOperation(player));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void calculateResult() {
		try {
			this.requestStream.reset();
			this.requestStream.writeObject(new CalculateResultOperation());
			//this.responseStream.readObject
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> players = null;
		try {
			this.requestStream.reset();
			this.requestStream.writeObject(new GetAllPlayersOperation());
			players = (Collection<Player>) this.responseStream.readObject();
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
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
			this.requestStream.reset();
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
		try {
			this.requestStream.reset();
			this.requestStream.writeObject(new PlaceBetOperation(
					this.getPlayer(player.getPlayerId()), betPoints));
			
			Boolean insufficientFunds = (Boolean) this.responseStream.readObject();
			if (insufficientFunds) {
				throw new InsufficientFundsException();
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerGECallbackServer(RegisterCBOperation op) {
		try {
			this.requestStream.reset();
			this.requestStream.writeObject(op);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// callbackServer.getSocketPort() to server stub
	}

	@Override
	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removePlayer(Player player) {
		boolean removed = false;
		try {
			this.requestStream.reset();
			this.requestStream.writeObject(new RemovePlayerOperation(player));
			removed = (boolean) this.responseStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return removed;
	}


	// DO I need this one?
	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollPlayer(Player player, int initialDelay, 
			int finalDelay, int delayIncrement) {
	
		// TODO: remove debug msg
		try {
			this.requestStream.writeObject(new RollPlayerOperation(
					this.getPlayer(player.getPlayerId()), initialDelay, 
					finalDelay, delayIncrement));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setPlayerPoints(Player player, int totalPoints) {
		// TODO Auto-generated method stub

	}

}
