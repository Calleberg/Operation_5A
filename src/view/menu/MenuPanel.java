package view.menu;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	public MenuPanel(String string, MenuButton[] buttons) {
		add(new ButtonColumn(new MenuLabel(string), buttons));
		setBackground(resources.MenuLookAndFeel.getMenuColor());
	}
	
}
