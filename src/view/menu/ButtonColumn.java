package view.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class ButtonColumn extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonColumn(JLabel l, JButton[] b) {
		setLayout(new GridLayout(1+b.length, 0, 0, 10));
		setBackground(Color.cyan);
		add(l);
		for (int a=0; a<b.length; a++){
			add(b[a]);
		}
	}
	
	
}
