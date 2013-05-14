package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.GameController;

/**
 * A GUI object which will render a score.
 * 
 * @author 
 *
 */
public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameController ctrl;
	
	/**
	 * Creates a new instance of the object.
	 * @param ctrl the controller which holds the high score.
	 */
	public ScorePanel(GameController ctrl) {
		super();
		this.setPreferredSize(new Dimension(100, 100));
		this.ctrl = ctrl;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 255));
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		g.drawString("Score: " + ctrl.getGameScore(), 10, 10);
	}
}
