package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.geometrical.Position;

/**
 * Draws a small pool of blood at the specified position.
 * 
 * @author Calleberg
 *
 */
public class BloodPool implements ObjectRenderer<Position> {

	private Position pos;
	private static final BufferedImage[] texture = Resources.splitImages("blood.png", 4, 1);
	private int image;
	
	/**
	 * Creates a new blood pool at the specified position.
	 * @param pos the position of the pool.
	 */
	public BloodPool(Position pos) {
		this.setObject(pos);
		this.image = (int)(Math.random() * texture.length);
	}
	
	@Override
	public Position getObject() {
		return this.pos;
	}

	@Override
	public void setObject(Position object) {
		this.pos = object;
	}

	@Override
	public void render(Graphics g, Position offset, int scale) {
		if(pos != null) {
			int x = (int)((pos.getX() -0.5f) * scale + offset.getX());
			int y = (int)((pos.getY() -0.5f) * scale + offset.getY());
			
			g.drawImage(texture[image], x, y, null);
		}
	}

}
