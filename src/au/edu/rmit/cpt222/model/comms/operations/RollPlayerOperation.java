package au.edu.rmit.cpt222.model.comms.operations;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class RollPlayerOperation extends AbstractGameOperation {
	
	private Player player;
	private int initialDelay;
	private int finalDelay;
	private int delayIncrement;
	

	public RollPlayerOperation(Player player, int initialDelay, 
			int finalDelay, int delayIncrement) {
		this.player = player;
		this.initialDelay = initialDelay;
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
	}
	
	@Override
	public void execute(GameEngineServerStub serverStub, 
			ObjectOutputStream requestStream) {
		serverStub.getEngine().rollPlayer(
				this.player, this.initialDelay, this.finalDelay, this.delayIncrement);
		try {
			// rollPlayer() returns nothing, so just do a simple write back
			requestStream.writeObject(true);
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
