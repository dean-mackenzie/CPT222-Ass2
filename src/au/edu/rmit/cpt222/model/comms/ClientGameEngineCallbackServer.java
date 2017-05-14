package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import au.edu.rmit.cpt222.model.comms.callback.operations.CallbackOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.GameResultOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.HouseRollOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.HouseRollOutcomeOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.PlayerRollOperation;
import au.edu.rmit.cpt222.model.comms.callback.operations.PlayerRollOutcomeOperation;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;

public class ClientGameEngineCallbackServer {

	private GameEngineCallback callback;
	private ObjectInputStream inputStream;
	private int socketPort = 0;	// capture dynamic port no. and send to server\
	private GameEngineClientStub gameEngine;
		
	
	public ClientGameEngineCallbackServer(GameEngineClientStub clientStub) {
		this.gameEngine = clientStub;
		this.startCallbackServer();
	}
	
//	private GameEngine getClientStub() {
//		return this.client;
//	}
	
	private void startCallbackServer() {
		// Do this in a separate thread, as this won't happen straight away
		// and if it waits it blocks the app
		Thread thread = new Thread()
		{
			ServerSocket socket;
			Socket clientSocket;
			
			@Override
			public void run() {
				try {
					this.socket = new ServerSocket(socketPort);	
					ClientGameEngineCallbackServer.this.socketPort = 
							socket.getLocalPort();
					
					System.out.println("Callback Server on " + socket.getLocalPort() + 
							" / " + socket.getLocalSocketAddress() + " waiting");
					
					// Wait for server-side connections
					clientSocket = this.socket.accept();

					// Don't need outputStream(?), executing operations to GUICallback
					ClientGameEngineCallbackServer.this.inputStream = 
							new ObjectInputStream(clientSocket.getInputStream());
					
					// Loop to handle multiple requests from client
					while (!socket.isClosed()) {
						try {
							// This acts like server, executing callback methods
							// Have to know which specific method to call
							// pass back data like ArrayList - methodName (0), data(1), data(2)...
							CallbackOperation op = 
									(CallbackOperation) ClientGameEngineCallbackServer.this.inputStream.readObject();
							//op.execute(ClientGameEngineCallbackServer.this.callback);
							
							try {
								//TODO: put this into a separate method
								callback = ClientGameEngineCallbackServer.this.gameEngine.getCallback();
								if (op.getMethodName().equals("playerRoll")) {
									Player player = ((PlayerRollOperation) op).getPlayer();
									DicePair dicePair = ((PlayerRollOperation) op).getDicePair();
									callback.playerRoll(player, dicePair, gameEngine);
								}
								if (op.getMethodName().equals("playerRollOutcome")) {
									Player player = ((PlayerRollOutcomeOperation) op).getPlayer();
									DicePair dicePair = ((PlayerRollOutcomeOperation) op).getDicePair();
									callback.playerRollOutcome(player, dicePair, gameEngine);
								}
								if (op.getMethodName().equals("houseRoll")) {
									DicePair dicePair = ((HouseRollOperation) op).getDicePair();
									callback.houseRoll(dicePair, gameEngine);
								}
								if (op.getMethodName().equals("houseRollOutcome")) {
									DicePair dicePair = ((HouseRollOutcomeOperation) op).getDicePair();
									callback.houseRoll(dicePair, gameEngine);
								}
								if (op.getMethodName().equals("gameResult")) {
									Player player = ((GameResultOperation) op).getPlayer();
									GameStatus result = ((GameResultOperation) op).getResult();
									callback.gameResult(player, result, gameEngine);
								}
								
							} catch (Exception e) {
								e.getMessage();
								e.printStackTrace();
							}
							
							System.out.println("Operation executed: " + op.toString());
						}
						catch (ClassCastException | ClassNotFoundException e) {
							System.out.println("ClassCastException thrown");
							e.printStackTrace();
						}
						catch (SocketException e) {
							e.printStackTrace();
							socket.close();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	public int getSocketPort() {
		return this.socketPort;
	}
}
