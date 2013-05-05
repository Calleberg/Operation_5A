package view.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class MenuButtonColumn extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuButtonColumn(JLabel l, JButton[] b) {
		setLayout(new GridLayout(1+b.length, 0, 0, 0));
		add(l);
		for (int a=0; a<b.length; a++){
			add(b[a]);
			//TODO
		}
	}
	
}
