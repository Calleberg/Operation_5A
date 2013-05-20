package view.menu;

import javax.swing.JPanel;
import javax.swing.JLabel;

import resources.MenuLookAndFeel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class LoadingPanel extends JPanel{
	
	public LoadingPanel() {
		
		JLabel lblLoading = new JLabel("Loading...");
		add(lblLoading);
		lblLoading.setFont(MenuLookAndFeel.getLargeFont());
	}

}
