package view.menu;

import javax.swing.JLabel;
/**
 * Has the specified look and feel of the system.
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuLabel extends JLabel {

	public MenuLabel(String s) {
		setText(s);
		setFont(resources.MenuLookAndFeel.getLargeFont());
	}
}
