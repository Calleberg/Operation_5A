package view.menu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MenuModel.MenuActions;

@SuppressWarnings("serial")
public class PauseMenu extends JPanel{
	
	public PauseMenu() {
//		add(new MenuButtonColumn(MenuButtonColumn.MenuType.PAUSE_MENU));
		setBackground(Color.BLUE);
	}

	public PauseMenu(String string, MenuActions[] menuActions) {
		// TODO Auto-generated constructor stub
	}

}
