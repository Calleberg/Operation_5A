package view.menu.sub;

import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel {
	public GameOverPanel(long time) {
		
		JLabel lblGameOverSorry = new JLabel("Game Over sorry...\n\nTime: " + time/1000 + "s");
		add(lblGameOverSorry);
	}
	

}
