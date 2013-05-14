package view.menu.sub;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.HighScore;


import view.menu.MenuButton;

@SuppressWarnings("serial")
public class HighscorePanel extends SubMenuPanel {
	
	private static JPanel doSomething(){
		JPanel p = new JPanel();
		HighScore h = new HighScore();
		p.setBackground(Color.red);
		p.setLayout(new GridLayout(h.getHighScore().getLenght(), 0, 0, 10));

		for (int a=0; a<h.getHighScore().getLenght(); a++){
			p.add(new JLabel(
					a+1 + ". " +h.getHighScore().getScore(a) + "p. " + h.getHighScore().getName(a)));
		}
//		p.repaint();
		return p;
				
	}

	public HighscorePanel(MenuButton mainMenuButton) {
		super(mainMenuButton, doSomething());
		// TODO Auto-generated constructor stub
	}

}
