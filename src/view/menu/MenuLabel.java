package view.menu;

import javax.swing.JLabel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuLabel extends JLabel {

	public MenuLabel(String s) {
//		super(s);
		setText(s);
//		setAlignmentY(CENTER_ALIGNMENT);
		setFont(resources.MenuLookAndFeel.getLargeFont());
	}
}
