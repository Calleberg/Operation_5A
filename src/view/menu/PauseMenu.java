package view.menu;

import java.awt.Color;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class PauseMenu extends JPanel{
	
	private PauseMenu() {
		setBackground(Color.BLUE);

		
	}

	public PauseMenu(String string, MenuButton[] buttons) {
		this();
		add(new ButtonColumn(new MenuLabel(string), buttons));
	}

	public PauseMenu(String string, MenuButton[] pauseMenuButtons,
			JPanel gamePanel) {
		this(string, pauseMenuButtons);
		// TODO Auto-generated constructor stub
		//TODO lägg pause menyn över spelvärlden
	}

}
