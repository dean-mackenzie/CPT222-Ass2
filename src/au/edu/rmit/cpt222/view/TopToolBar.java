package au.edu.rmit.cpt222.view;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import au.edu.rmit.cpt222.controller.TopController;

@SuppressWarnings("serial")
public class TopToolBar extends JPanel {
	
	public static final String PLAY_TEXT = "Play";
	public static final String ADDPLAYER_TEXT = "Add Player";
	public static final String RESET_TEXT = "Reset";
	public static final String QUIT_TEXT = "Quit";
		
	private MainWindow mw;
	private TopController tc;
	
	private JToolBar toolBar;
	private JButton addPlayer;
	private JButton play;
	private JButton quit;
	private JButton reset;
	private JComboBox<String> playerCombo;
	
	
	public TopToolBar(MainWindow mw, TopController tc) {
		this.mw = mw;
		this.tc = tc;
		createTopMenu();
	}
	
	public void createTopMenu() {
		this.toolBar = new JToolBar();
		this.toolBar.setFloatable(false);
		
		// Create tool bar components
		this.playerCombo = new JComboBox<String>();
		this.addPlayer = new JButton(ADDPLAYER_TEXT);
		this.play = new JButton(PLAY_TEXT);
		this.reset = new JButton(RESET_TEXT);
		this.quit = new JButton(QUIT_TEXT);
		
		// Add components
		toolBar.add(playerCombo);
		toolBar.add(addPlayer);
		toolBar.add(play);
		toolBar.add(reset);
		toolBar.add(quit);

		this.add(toolBar);
		
		// Add listeners
		this.addPlayer.setActionCommand(ADDPLAYER_TEXT);
		this.play.setActionCommand(PLAY_TEXT);
		this.reset.setActionCommand(RESET_TEXT);
		this.quit.setActionCommand(QUIT_TEXT);
	}
	
	public void disableAddPlayerButton() {
		this.addPlayer.setEnabled(false);
	}
	
	public MainWindow getMainWindow() {
		return mw;
	}
	
	public void updatePlayerCombo(List<String> playerNames) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.playerCombo.getModel();
		model.removeAllElements();
		
		for (String name : playerNames) {
			model.addElement(name);
		}
		
		this.playerCombo.setModel(model);
	}
	
	// Done only once this and TopController initialised
	public void setController(TopController tc) {
		this.tc = tc;
		
		this.addPlayer.addActionListener(this.tc);
		this.play.addActionListener(this.tc);
		this.reset.addActionListener(this.tc);
		this.quit.addActionListener(this.tc);
	}

}
