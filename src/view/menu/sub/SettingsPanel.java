package view.menu.sub;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.menu.MenuButton;

@SuppressWarnings("serial")
public class SettingsPanel extends SubMenuPanel {

	public SettingsPanel(MenuButton mainMenuButton) {
		super(mainMenuButton, getSettingsPanel());
		// TODO Auto-generated constructor stub
	}

	private static JPanel getSettingsPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();
		p.add(new JLabel("Detta är settings panel"));
		p.setBackground(Color.GRAY);
		return p;
	}

}
