package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.comms.operations.GameOperation;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

public class GameEngineServerStub {

	private GameEngine engine = new GameEngineImpl();
	private ObjectOutputStream responseStream;
	private ObjectInputStream requestStream;
	
	public GameEngineServerStub(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			System.out.println("Server on " + serverSocket.getLocalPort() + 
					" / " + serverSocket.getLocalSocketAddress() + " waiting");

			// set to timeout: serverSocket.setSoTimeout(0);
			
			// Open server socket to wait for client connections
			while (!serverSocket.isClosed()) {
				new RequestTask (this, serverSocket.accept()).start();
			}
			
		} catch (BindException e) {
			System.out.println("Could not initialise server. Is one already running?");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Something IO-related occurred.");
			e.printStackTrace();
		}
	}
	
	// TODO: Superseded by RequestTask: delete once multi-threading working
	public void handleRequest (Socket socket) throws IOException {
		System.out.println("Connection accepted on " + socket.getPort());

		// Set up streams for client/server communication
		try {
			this.responseStream = new ObjectOutputStream(socket.getOutputStream());
			this.requestStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("IOException thrown (streams)");
			e.printStackTrace();
		}

		// Loop to handle multiple requests from client
		while (!socket.isClosed()) {
			try {
				GameOperation op = (GameOperation) this.requestStream.readObject();
				op.execute(this, responseStream);
				System.out.println("Operation executed: " + op.toString());
			}
			catch (ClassCastException | ClassNotFoundException e) {
				// Thrown when client connects, which is OK (it's not an operation)
				System.out.println("ClassCastException thrown");
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
