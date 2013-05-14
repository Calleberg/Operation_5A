package view.menu.sub;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.menu.MenuButton;

@SuppressWarnings("serial")
public class SaveLoadGamePanel extends SubMenuPanel{
	 
	public SaveLoadGamePanel(MenuButton pauseMenuButton) {
		super(pauseMenuButton, createPanel());

		// TODO Auto-generated constructor stub
	}
	private static JPanel createPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.add(new JLabel("Load/SavePanel"));
		panel.setFont(resources.GameFont.getLargeFont());
		return panel;
	}

}

