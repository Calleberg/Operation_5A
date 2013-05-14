package view.menu.sub;

import javax.swing.JPanel;
import javax.swing.JLabel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class LoadingPanel extends JPanel{
	
	public LoadingPanel() {
		
		JLabel lblLoading = new JLabel("Loading...");
		add(lblLoading);
		lblLoading.setFont(resources.GameFont.getLargeFont());
	}

}
