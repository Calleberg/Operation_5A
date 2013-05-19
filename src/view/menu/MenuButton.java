package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import resources.MenuLookAndFeel;


/**
 * Should be used for all buttons in the system. Has the specified look and feel of the system.
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuButton extends JButton implements MouseListener, ActionListener{
	/**
	 * 
	 * @param s the text on the button.
	 */
	public MenuButton(String s){
		setText(s);
		setBackground(MenuLookAndFeel.getButtonColor());
		setFont(MenuLookAndFeel.getButtonFont());
		setBorder(MenuLookAndFeel.getButtonBorder());
		addMouseListener(this);
		addActionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Nothing
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (this.isEnabled()){
			setBorder(MenuLookAndFeel.getButtonHighlightedBorder());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (this.isEnabled()){
			setBorder(MenuLookAndFeel.getButtonBorder());
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nothing		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nothing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.isEnabled()){
			setBorder(MenuLookAndFeel.getButtonBorder());
		}
	}
	
}
