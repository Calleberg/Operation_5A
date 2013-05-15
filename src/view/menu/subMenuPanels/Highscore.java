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
		
		p.setLayout(new GridLayout(0, 1, 0, resources.Menu.getGap()));

		for (int a=0; a<10 && a<h.getLenght(); a++){
			String s = a+1 + ". "; 
			if (h.getScore(a).length() < 1){
				s+="No score ";
			} else {
				s+=h.getScore(a) + "p ";
			}
			
			if (h.getName(a).length() < 1){
				s+="No name";
			} else {
				s+=h.getName(a) + "";
			}
			
			JLabel l = new JLabel(s);
			l.setFont(resources.Menu.getLargeFont());
			p.add(l);
		}

		return p;
		// TODO Auto-generated constructor stub
	}
	


}
