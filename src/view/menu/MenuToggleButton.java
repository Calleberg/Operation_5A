package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import resources.MenuLookAndFeel;

/**
 * A button which can toggle but with custom design.
 * 
 * @author
 *
 */
@SuppressWarnings("serial")
public class MenuToggleButton extends JToggleButton implements MouseListener, ActionListener{ 

	/**
	 * Creates a new MenuToggleButton with the specified string as text to display.
	 * @param s the text to display.
	 */
	public MenuToggleButton(String s){
		setText(s);
		setBackground(MenuLookAndFeel.getButtonColor());
		setFont(MenuLookAndFeel.getButtonFont());
		setBorder(MenuLookAndFeel.getButtonBorder());
		addMouseListener(this);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.isEnabled()){
			setBorder(MenuLookAndFeel.getButtonBorder());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used here
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Not used here
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Not used here
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (this.isEnabled()){
			setBorder(MenuLookAndFeel.getButtonHighlightedBorder());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (this.isEnabled()){
			setBorder(MenuLookAndFeel.getButtonBorder());
		}
	}
}
