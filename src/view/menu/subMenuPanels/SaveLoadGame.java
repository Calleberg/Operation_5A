package view.menu.subMenuPanels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Translator;

import view.menu.MenuButton;

/**
 * Panel to save and load the game.
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class SaveLoadGame extends SubMenuPanel{
	
	/**
	 * 
	 * @param button the buttons to be added
	 * @param bol <code>true<code> if the user is allowed to save.
	 */
	public SaveLoadGame(String timeSaved, MenuButton[] button, boolean bol) {
		super(getText(bol), getPanel(timeSaved), button);
		button[1].setEnabled(bol);
	}
	private static String getText(boolean bol) {
		if (bol){
			return Translator.getString("loadSave");
		} else {
			return Translator.getString("load");
		}
	}
	private static JPanel getPanel(String timeSaved) {
		JPanel panel = new JPanel();
		JLabel l = new JLabel(Translator.getString("lastGameSavedAt") + ": \n" + timeSaved);
		l.setFont(resources.MenuLookAndFeel.getLargeFont());
		panel.add(l);
		return panel;
	}

}