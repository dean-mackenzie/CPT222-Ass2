package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineClientStub implements GameEngine {
	
	// Streams to communicate with server
	private ObjectOutputStream requestStream;
	private ObjectInputStream responseStream;
	
	//open a socket
	//open output/input streams to this socket
	//user the streams to read/write data to server
	//close streams
	//close socket
	
	public GameEngineClientStub(String hostName, int hostPort) {
		try {
			// Open server connection
			Socket socket = new Socket(hostName, hostPort);
			
			// Open streams for client/server communication
			// doesn't happen here?
			requestStream = new ObjectOutputStream(
					socket.getOutputStream());
			responseStream = new ObjectInputStream(
					socket.getInputStream());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculateResult() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayer(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeBet(Player player, int betPoints) throws InsufficientFundsException {
		// TODO Auto-generated method stub

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
