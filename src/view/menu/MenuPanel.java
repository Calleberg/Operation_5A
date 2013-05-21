package view.menu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
//TODO MVC bättrra på gemensmma metoder
	/**
	 * 
	 * @param string the title of the menu.
	 * @param buttons the buttons in the panel.
	 */
	public MenuPanel(String string, MenuButton[] buttons) {
		this.add(createButtonColumn(new MenuLabel(string), buttons));
		this.setBackground(resources.MenuLookAndFeel.getMenuColor());
	}
	
	private JPanel createButtonColumn(MenuLabel l, JButton[] b) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1, resources.MenuLookAndFeel.getGap(), resources.MenuLookAndFeel.getGap()));
		p.setBackground(resources.MenuLookAndFeel.getMenuColor());
		
		JPanel textPanel = new JPanel();
		p.add(textPanel);
		textPanel.add(l);
		textPanel.setBackground(resources.MenuLookAndFeel.getMenuColor());
		
		for (int a=0; a<b.length; a++){
			p.add(b[a]);
		}
		return p;
	}
}
