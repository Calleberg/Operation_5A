package view.menu;

import javax.swing.JLabel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuLabel extends JLabel {

	public MenuLabel(String string) {
		super(string);
		setFont(resources.GameFont.getLargeFont());
	}
}
