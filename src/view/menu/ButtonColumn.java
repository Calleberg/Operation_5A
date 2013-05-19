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
	 * Creates a <code>JPanel<code> that holds a vertical line of buttons.
	 * @param l the title of the created line.
	 * @param b the buttons to add to the line.
	 */
	public ButtonColumn(MenuLabel l, JButton[] b) {
		setLayout(new GridLayout(0, 1, resources.MenuLookAndFeel.getGap(), resources.MenuLookAndFeel.getGap()));
		setBackground(resources.MenuLookAndFeel.getMenuColor());
		
		JPanel textPanel = new JPanel();
		add(textPanel);
		textPanel.add(l);
		textPanel.setBackground(resources.MenuLookAndFeel.getMenuColor());
		
		for (int a=0; a<b.length; a++){
			add(b[a]);
		}
	}
	/**
	 * Creates a <code>JPanel<code> that holds a vertical line of buttons.
	 * @param s the title of the created line.
	 * @param b the buttons to add to the line.
	 */
	public ButtonColumn(String s, JButton[] b) {
		this(new MenuLabel(s) , b);
	}
	
	
}
