package view.menu.sub;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.HighScoreModel;


import view.menu.MenuButton;

@SuppressWarnings("serial")
public class HighscorePanel extends SubMenuPanel {
	
	private static JPanel doSomething(){
		JPanel p = new JPanel();
		HighScoreModel h = new HighScoreModel();
		
		p.setLayout(new GridLayout(11, 0, 0, 10));

		for (int a=0; a<10 && a<h.getLenght(); a++){
			p.add(new JLabel(
					a+1 + ". " +h.getScore(a) + "p. " + h.getName(a)));
		}

		return p;
				
	}

	public HighscorePanel(MenuButton mainMenuButton) {
		super(mainMenuButton, doSomething());
		super.setBackground(Color.red);
		// TODO Auto-generated constructor stub
	}

}
