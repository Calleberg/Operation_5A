package view.menu;

import javax.swing.JPanel;
import javax.swing.JLabel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class LoadingScreen extends JPanel{
	
	public LoadingScreen() {
		
		JLabel lblLoading = new JLabel("Loading...");
		add(lblLoading);
		lblLoading.setFont(resources.GameFont.getLargeFont());
	}

}
