package view.menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * not done yet.
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class BooleanPopupMenu extends JFrame {
	private boolean result = false;
	private boolean hasGotResult = false;
	public BooleanPopupMenu(String text, String button1, String button2) {
		
		JPanel p = new JPanel();
		p.setBackground(resources.MenuLookAndFeel.getPopupColor());
		
		p.setLayout(new GridLayout(1, 0, resources.MenuLookAndFeel.getGap(), resources.MenuLookAndFeel.getGap()));
		p.add(new BooleanButton(button1,true));
		p.add(new BooleanButton(button2,false));
		p.setBorder(resources.MenuLookAndFeel.getPopupBorder());
		p.setVisible(true);
		
//		this.setBackground(Color.magenta);
//		this.setUndecorated(true);
		this.add(p);
		this.setAlwaysOnTop(true);	
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.pack();		
//		this.setSize(1000, 800);
		this.setIconImage(resources.MenuLookAndFeel.setPopupMenuIcon());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
//		this.requestFocus();
//		this.repaint();
		
		
		p.setVisible(true);
		p.requestFocus();
		p.repaint();
		p.paintImmediately(p.getBounds());
	}
	
	private void removePopup(){
		this.setVisible(false);
	}
	
	public boolean hasGotResult(){
		return hasGotResult;
	}
	
	public boolean getResult(){
		while (!hasGotResult){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private class BooleanButton extends MenuButton {
		private boolean b;
		public BooleanButton(String s, boolean b) {
			super(s);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			result=b;
			hasGotResult=true;
			removePopup();			
		}
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
