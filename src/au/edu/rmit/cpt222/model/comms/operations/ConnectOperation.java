package au.edu.rmit.cpt222.model.comms.operations;

import java.io.Serializable;

public class ConnectOperation implements Serializable {
	private String hostName;
	private int port;
	
	public ConnectOperation(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
	}

	public String getHostName() {
		return hostName;
	}

	public int getPort() {
		return port;
	}

}
