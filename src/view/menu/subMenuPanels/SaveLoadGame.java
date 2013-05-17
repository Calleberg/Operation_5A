package view.menu.subMenuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;
import controller.IO.GameIO;

import view.menu.MenuButton;

/**
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class SaveLoadGame extends SubMenuPanel{
	
	/**
	 * 
	 * @param button
	 * @param bol save enabled / disabled.
	 */
	public SaveLoadGame(MenuButton button, boolean bol, GameController controller) {
		super(getText(bol), getPanel(), new MenuButton[]{getLoadButton(), getSaveButton(bol, controller), button});
	}
	private static MenuButton getLoadButton() {
		MenuButton b = new MenuButton("Load");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameIO.loadGame(resources.Save.getSavegamePath());
				System.out.println("Game Loaded - NOT IMPLEMENTED YET");
			}
		});
		return b;
	}

	private static MenuButton getSaveButton(boolean bol, final GameController controller) {
		MenuButton b = new MenuButton("Save");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameIO.saveGame(controller, resources.Save.getSavegamePath());
				System.out.println("Game saved - NOT IMPLEMENTED YET");
			}
		});
		b.setEnabled(bol);
		return b;
	}
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