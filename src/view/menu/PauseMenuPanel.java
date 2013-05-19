package view.menu;

import javax.swing.JPanel;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class PauseMenuPanel extends JPanel{
	
	public PauseMenuPanel(String string, MenuButton[] buttons) {
		add(new ButtonColumn(new MenuLabel(string), buttons));
		setBackground(resources.MenuLookAndFeel.getMenuColor());
	}

	public PauseMenuPanel(String string, MenuButton[] pauseMenuButtons,
			JPanel gamePanel) {
		this(string, pauseMenuButtons);
		// TODO Auto-generated constructor stub
		//TODO lägg pause menyn över spelvärlden
	}
	
//	public PauseMenuPanel(String string, MenuButton[] buttons,
//			JPanel gamePanel) {
////		this(string, buttons);
//		
//		JLayeredPane layeredPane = new JLayeredPane();
////		layeredPane.add(gamePanel,0);
//		layeredPane.add(new ButtonColumn(new MenuLabel(string), buttons), 1);
//		add(layeredPane);
////		layeredPane.requestFocus();
////		layeredPane.repaint();
//		// TODO Auto-generated constructor stub
//		//TODO lägg pause menyn över spelvärlden
//	}
	
}
