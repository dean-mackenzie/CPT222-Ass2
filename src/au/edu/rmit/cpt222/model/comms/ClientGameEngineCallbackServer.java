package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import au.edu.rmit.cpt222.model.comms.callback.operations.CallbackOperation;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;

public class ClientGameEngineCallbackServer {

	private GameEngineCallback callback;
	private ObjectInputStream inputStream;
	//private ObjectOutputStream outputStream;
	private int socketPort = 0;	// capture dynamic port no. and send to server\
		
	
	public ClientGameEngineCallbackServer(GameEngineClientStub clientStub) {
		this.callback = clientStub.getCallback();
		this.startCallbackServer();
	}
	
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
//					ClientGameEngineCallbackServer.this.outputStream = 
//							new ObjectOutputStream(clientSocket.getOutputStream());
					ClientGameEngineCallbackServer.this.inputStream = 
							new ObjectInputStream(clientSocket.getInputStream());
					
					// Loop to handle multiple requests from client
					while (!socket.isClosed()) {
						try {
							// This acts like server, executing callback methods
							// Have to know which specific method to call
							CallbackOperation op = 
									(CallbackOperation) ClientGameEngineCallbackServer.this.inputStream.readObject();
							op.execute(ClientGameEngineCallbackServer.this.callback);
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
