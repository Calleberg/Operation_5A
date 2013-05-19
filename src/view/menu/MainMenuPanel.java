package view.menu;

import javax.swing.JPanel;
/**
 * Not done yet MVC
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	//TODO gemensamma klasser
	/**
	 * 
	 * @param string The title of the mmain menu.
	 * @param buttons the buttons to be added
	 */
	public MainMenuPanel(String string, MenuButton[] buttons) {
		add(new ButtonColumn(new MenuLabel(string), buttons));
		setBackground(resources.MenuLookAndFeel.getMenuColor());
	}

}
