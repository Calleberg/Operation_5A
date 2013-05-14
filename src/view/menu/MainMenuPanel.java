package view.menu;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {

	public MainMenuPanel(String string, MenuButton[] buttons) {
		add(new ButtonColumn(new MenuLabel(string), buttons));
		setBackground(resources.Menu.getMenuColor());
	}

}
