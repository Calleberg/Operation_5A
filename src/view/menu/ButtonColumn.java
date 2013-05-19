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
	public ButtonColumn(String s, JButton[] b) {
		this(new MenuLabel(s) , b);
	}
	
	
}
