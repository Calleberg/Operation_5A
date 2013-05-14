package view.menu.sub;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.menu.MenuButton;

@SuppressWarnings("serial")
public class SaveLoadGamePanel extends SubMenuPanel{
	private static SaveLoadGamePanel panel = null; 
	public SaveLoadGamePanel(MenuButton pauseMenuButton) {
		super(pauseMenuButton, panel);
		panel.add(new JLabel("Load/SavePanel"));
		panel.setFont(resources.GameFont.getLargeFont());
		// TODO Auto-generated constructor stub
	}

}

