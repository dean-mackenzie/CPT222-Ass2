package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.TopController;

@SuppressWarnings("serial")
public class TopMenu extends JPanel {
	public static final String PLAY_TEXT = "Play";
	public static final String ADDPLAYER_TEXT = "Add Player";
	public static final String RESET_TEXT = "Reset";
	public static final String QUIT_TEXT = "Quit";
	
	MainWindow mw;
	TopMenuBar topMenuBar;
	TopToolBar topToolBar;
	TopController tc;
	
	public TopMenu(MainWindow mw) {
		this.mw = mw;
		createTopMenu();
	}
	
	public void createTopMenu() {
		this.setLayout(new BorderLayout());
		
		topMenuBar = new TopMenuBar(this.mw, this.tc);
		topToolBar = new TopToolBar(this.mw, this.tc);
		
		this.add(topMenuBar, BorderLayout.PAGE_START);
		this.add(topToolBar, BorderLayout.PAGE_END);
		
		// Set controller to components
		this.tc = new TopController(topToolBar, topMenuBar);
		topMenuBar.setController(tc);
		topToolBar.setController(tc);
	}
	
	public MainWindow getMainWindow() {
		return this.mw;
	}
	
	public TopController getTopController() {
		return tc;
	}
	
	

}
