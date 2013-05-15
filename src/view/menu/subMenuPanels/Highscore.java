package view.menu.subMenuPanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.HighScoreModel;


import view.menu.MenuButton;

/**
 * 
 * @author Vidar Eriksson
 *
 */
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
			JLabel l = new JLabel(
					a+1 + ". " +h.getScore(a) + "p. " + h.getName(a));
			l.setFont(resources.Menu.getLargeFont());
			p.add(l);
		}

		return p;
		// TODO Auto-generated constructor stub
	}
	


}
