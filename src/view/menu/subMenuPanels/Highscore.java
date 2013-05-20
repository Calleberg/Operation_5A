package view.menu.subMenuPanels;

import inputOutput.HighScoreModel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;




import view.menu.MenuButton;

/**
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class HighScore extends SubMenuPanel {
	
	public HighScore(MenuButton mainMenuButton) {
		super("High Score", getPanel(), mainMenuButton);
	}
	
	private static JPanel getPanel(){
		JPanel p = new JPanel();
		HighScoreModel h = new HighScoreModel();
		
		p.setLayout(new GridLayout(0, 1, 0, resources.MenuLookAndFeel.getGap()));
		
		if (h.existsNoScore()){
			JLabel l = new JLabel("No High Score Exists");
			l.setFont(resources.MenuLookAndFeel.getLargeFont());
			p.add(l);
		} else {
			for (int a=0; a<h.getLenght(); a++){
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
				l.setFont(resources.MenuLookAndFeel.getLargeFont());
				p.add(l);
			}
		}

		return p;
		// TODO Auto-generated constructor stub
	}

}
