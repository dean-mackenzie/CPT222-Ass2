package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import au.edu.rmit.cpt222.view.TopMenuBar;
import au.edu.rmit.cpt222.view.TopToolBar;

public class TopController implements ActionListener {
	// Note: this controller handles buttons and pull-down menu,
	// but the functionality is duplicated so I don't need TopMenuBar
	private TopMenuBar tmbr;
	private TopToolBar ttb;

	private ArrayList<Integer> idsUsed;
		
	public TopController(TopToolBar ttb, TopMenuBar tmbr) {
		this.ttb = ttb;
		this.tmbr = tmbr;
		idsUsed = new ArrayList<Integer>();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if (e.getActionCommand().equals(TopToolBar.PLAY_TEXT)) {
			//Check if player exists, place bet, then roll
			if (this.ttb.getMainWindow().getMainController().checkPlayerExists()) {
				int bet = this.placeBet(); 
				if (bet > 0) {
					this.ttb.getMainWindow().getMainController().betAndRoll(bet);
				}
				else {
					;
				}
				
			}
		}
		else if (e.getActionCommand().equals(TopToolBar.ADDPLAYER_TEXT)) {
			// If 1 player exists, don't allow more (for Assignment 1)
			if (this.ttb.getMainWindow().getMainController().getNumberPlayers() == 1) {
				this.ttb.disableAddPlayerButton();
				this.ttb.getMainWindow().displayWarning("Only 1 player can be created.");
			}
			else {
				this.addPlayer();
			}
		}
		else if (e.getActionCommand().equals(TopToolBar.RESET_TEXT)) {
			// Reset = remove players and update UI
			this.ttb.getMainWindow().getMainController().reset();
			this.updatePlayerCombo();
		}
		else if (e.getActionCommand().equals(TopToolBar.QUIT_TEXT)) {
			this.close();
		}
	}
	
	public void close() {
		ttb.getMainWindow().setVisible(false);
		ttb.getMainWindow().dispose();
	}

	// This controls UI interaction only
	public void addPlayer() {
		boolean created = false;
		
		try {
			//Get details for new player
			String name = this.enterName();
			int points = enterPoints();
			if (points > 0) {
				String playerId = this.createUniqueId();
				
				// Call main controller to add player
				created = this.ttb.getMainWindow().getMainController()
						.addPlayer(playerId, name, points);	
			}
			else {
				;
			}
			
		} catch (NullPointerException e) {
			// Created already false
			return;
		}
		
		// Check player created
		if (created) {
			this.updatePlayerCombo();
		}
		else {
			this.ttb.getMainWindow()
			.displayWarning("Error creating player.  Please try again.");
		}
	}
	
	public String enterName() {
		String playerName = "";
		boolean valid = false;
		while (!valid) {
			try {
				playerName = (String)JOptionPane.showInputDialog(
					this.ttb.getMainWindow(),
					"Enter Player Name:",
					"Add Player",
					JOptionPane.PLAIN_MESSAGE);
				
				if (playerName.equals("")) {
					JOptionPane.showMessageDialog(this.ttb.getMainWindow(),
						    "Please enter a name.");
				   continue;
				}
			   
				if (playerName.length() > 20) {
				   throw new IllegalArgumentException();
			   }
			   
			   valid = true;
		   } catch (IllegalArgumentException e) {
			   JOptionPane.showMessageDialog(this.ttb.getMainWindow(),
					    "Please enter a name no longer than 20 characters.");
			   continue;
		   }
	   }
	   return playerName;
	}
	
	public int enterPoints() {
	   int points = 0;
	   boolean valid = false;
	  
	   // Loop until valid value entered
	   while (!valid) {
		   try {
			   points = Integer.parseInt(
					   JOptionPane.showInputDialog(
		                   this.ttb.getMainWindow(),
		                   "Enter points:",
		                   "Add Player",
		                   JOptionPane.PLAIN_MESSAGE));
			   
			   if (points == JOptionPane.CLOSED_OPTION) {
				   break;
			   }
			   else if (points < 1) {
				   throw new IllegalArgumentException();
			   }


			   valid = true;
		   } catch (NumberFormatException e) {
			   JOptionPane.showMessageDialog(this.ttb.getMainWindow(),
					"Please enter an integer.",
					"Incorrect value",
				    JOptionPane.ERROR_MESSAGE);
			   return points;
		   } catch (IllegalArgumentException e) {
			   JOptionPane.showMessageDialog(this.ttb.getMainWindow(),
				   "Please enter a value greater than zero.",
				   "Incorrect value",
				   JOptionPane.ERROR_MESSAGE);
			   return points;
		   }
	   }
	   return points;
	}
	
	public String createUniqueId() {
		// Create a unique player ID and store (so it's not re-used)
		int id = ThreadLocalRandom.current().nextInt(1, 100);
		for (int i = 0; i < idsUsed.size(); i++) {
			if(id == idsUsed.get(i)) {
				// Assign a new ID and restart loop
				id = ThreadLocalRandom.current().nextInt(1, 100);
				i = 0;
			}
		}
		
		// If unique, add to list
		idsUsed.add(id);
		String playerId = Integer.toString(id);
		return playerId;
	}
	
	// Re-use enterPoints() to prompt user for bet
	public int placeBet() {
		int bet = this.enterPoints();
		return bet;
	}
	
	public void updatePlayerCombo() {
		this.ttb.updatePlayerCombo(this.ttb.getMainWindow().
				getMainController().getPlayerNames());
	}
}
