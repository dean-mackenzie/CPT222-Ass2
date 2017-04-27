package au.edu.rmit.cpt222.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import au.edu.rmit.cpt222.controller.TopController;

@SuppressWarnings("serial")
public class TopMenuBar extends JMenuBar {
		
	// TODO: should put these in mainwindow for both top menu and top menu bar
	public static final String PLAY_TEXT = "Play";
	public static final String ADDPLAYER_TEXT = "Add Player";
	public static final String RESET_TEXT = "Reset";
	public static final String QUIT_TEXT = "Quit";
	
	private MainWindow mw;
	private TopController tc;
	
	private JMenu menu;
	private JMenuItem play;
	private JMenuItem addPlayer;
	private JMenuItem reset;
	private JMenuItem quit;
	
	
	public TopMenuBar(MainWindow mw, TopController tc) {
		this.mw = mw;
		this.tc = tc;
		createTopMenuBar();
	}
	
	public void createTopMenuBar() {
		
		// Create menu
		this.menu = new JMenu("Options");
		//menu.setMnemonic(KeyEvent.
		
		this.add(menu);
		
		// Add menu items
		addPlayer = new JMenuItem(ADDPLAYER_TEXT);
		play = new JMenuItem(PLAY_TEXT);
		reset = new JMenuItem(RESET_TEXT);
		quit = new JMenuItem(QUIT_TEXT);
		
		this.menu.add(addPlayer);
		this.menu.add(play);
		this.menu.add(reset);
		this.menu.add(quit);
	}
	
	public MainWindow getMainWindow() {
		return mw;
	}
	
	// Do only once this and TopController initialised
	public void setController(TopController tc) {
		this.tc = tc;
		
		this.addPlayer.addActionListener(this.tc);
		this.play.addActionListener(this.tc);
		this.reset.addActionListener(this.tc);
		this.quit.addActionListener(this.tc);
	}
}
