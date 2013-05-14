package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuButton extends JButton implements MouseListener, ActionListener{

	public MenuButton(String s){
		super(s);
		setBackground(resources.Menu.getButtonColor());
		setFont(resources.Menu.getButtonFont());
		setBorder(resources.Menu.getButtoBorder());
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
			setBorder(resources.Menu.getButtonHighlightedBorder());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (this.isEnabled()){
			setBorder(resources.Menu.getButtoBorder());
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
			setBorder(resources.Menu.getButtoBorder());
		}
	}
	
}
