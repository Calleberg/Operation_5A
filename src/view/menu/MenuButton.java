package view.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
/**
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class MenuButton extends JButton implements MouseListener{
	private final Color buttonColor = Color.YELLOW;
	private final Font font = new Font("Garamond",Font.BOLD, 48);
//	private final Border border = new SoftBevelBorder(
//			BevelBorder.RAISED, Color.RED, Color.RED, Color.RED, Color.RED);
//	private final Border border2 = new SoftBevelBorder(
//			5, Color.RED, Color.RED, Color.RED, Color.RED);
	private final Border border = new LineBorder(
			Color.YELLOW, 5, false);
	private final Border border2 = new LineBorder(
			Color.RED, 5, false);
	

	public MenuButton(String s){
		super(s);
		setBackground(buttonColor);
		setFont(font);
		setBorder(border);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Nothing
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setBorder(border2);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setBorder(border);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nothing		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nothing
	}
	
	

	
}
