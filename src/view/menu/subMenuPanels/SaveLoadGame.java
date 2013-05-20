package view.menu.subMenuPanels;

import inputOutput.GameIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;
import controller.MenuController;

import view.menu.MenuButton;

/**
 * not done mvc
 * Panel to save and load the game.
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class SaveLoadGame extends SubMenuPanel{
	
	/**
	 * 
	 * @param button the buttons to be added
	 * @param bol write?
	 */
	public SaveLoadGame(MenuButton[] button, boolean bol) {
		super(getText(bol), getPanel(), button);
		
//		super(getText(bol), getPanel(), new MenuButton[]{getLoadButton(), getSaveButton(bol, controller), button});
	}
//	private static MenuButton getLoadButton() {
//		MenuButton b = new MenuButton("Load");
//		b.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				MenuController.startGame(GameIO.loadGame());
//			}
//		});
//		return b;
//	}
//
//	private static MenuButton getSaveButton(boolean bol, final GameController controller) {
//		MenuButton b = new MenuButton("Save");
//		b.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				GameIO.saveGame(controller.getGameModel());
//			}
//		});
//		b.setEnabled(bol);
//		return b;
//	}
	private static String getText(boolean bol) {
		if (bol){
			return "Load / Save Game";
		} else {
			return "Load Game";
		}
	}
	private static JPanel getPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.add(new JLabel("Load/SavePanel  A list of saved games should be visible"));
		return panel;
	}

}