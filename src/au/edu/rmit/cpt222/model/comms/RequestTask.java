package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import au.edu.rmit.cpt222.model.comms.operations.GameOperation;

//this is where the main work of handling requests from the client is done

public class RequestTask extends Thread

{
	private final GameEngineServerStub gameEngineServerStub;
	private Socket clientSocket;

	public RequestTask(GameEngineServerStub gameEngineServerStub, Socket clientSocket)
	{
		this.gameEngineServerStub = gameEngineServerStub;
		this.clientSocket = clientSocket;
	}

	@Override
	public void run()
	{
		System.out.println("Connection accepted on " + clientSocket.getPort());

		try {
			// Set up streams for client/server communication
			ObjectOutputStream responseStream = 
					new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream requestStream = 
					new ObjectInputStream(clientSocket.getInputStream());
			
			// Receive and execute operations
			while (true)
			{
				GameOperation op = (GameOperation) requestStream.readObject();
				op.execute(this.gameEngineServerStub, responseStream);
				System.out.println("Operation executed: " + op.toString());
			}

		} catch (IOException e) {
			System.out.println("IOException thrown");
			e.printStackTrace();
		} catch (ClassCastException | ClassNotFoundException e) {
			System.out.println("ClassCast/ClassNotFoundException thrown (streams)");
			e.printStackTrace();
		}
	}
}

