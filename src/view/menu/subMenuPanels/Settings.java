package view.menu.subMenuPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.menu.MenuButton;

/**
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class Settings extends SubMenuPanel {

	public Settings(MenuButton button) {
		super("Settings", getPanel(), button);
	}

	private static JPanel getPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();
		p.add(new JLabel("Detta �r settings panel"));
		return p;
	}

}
