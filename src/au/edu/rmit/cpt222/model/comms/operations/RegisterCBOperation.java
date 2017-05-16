package au.edu.rmit.cpt222.model.comms.operations;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.comms.HostDetails;

public class RegisterCBOperation extends AbstractGameOperation implements Serializable, GameOperation {
	HostDetails details;

	public RegisterCBOperation(String hostName, int port) {
		details = new HostDetails(hostName, port);
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, ObjectOutputStream requestStream) {
		serverStub.addGameEngineCallback(details);
		// TODO: does this need to pass back a UNique ID?
	}
}
