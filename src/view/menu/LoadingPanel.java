package view.menu;

import javax.swing.JPanel;
import javax.swing.JLabel;

import resources.MenuLookAndFeel;
import resources.Translator;
/**
 * Shows a screen while loading other components.
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class LoadingPanel extends JPanel{
	public LoadingPanel() {
		JLabel lblLoading = new JLabel(Translator.getString("loading"));
		add(lblLoading);
		lblLoading.setFont(MenuLookAndFeel.getLargeFont());
		setBackground(MenuLookAndFeel.getMenuColor());
	}
}
