package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomBars extends JPanel {
		
	private MainWindow mw;
	
	private JPanel status;
	private JPanel winLoss;
	
	private JLabel statusText;
	private JLabel winLossText;
	
	public BottomBars(MainWindow mw) {
		this.mw = mw;
		createBottomBars();
	}
	
	public void createBottomBars() {
		//Add and position panels
		this.setLayout(new BorderLayout());
		
		status = new JPanel();
		winLoss = new JPanel();
				
		// Configure each sub panel
		status.setBackground(Color.LIGHT_GRAY);
		status.setPreferredSize(new Dimension((int) (mw.getWidth() / 2), 30));
		status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusText = new JLabel("Game Result: none played");
		status.add(statusText);
		
		winLoss.setBackground(Color.LIGHT_GRAY);
		winLoss.setPreferredSize(new Dimension((int) (mw.getWidth() / 2), 30));
		winLoss.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// Hardcoded 0 for initial paint
		winLossText = new JLabel("Bet: 0, Points: 0");
		winLoss.add(winLossText);
		
		this.add(status, BorderLayout.LINE_START);
		this.add(winLoss,BorderLayout.LINE_END);
	}
	
	public void updatePoints(int bet, int points) {
		winLossText.setText("Bet: " + bet + ", Updated Points: " + points);
	}
	
	public void updateGameStatus(String resultText, int bet, int points) {
		statusText.setText("Game Result: " + resultText);
		this.updatePoints(bet, points);
	}
}
