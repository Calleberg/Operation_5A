package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import controller.IO.Resources;

import model.geometrical.Position;
import model.other.Animation;
import model.sprites.Sprite;

/**
 * A class which will take and then draw a specific Sprite instance.
 * 
 * @author
 *
 */
public class SpriteView implements ObjectRenderer<Sprite> {

	private Sprite sprite;
	//TODO: add enemy texture
	private BufferedImage[] texture = Resources.splitImages("zombie01.png", 5, 4);
	
	private Animation walkAnimation = new Animation(new int[]{0,1,2,3,4,5,6,7}, 100, true);
	private int standImage = 10;
	
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
			int x = (int)(sprite.getMoveBox().getPosition().getX() * scale + offset.getX());
			int y = (int)(sprite.getMoveBox().getPosition().getY() * scale + offset.getY());
			int rX = (int)(sprite.getCenter().getX() * scale + offset.getX());
			int rY = (int)(sprite.getCenter().getY() * scale + offset.getY());
			
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;

			//Draws the body
			AffineTransform transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getRotateInstance(-sprite.getDirection(), rX, rY));
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			transformer.concatenate(AffineTransform.getScaleInstance(sprite.getMoveBox().getWidth(), 
					sprite.getMoveBox().getHeight()));
			if(sprite.getState() == Sprite.State.MOVING) {
				g2d.drawImage(texture[walkAnimation.getFrame()], transformer, null);
			}
			g2d.drawImage(texture[standImage], transformer, null);
			
			//Draws dev data
//			g2d.setColor(Color.RED);
//			g2d.drawString(sprite.getHealth() + "hp", x, y);
//			g2d.fillRect((int)(sprite.getProjectileSpawn().getX() * scale + offset.getX()),
//					(int)(sprite.getProjectileSpawn().getY() * scale + offset.getY()), 2, 2);
//			g2d.fillRect((int)(sprite.getCenter().getX() * scale + offset.getX()),
//					(int)(sprite.getCenter().getY() * scale + offset.getY()), 2, 2);
			
//			GamePanel.renderCollisionBox(g, offset, scale, sprite.getHitBox(), Color.RED, false, null);
//			GamePanel.renderCollisionBox(g, offset, scale, sprite.getMoveBox(), Color.ORANGE, false, null);
		}
	}
}
