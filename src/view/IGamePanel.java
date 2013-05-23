package view;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Interface for a view of the game. The implemented classes should be able to loop a paint
 * method.
 * 
 * @author Martin Calleberg
 *
 */
@SuppressWarnings("serial")
public abstract class IGamePanel extends JPanel {

	public static final String MOUSE_INPUT = "msInput";
	
	/**
	 * Temporarily stops the view from painting the game.
	 */
	public abstract void pauseThread();
	
	/**
	 * Starts the painting loop again.
	 */
	public abstract void resumeThread();

	/**
	 * Stops the view from painting the game. Note: this means it cannot even be resumed later.
	 */
	public abstract void stopThread();
	
	@Override
	public void paintComponent(Graphics g) {
		this.render(g);
	}
	
	/**
	 * Repaints the game.
	 * @param g the graphics instance to draw to.
	 */
	public abstract void render(Graphics g);
}
