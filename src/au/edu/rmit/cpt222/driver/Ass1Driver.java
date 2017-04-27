package au.edu.rmit.cpt222.driver;

import javax.swing.SwingUtilities;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.comms.GameEngineClientStub;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainWindow;

public class Ass1Driver {
	
	// you could always have an interface for the stub & store variables there
	public static final String SERVER_HOST = "127.0.0.1";
	public static final int SERVER_PORT = 8888;
	final static GameEngine engine = new GameEngineClientStub(SERVER_HOST, 
			SERVER_PORT);
	
	public static void main(String args[]) {
	     SwingUtilities.invokeLater(new Runnable() {
	          @Override
	           public void run() { 
	        	  // Initialise view
	        	  MainWindow mw = new MainWindow(engine);
	        	  mw.setVisible(true);
	           }
	     });
	}
}
