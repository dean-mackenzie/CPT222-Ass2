//package au.edu.rmit.cpt222.controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;
//
//import javax.swing.JOptionPane;
//
//import au.edu.rmit.cpt222.view.TopMenuButtons;
//
//public class TopMenuController extends TopController implements ActionListener {
//	private TopController tc;
//	private TopMenuButtons tm;
//	private ArrayList<Integer> idsUsed;
//	
//	public TopMenuController(TopMenuButtons tm, TopController tc) {
//		this.tm = tm;
//		this.tc = tc;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		// Run according to which button was clicked
//		if(e.getActionCommand().equals(TopMenuButtons.PLAY_TEXT)) {
//			//Check if player exists, place bet, then roll
//			if (this.tm.getMainWindow().getMainController().checkPlayerExists()) {
//				int bet = this.placeBet();
//				this.tm.getMainWindow().getMainController().betAndRoll(bet);
//			}
//		}
//		else if (e.getActionCommand().equals(TopMenuButtons.ADDPLAYER_TEXT)) {
//			this.addPlayer();
//		}
//		else if (e.getActionCommand().equals(TopMenuButtons.QUIT_TEXT)) {
//			this.close();
//		}
//	}
//	
//	public void close() {
//		tm.getMainWindow().setVisible(false);
//		tm.getMainWindow().dispose();
//	}
//
//	// This controls UI interaction
//	// Actual logic of player entry in Maincontroller
//	public void addPlayer() {
//		String name = this.enterName();
//		// TODO: if Cancel or Close clicked, abort
//		int points = enterPoints();
//
//		// Create a unique player ID and store (so it's not re-used)
//		int id = ThreadLocalRandom.current().nextInt(1, 100);
//		for (int i = 0; i < idsUsed.size(); i++) {
//			if(id == idsUsed.get(i)) {
//				// Assign a new ID and restart loop
//				id = ThreadLocalRandom.current().nextInt(1, 100);
//				i = 0;
//			}
//		}
//		// If unique, add to list
//		idsUsed.add(id);
//		String playerId = Integer.toString(id);
//		
//		// Call main controller to implement logic of adding player
//		// TODO: returns something 
//		boolean created = this.tm.getMainWindow().getMainController().addPlayer(playerId, name, points);
//		
//		if (created) {
//			this.tm.updatePlayerCombo(this.tm.getMainWindow().getMainController().getPlayerNames());
//		}
//		else {
//			// TODO: if player not created
//		}
//	}
//	
//	public String enterName() {
//		String playerName = "";
//		boolean valid = false;
//		while (!valid) {
//			try {
//				playerName = (String)JOptionPane.showInputDialog(
//					this.tm.getMainWindow(),
//					"Enter Player Name:",
//					"Add Player",
//					JOptionPane.PLAIN_MESSAGE);
//			   
//			   if (playerName.length() > 20) {
//				   throw new IllegalArgumentException();
//			   }
//			   
//			   valid = true;
//		   } catch (IllegalArgumentException e) {
//			   JOptionPane.showMessageDialog(this.tm.getMainWindow(),
//					    "Please enter a name no longer than 20 characters.");
//			   continue;
//		   }
//	   }
//	   return playerName;
//	}
//	
//	public int enterPoints() {
//	   int points = 0;
//	   boolean valid = false;
//	   while (!valid) {
//		   try {
//			   String initialPoints = (String)JOptionPane.showInputDialog(
//	                   this.tm.getMainWindow(),
//	                   "Enter points:",
//	                   "Add Player",
//	                   JOptionPane.PLAIN_MESSAGE);
//			   points = Integer.parseInt(initialPoints);
//			   
//			   if (points < 1) {
//				   throw new IllegalArgumentException();
//			   }
//				   
//			   valid = true;
//		   } catch (NumberFormatException e) {
//			   JOptionPane.showMessageDialog(this.tm.getMainWindow(),
//				    "Please enter an integer.");
//			   continue;
//		   } catch (IllegalArgumentException e) {
//			   JOptionPane.showMessageDialog(this.tm.getMainWindow(),
//				   "Please enter a value greater than zero.");
//			   continue;
//		   }   
//	   }
//	   return points;
//	}
//	
//	// Can re-use enterPoints method to prompt user to enter bet
//	public int placeBet() {
//		int bet = this.enterPoints();
//		return bet;
//	}
//	
//}
