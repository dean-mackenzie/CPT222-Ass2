package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.net.ServerSocket;


import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

public class GameEngineServerStub {
	
	// use GameEngineImpl to perform all game functions
	private GameEngine engine = new GameEngineImpl();
	
	public GameEngineServerStub(int port) {
		//open up server socket to listen for incoming client connections
		try {
			ServerSocket socket = new ServerSocket(port);
			
			// Listen for closed connection
			while(!socket.isClosed()) {
				// create new thread for each new incoming client
				// to process requests
				// multi-thread implementation here
					// at some point, accept client connections
					// and deal with them: socket.accept();
						// .accept() is invoked once client connections start hitting
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
