package view.menu;

import java.awt.Color;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private MainMenuPanel() {
		setBackground(Color.GREEN);

		
	}

	public MainMenuPanel(String string, MenuButton[] buttons) {
		this();
		add(new ButtonColumn(new MenuLabel(string), buttons));
	}

}
