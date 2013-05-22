package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import resources.MenuLookAndFeel;

@SuppressWarnings("serial")
public class MenuToggleButton extends JToggleButton implements MouseListener, ActionListener{ 

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
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
