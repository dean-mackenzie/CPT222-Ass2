package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.comms.operations.GameOperation;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

public class GameEngineServerStub {
	
	// use GameEngineImpl to perform all game functions
	private GameEngine engine = new GameEngineImpl();
	
	private ObjectOutputStream requestStream;
	private ObjectInputStream responseStream;
	
	public GameEngineServerStub(int port) {
		// Open server socket to wait for client connections
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			// TODO: see workshop 2 notes (52:00)
			System.out.println("Server on " + serverSocket.getLocalPort() + " / " + serverSocket.getLocalSocketAddress() + " waiting");

			// set to timeout: serverSocket.setSoTimeout(0);
				
			// Handle requests sent
			this.handleRequest(serverSocket.accept());
				
				// THIS IS ONE OF THE KEY PARTS OF ASSIGNMENT
				// create new thread for each new incoming client
				// to process requests
				// multi-thread implementation here
					// at some point, accept client connections
					// and deal with them: serverSocket.accept();
						// .accept() is invoked once client connections start hitting

				// all you have to is execute() for the commands sent through(??)
					// the operation method, that is
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
	
	public void handleRequest (Socket socket) throws IOException {
		System.out.println("Connection accepted on " + socket.getPort());

		try {
			this.requestStream = new ObjectOutputStream(socket.getOutputStream());
			this.responseStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("IOException thrown (streams)");
			e.printStackTrace();
		}

		while (!socket.isClosed()) {
			try {
				GameOperation op = (GameOperation) this.responseStream.readObject();
				op.execute(this, requestStream);
				System.out.println("Operation executed: " + op.toString());
			}
			catch (ClassCastException | ClassNotFoundException e) {
				// Thrown when client connects, which is OK (it's not an operation)
				;
				System.out.println("ClassCastException thrown (should only be conn?)");
			}
			catch (SocketException e) {
				e.printStackTrace();
				socket.close();
			}
		}
	}
	
	public void addGameEngineCallback(HostDetails details) {
		engine.addGameEngineCallback(new ServerStubGameEngineCallback(details));
	}

	public GameEngine getEngine() {
		return this.engine;
	}

}
