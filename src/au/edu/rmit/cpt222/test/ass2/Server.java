package au.edu.rmit.cpt222.test.ass2;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public class Server {
	
	// don't think we need this: clients will use it, but server is already on its own machine
	//public static final String SERVER_HOST = "127.0.0.1";
	public static final int SERVER_PORT = 8888;

	public static void main(String[] args) {
		int port = SERVER_PORT;
		
		if (args.length == 1) {
			port = Integer.valueOf(args[0]);
		}
		
		new GameEngineServerStub(port);

	}

}
