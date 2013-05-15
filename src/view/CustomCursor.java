package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import controller.IO.Resources;

/**
 * A cursor with a custom image and which supports alpha values on the image.
 * Note: For this cursor to work correctly it must listen to the panel it should display on.
 * 
 * @author 
 *
 */
public class CustomCursor implements MouseMotionListener {

	private Point cursorPosition;
	private final BufferedImage cursor = Resources.getSingleImage("cursor.png");
	
	/**
	 * Draws the cursor.
	 * @param g the graphics instance to draw to.
	 */
	public void render(Graphics g) {
		if(cursorPosition != null) {
			g.drawImage(cursor, cursorPosition.x - cursor.getWidth()/2, 
					cursorPosition.y - cursor.getHeight()/2, null);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		cursorPosition = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cursorPosition = e.getPoint();
	}

}
