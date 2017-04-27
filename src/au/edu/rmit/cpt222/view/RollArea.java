package au.edu.rmit.cpt222.view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RollArea extends JPanel {
	private JPanel leftDice;
	private JPanel rightDice;
	
	private JLabel playerDice;
	private JLabel houseDice;
	
	private JLabel diceImgPnl1;
	private JLabel diceImgPnl2;
	private JLabel diceImgPnl3;
	private JLabel diceImgPnl4;
	private JLabel diceImgPnl5;
	private JLabel diceImgPnl6;
	private JLabel diceImgPnl7;
	private JLabel diceImgPnl8;
	private JLabel diceImgPnl9;
	private JLabel diceImgPnl10;
	private JLabel diceImgPnl11;
	private JLabel diceImgPnl12;
	private JLabel diceImgPnl13;
	private JLabel diceImgPnl14;
	private JLabel diceImgPnl15;
	private JLabel diceImgPnl16;
	private JLabel diceImgPnl17;
	private JLabel diceImgPnl18;
	private JLabel diceImgPnl19;
	private JLabel diceImgPnl20;
	private JLabel diceImgPnl21;
	private JLabel diceImgPnl22;
	private JLabel diceImgPnl23;
	private JLabel diceImgPnl24;
		
	private Image pDiceImg1;
	private Image pDiceImg2;
	private Image pDiceImg3;
	private Image pDiceImg4;
	private Image pDiceImg5;
	private Image pDiceImg6;
	private Image hDiceImg1;
	private Image hDiceImg2;
	private Image hDiceImg3;
	private Image hDiceImg4;
	private Image hDiceImg5;
	private Image hDiceImg6;
	
	// Map for each dices to avoid duplicate results problems (e.g. 2 and 2)
	private Map<Integer, JLabel> diceRoll1 = new HashMap<Integer, JLabel>();
	private Map<Integer, JLabel> diceRoll2 = new HashMap<Integer, JLabel>();
	private Map<Integer, JLabel> diceRoll3 = new HashMap<Integer, JLabel>();
	private Map<Integer, JLabel> diceRoll4 = new HashMap<Integer, JLabel>();
		
	public RollArea(MainWindow mw) {
		createRollArea();
	}
	
	public void createRollArea() {
		// Create components
		leftDice = new JPanel();
		rightDice = new JPanel();
		leftDice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rightDice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// Load images
		this.loadImages();
		
		// Used for initial display and reset
		this.resetRollArea();
	}
	
	public void loadImages() {
		try {
		    pDiceImg1 = ImageIO.read(new File("assets/1d-player.png"));
		    pDiceImg2 = ImageIO.read(new File("assets/2d-player.png"));
		    pDiceImg3 = ImageIO.read(new File("assets/3d-player.png"));
		    pDiceImg4 = ImageIO.read(new File("assets/4d-player.png"));
		    pDiceImg5 = ImageIO.read(new File("assets/5d-player.png"));
		    pDiceImg6 = ImageIO.read(new File("assets/6d-player.png"));
		    
		    hDiceImg1 = ImageIO.read(new File("assets/1d-house.png"));
		    hDiceImg2 = ImageIO.read(new File("assets/2d-house.png"));
		    hDiceImg3 = ImageIO.read(new File("assets/3d-house.png"));
		    hDiceImg4 = ImageIO.read(new File("assets/4d-house.png"));
		    hDiceImg5 = ImageIO.read(new File("assets/5d-house.png"));
		    hDiceImg6 = ImageIO.read(new File("assets/6d-house.png"));

		} catch (IOException e) {
			//TODO: default to numbers?
		}
		
		// Load dice into JLabels for display
		// 1 set per dice avoids duplicate component problems
		diceImgPnl1 = new JLabel(new ImageIcon(pDiceImg1));
		diceImgPnl2 = new JLabel(new ImageIcon(pDiceImg2));
		diceImgPnl3 = new JLabel(new ImageIcon(pDiceImg3));
		diceImgPnl4 = new JLabel(new ImageIcon(pDiceImg4));
		diceImgPnl5 = new JLabel(new ImageIcon(pDiceImg5));
		diceImgPnl6 = new JLabel(new ImageIcon(pDiceImg6));
		
		diceImgPnl7 = new JLabel(new ImageIcon(pDiceImg1));
		diceImgPnl8 = new JLabel(new ImageIcon(pDiceImg2));
		diceImgPnl9 = new JLabel(new ImageIcon(pDiceImg3));
		diceImgPnl10 = new JLabel(new ImageIcon(pDiceImg4));
		diceImgPnl11 = new JLabel(new ImageIcon(pDiceImg5));
		diceImgPnl12 = new JLabel(new ImageIcon(pDiceImg6));
		
		diceImgPnl13 = new JLabel(new ImageIcon(hDiceImg1));
		diceImgPnl14 = new JLabel(new ImageIcon(hDiceImg2));
		diceImgPnl15 = new JLabel(new ImageIcon(hDiceImg3));
		diceImgPnl16 = new JLabel(new ImageIcon(hDiceImg4));
		diceImgPnl17 = new JLabel(new ImageIcon(hDiceImg5));
		diceImgPnl18 = new JLabel(new ImageIcon(hDiceImg6));
		
		diceImgPnl19 = new JLabel(new ImageIcon(hDiceImg1));
		diceImgPnl20 = new JLabel(new ImageIcon(hDiceImg2));
		diceImgPnl21 = new JLabel(new ImageIcon(hDiceImg3));
		diceImgPnl22 = new JLabel(new ImageIcon(hDiceImg4));
		diceImgPnl23 = new JLabel(new ImageIcon(hDiceImg5));
		diceImgPnl24 = new JLabel(new ImageIcon(hDiceImg6));
		
		// Add images to HashMap for mapping
		diceRoll1.put(1, diceImgPnl1);
		diceRoll1.put(2, diceImgPnl2);
		diceRoll1.put(3, diceImgPnl3);
		diceRoll1.put(4, diceImgPnl4);
		diceRoll1.put(5, diceImgPnl5);
		diceRoll1.put(6, diceImgPnl6);
		
		diceRoll2.put(1, diceImgPnl7);
		diceRoll2.put(2, diceImgPnl8);
		diceRoll2.put(3, diceImgPnl9);
		diceRoll2.put(4, diceImgPnl10);
		diceRoll2.put(5, diceImgPnl11);
		diceRoll2.put(6, diceImgPnl12);
		
		diceRoll3.put(1, diceImgPnl13);
		diceRoll3.put(2, diceImgPnl14);
		diceRoll3.put(3, diceImgPnl15);
		diceRoll3.put(4, diceImgPnl16);
		diceRoll3.put(5, diceImgPnl17);
		diceRoll3.put(6, diceImgPnl18);
		
		diceRoll4.put(1, diceImgPnl19);
		diceRoll4.put(2, diceImgPnl20);
		diceRoll4.put(3, diceImgPnl21);
		diceRoll4.put(4, diceImgPnl22);
		diceRoll4.put(5, diceImgPnl23);
		diceRoll4.put(6, diceImgPnl24);
	}
	
	public void updatePlayerDice(int dice1, int dice2) {
		// Remove existing dice images
		try {
			leftDice.removeAll();
		} catch (NullPointerException e) {
			;
		}

		// Add updated dice images
		leftDice.add(diceRoll1.get(dice1));
		leftDice.add(diceRoll2.get(dice2));
		leftDice.revalidate();
	}
	
	public void updateHouseDice(int dice1, int dice2) {
		// Remove existing dice images
		try {
			rightDice.removeAll();
		} catch (NullPointerException e) {
			;
		}

		// Add updated dice images
		rightDice.add(diceRoll3.get(dice1));
		rightDice.add(diceRoll4.get(dice2));
		rightDice.revalidate();
	}
	
	public void resetRollArea() {
		// Remove anything already in pane
		leftDice.removeAll();
		rightDice.removeAll();

		// Set text
		playerDice = new JLabel("Play to see player dice");
		houseDice = new JLabel("Play to see house dice");
		
		// Add components
		leftDice.add(playerDice);
		rightDice.add(houseDice);
		
		this.add(leftDice);
		this.add(rightDice);
	}
}
