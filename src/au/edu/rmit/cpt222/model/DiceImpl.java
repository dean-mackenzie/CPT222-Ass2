package au.edu.rmit.cpt222.model;

import java.util.concurrent.ThreadLocalRandom;

import au.edu.rmit.cpt222.model.interfaces.Dice;

public class DiceImpl implements Dice  {
	
	private int face;
	
	public DiceImpl(){
		face = ThreadLocalRandom.current().nextInt(1, 7);
	}
	
	public int getFace() {
		return this.face;
	}
	
	public void setFace(int currentFace) {
		this.face = currentFace;
	}

}
