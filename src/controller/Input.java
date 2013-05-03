package controller;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Holds which keys have been pressed and which haven't.
 *
 * @author Calleberg
 *
 */
public class Input implements KeyListener, MouseListener {

	private boolean[] keys = new boolean[255];
	private boolean[] mouseButtons = new boolean[3];	//the 3 mouse buttons.
	
	/**
	 * Sets which container to listen to.
	 * @param container the container to get input from.
	 */
	public void setContainer(Container container) {
		container.addKeyListener(this);
		container.addMouseListener(this);
	}
	
	/**
	 * Gives <code>true</code> if the specified key is pressed.
	 * @param key the key to check.
	 * @return <code>true</code> if the specified key is pressed.
	 */
	public boolean isPressed(int key) {
		if(key < 0 || key >= keys.length) {
			return false;
		}else{
			return keys[key];
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
		this.mouseButtons[arg0.getButton() - 1] = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.mouseButtons[arg0.getButton() - 1] = false;
	}

	/**
	 * Gives <code>true</code> if the specified mouse button is pressed.
	 * @param mouseButton the mouse button to check.
	 * @return <code>true</code> if the specified mouse button is pressed.
	 */
	public boolean mousePressed(int mouseButton) {
		mouseButton--;
		if(mouseButton < 0 || mouseButton >= mouseButtons.length) {
			return false;
		}else{
			return mouseButtons[mouseButton];
		}
	}
}
