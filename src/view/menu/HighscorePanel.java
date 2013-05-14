package view.menu;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HighscorePanel extends JPanel {
	
	private static String[] getHighscoreList(){
		String s[] = new String[5];
		s[0]="Vidare 1337";
		s[0]="Vidare 1000";
		s[0]="Vidare 805";
		s[0]="Vidare 600";
		s[0]="Vidare 400";
		return s;
		
	}
	
	public HighscorePanel(){
		String[] s=getHighscoreList();
		setBackground(Color.red);
		setLayout(new GridLayout(s.length, 0, 0, 10));

		for (int a=0; a<s.length; a++){
			add(new JLabel(s[a]));
		}
		this.repaint();
			
				
	}

	public HighscorePanel(MenuButton mainMenuButton) {
		// TODO Auto-generated constructor stub
	}

}
