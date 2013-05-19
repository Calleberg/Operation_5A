package view.menu;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PopupMenu extends JOptionPane {
	public PopupMenu(String s, String s2) {
		setBackground(resources.Menu.getPopupColor());
	}

}


//int optionButton = JOptionPane.YES_NO_OPTION;
//optionButton = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", 
//		"Confirm", optionButton);
//if(optionButton == JOptionPane.YES_OPTION){
//	exitGame();
//}else{
//	showPauseMenu();
//}
