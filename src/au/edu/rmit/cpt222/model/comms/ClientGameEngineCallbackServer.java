package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientGameEngineCallbackServer {
	// purpose is to provide client with server callbacks

	private GameEngineClientStub gameEngine;
	private ObjectInputStream inputStream;
	private int socketPort = 0;	// capture dynamic port no. and send to server
	
	public ClientGameEngineCallbackServer(GameEngineClientStub clientStub) {
		this.gameEngine = clientStub;
		this.startCallbackServer();
		
	}
	
	private void startCallbackServer() {
		// Do this in a separate thread, as this won't happen straight away
		// and if it waits it blocks the app
		Thread thread = new Thread()
		{
			ServerSocket socket;
			Socket clientSocket;	//
			
			@Override
			public void run() {
				try {
					this.socket = new ServerSocket(socketPort);	
					// when 0 used, JVM will utilise OS to find first avail port
					ClientGameEngineCallbackServer.this.socketPort = 
							socket.getLocalPort();
					
					// Wait for server-side connections (ServerStubGameEngineCallback)
					clientSocket = this.socket.accept();
					ClientGameEngineCallbackServer.this.inputStream = 
							new ObjectInputStream(clientSocket.getInputStream());
					
					// Create callback loop using inputStream to recieve "callbacks"
					// from server
					//while...
					
					// Have to know which specific method to call
					//example only - CHANGE
//					ClientGameEngineCallbackServer.this.gameEngine
//						.getCallback().houseRoll(dicepair,engine);
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
