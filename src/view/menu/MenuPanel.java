package view.menu;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
//TODO MVC bättrra på gemensmma metoder
	public MenuPanel(String string, MenuButton[] buttons) {
		add(new ButtonColumn(new MenuLabel(string), buttons));
		setBackground(resources.MenuLookAndFeel.getMenuColor());
	}
	
}
