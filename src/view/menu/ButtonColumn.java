package view.menu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class ButtonColumn extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonColumn(MenuLabel l, JButton[] b) {
		setLayout(new GridLayout(0, 1, resources.Menu.getGap(), resources.Menu.getGap()));
		setBackground(resources.Menu.getMenuColor());
		
		
		add(l);
		//TODO centrera texten ovan
		for (int a=0; a<b.length; a++){
			add(b[a]);
		}
	}
	public ButtonColumn(String s, JButton[] b) {
		this(new MenuLabel(s) , b);
	}
	
	
}
