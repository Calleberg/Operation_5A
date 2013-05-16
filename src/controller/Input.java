package controller;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import model.sprites.Sprite;

/**
 * Holds which keys have been pressed and which haven't.
 *
 * @author Calleberg
 *
 */
public class Input implements KeyListener, MouseListener {

	private List<Boolean> keys = new ArrayList<Boolean>();
	private List<Boolean> mouseButtons = new ArrayList<Boolean>();
	//private boolean[] keys;
	//private boolean[] mouseButtons;
	
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
		this.keys.set(key, false);
		//this.keys[key] = false
	}
	
	/**
	 * Gives <code>true</code> if the specified key is pressed.
	 * @param key the key to check.
	 * @return <code>true</code> if the specified key is pressed.
	 */
	public boolean isPressed(int key) {
		if(key < 0 || key >= keys.size()){//keys.length
			return false;
		}else{
			return keys.get(key);
			//return keys[key]
		}
	}
	
	/**
	 * Resets the instance and sets all the keys to false.
	 */
	public void reset() {
		keys = new ArrayList<Boolean>();
		mouseButtons = new ArrayList<Boolean>();
		
		for(int i = 0; i <= 254; i++){
			keys.add(i, false);
		}
		
		for(int i = 0; i <= 2; i++){
			mouseButtons.add(i, false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys.set(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys.set(e.getKeyCode(), false);
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
		this.mouseButtons.set(arg0.getButton() -1,true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.mouseButtons.set(arg0.getButton() - 1, false);
	}

	/**
	 * Gives <code>true</code> if the specified mouse button is pressed.
	 * @param mouseButton the mouse button to check.
	 * @return <code>true</code> if the specified mouse button is pressed.
	 */
	public boolean mousePressed(int mouseButton) {
		mouseButton--;
		if(mouseButton < 0 || mouseButton >= mouseButtons.size()) {
			return false;
		}else{
			return mouseButtons.get(mouseButton);
		}
	}
}
