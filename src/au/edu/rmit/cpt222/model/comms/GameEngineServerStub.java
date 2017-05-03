package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.net.ServerSocket;


import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

public class GameEngineServerStub {
	
	// use GameEngineImpl to perform all game functions
	private GameEngine engine = new GameEngineImpl();
	
	public GameEngineServerStub(int port) {
		// Open server socket to wait for client connections
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			// Listen for connections
			// TODO: see workshop 2 notes (52:00)
			while(!serverSocket.isClosed()) {
				System.out.println("Server on " + port + " waiting");
				serverSocket.accept();
				// THIS IS ONE OF THE KEY PARTS OF ASSIGNMENT
				// create new thread for each new incoming client
				// to process requests
				// multi-thread implementation here
					// at some point, accept client connections
					// and deal with them: serverSocket.accept();
						// .accept() is invoked once client connections start hitting

				// all you have to is execute() for the commands sent through(??)
					// the operation method, that is
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addGameEngineCallback(HostDetails details) {
		engine.addGameEngineCallback(new ServerStubGameEngineCallback(details));
	}

	public GameEngine getEngine() {
		return this.engine;
	}

}
