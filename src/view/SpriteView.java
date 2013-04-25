package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.geometrical.Position;
import model.sprites.Sprite;

/**
 * A class which will take and then draw a specific Sprite instance.
 * 
 * @author
 *
 */
public class SpriteView implements ObjectRenderer<Sprite> {

	private Sprite sprite;
	
	/**
	 * Creates a new sprite view which will render the specified sprite.
	 * @param sprite the sprite to render.
	 */
	public SpriteView(Sprite sprite) {
		this.sprite = sprite;
	}
	
	@Override
	public Sprite getObject() {
		return this.sprite;
	}

	@Override
	public void setObject(Sprite object) {
		this.sprite = object;
	}
	
	@Override
	public void render(Graphics g, Position offset, int scale) {
		if(sprite != null) {
			//Saves some values for quick access.
			int x = (int)(sprite.getX() * scale + offset.getX());
			int y = (int)(sprite.getY() * scale + offset.getY());
			int rX = (int)(sprite.getCenter().getX() * scale + offset.getX());
			int rY = (int)(sprite.getCenter().getY() * scale + offset.getY());
			
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;
			g2d.rotate(-sprite.getDirection(), rX, rY);
			
			//Draws what it has to draw...
			g2d.setColor(Color.BLACK);
			g2d.fillRect(x, y, (int)(sprite.getCollisionBox().getWidth() * scale), 
					(int)(sprite.getCollisionBox().getHeight() * scale));
			g2d.drawString(sprite.getHealth() + "hp", x, y);
			
			//Rotates the graphics to its original position.
			g2d.rotate(sprite.getDirection(), rX, rY);
			
			g2d.setColor(Color.RED);
			g2d.fillRect((int)(sprite.getProjectileSpawn().getX() * scale + offset.getX()),
					(int)(sprite.getProjectileSpawn().getY() * scale + offset.getY()), 2, 2);
			g2d.fillRect((int)(sprite.getCenter().getX() * scale + offset.getX()),
					(int)(sprite.getCenter().getY() * scale + offset.getY()), 2, 2);
		}
	}
}
