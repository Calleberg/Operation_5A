package controller;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds which keys have been pressed.
 * Supports keyboard and mouse button input.
 *
 * @author Calleberg
 *
 */
public class Input implements KeyListener, MouseListener {

	private List<Integer> keys = new ArrayList<Integer>();
	private List<Integer> mouseButtons = new ArrayList<Integer>();
	
	/**
	 * Sets which container to listen to.
	 * @param container the container to get input from.
	 */
	public void setContainer(Container container) {
		this.reset();
		container.addKeyListener(this);
		container.addMouseListener(this);
	}
	
	/**
	 * Resets the specified key. 
	 * Does the same as releasing a key.
	 * @param e the key to reset.
	 */
	public void resetKey(int key) {
		this.keys.remove(new Integer(key));
	}
	
	/**
	 * Gives <code>true</code> if the specified key is pressed.
	 * @param key the key to check.
	 * @return <code>true</code> if the specified key is pressed.
	 */
	public boolean isPressed(int key) {
		return this.keys.contains(key);
	}
	
	/**
	 * Resets the instance and sets all the keys to false.
	 */
	public void reset() {
		keys.clear();
		mouseButtons.clear();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!this.isPressed(e.getKeyCode())) {
			this.keys.add(new Integer(e.getKeyCode()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys.remove(new Integer(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Not used
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Not used
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.mouseButtons.add(new Integer(arg0.getButton()));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.mouseButtons.remove(new Integer(arg0.getButton()));
	}

	/**
	 * Gives <code>true</code> if the specified mouse button is pressed.
	 * @param mouseButton the mouse button to check.
	 * @return <code>true</code> if the specified mouse button is pressed.
	 */
	public boolean mousePressed(int mouseButton) {
		return this.mouseButtons.contains(mouseButton);
	}
}
