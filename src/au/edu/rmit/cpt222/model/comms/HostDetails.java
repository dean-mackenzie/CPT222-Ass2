package au.edu.rmit.cpt222.model.comms;

import java.io.Serializable;

//TODO: do I need this now - see ConnectOperation (and rename it)
public class HostDetails implements Serializable {
	private String hostName;
	private int port;
	
	public HostDetails(String hostName, int port) {
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
