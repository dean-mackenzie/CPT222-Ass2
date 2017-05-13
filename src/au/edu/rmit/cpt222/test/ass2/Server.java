package au.edu.rmit.cpt222.test.ass2;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public class Server {

	public static final int SERVER_PORT = 8888;

	public static void main(String[] args) {
		int port = SERVER_PORT;
		
		if (args.length == 1) {
			port = Integer.valueOf(args[0]);
		}
		
		new GameEngineServerStub(port);

	}

}
