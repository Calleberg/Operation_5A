package view.menu.subMenuPanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.HighScoreModel;


import view.menu.MenuButton;

@SuppressWarnings("serial")
public class Highscore extends SubMenuPanel {
	
	public Highscore(MenuButton mainMenuButton) {
		super("Highscore", getPanel(), mainMenuButton);
	}
	
	private static JPanel getPanel(){
		JPanel p = new JPanel();
		HighScoreModel h = new HighScoreModel();
		
		p.setLayout(new GridLayout(11, 0, 0, 10));

		for (int a=0; a<10 && a<h.getLenght(); a++){
			p.add(new JLabel(
					a+1 + ". " +h.getScore(a) + "p. " + h.getName(a)));
		}

		return p;
		// TODO Auto-generated constructor stub
	}
	


}
