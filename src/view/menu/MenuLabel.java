package view.menu;

import java.awt.Font;

import javax.swing.JLabel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuLabel extends JLabel {


	public MenuLabel(String string) {
		super(string);
		setFont(new Font("Garamond",Font.BOLD, 48));
	}
}
