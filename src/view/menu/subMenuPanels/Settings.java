package view.menu.subMenuPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.menu.MenuButton;

@SuppressWarnings("serial")
public class Settings extends SubMenuPanel {

	public Settings(MenuButton button) {
		super("Settings", getSettingsPanel(), button);
	}

	private static JPanel getSettingsPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();
		p.add(new JLabel("Detta är settings panel"));
		return p;
	}

}
