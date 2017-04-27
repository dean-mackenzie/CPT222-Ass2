package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.Dice;
import au.edu.rmit.cpt222.model.interfaces.DicePair;

public class DicePairImpl implements DicePair {
	
	//Variables
	private Dice dice1;
	private Dice dice2;
	
	//Constructor
	public DicePairImpl() {
		this.dice1 = new DiceImpl();
		this.dice2 = new DiceImpl();
	};
		
	//Methods
	public Dice getDice1() {
		return dice1;
	}

	public Dice getDice2() {
		return dice2;
	}

	public int getTotalScore() {
		return (dice1.getFace() + dice2.getFace());
	}
}
