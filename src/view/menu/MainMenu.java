package view.menu;

import java.awt.Color;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	private MainMenu() {
		setBackground(Color.GREEN);

		
	}

	public MainMenu(String string, MenuButton[] buttons) {
		this();
		add(new ButtonColumn(new MenuLabel(string), buttons));
	}

}
