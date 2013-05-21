package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import model.geometrical.Position;
import model.other.Animation;
import model.sprites.Sprite;

/**
 * A class which will take and then draw a specific Sprite instance.
 * 
 * @author
 *
 */
public class SpriteView implements ObjectRenderer<Sprite>, PropertyChangeListener {

	private Sprite sprite;
	private StatusBar hpbar;
	private static BufferedImage[] texture1 = Resources.splitImages("zombie01.png", 5, 4);
	
	//Leg animations
	private Animation walkAnimation = new Animation(new int[]{0,1,2,3,4,5,6,7}, 200, true);
	private Animation runAnimation = new Animation(new int[]{0,1,2,3,4,5,6,7}, 100, true);
	//Body animatins
	private Animation hitAnimation = new Animation(new int[]{15,16,17,18,19}, 60, false, false);
	private Animation activeAnimation;
	private int idleArms = 15;
	//Main image
	private int standImage = 10;
	
	/**
	 * Creates a new sprite view which will render the specified sprite.
	 * @param sprite the sprite to render.
	 */
	public SpriteView(Sprite sprite) {
		this.setObject(sprite);
	}
	
	@Override
	public Sprite getObject() {
		return this.sprite;
	}

	@Override
	public void setObject(Sprite object) {
		this.sprite = object;
		this.hpbar = new StatusBar(0.1f, 1f, object.getHealth());
		object.addListener(this);
	}
	
	@Override
	public int getLayer() {
		return 3;
	}
	
	@Override
	public void render(Graphics g, Position offset, int scale) {
		if(sprite != null) {
			//Saves some values for quick access.
			int rX = (int)(sprite.getCenter().getX() * scale + offset.getX());
			int rY = (int)(sprite.getCenter().getY() * scale + offset.getY());
			int x = rX - 20;
			int y = rY - 20;
			
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;

			//Draws the body
			AffineTransform transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getRotateInstance(-sprite.getDirection(), rX, rY));
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			if(sprite.getState() == Sprite.State.RUNNING) {
				g2d.drawImage(texture1[runAnimation.getFrame()], transformer, null);
			}else if(sprite.getState() == Sprite.State.WALKING) {
				g2d.drawImage(texture1[walkAnimation.getFrame()], transformer, null);
			}
			g2d.drawImage(texture1[standImage], transformer, null);
			
			if(activeAnimation != null && activeAnimation.isRunning()) {
				g2d.drawImage(texture1[activeAnimation.getFrame()], transformer, null);
			}else{
				g2d.drawImage(texture1[idleArms], transformer, null);
			}
			
			//Draws dev data
			hpbar.setValue(sprite.getHealth());
			hpbar.render(g2d, x, y - (int)(scale/10), scale);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Sprite.EVENT_USE_WEAPON)) {
			if(sprite.getActiveWeapon().getRange() < 1f) {
				this.runAnimation(hitAnimation);
			}
		}
	}
	
	private void runAnimation(Animation animation) {
		if(activeAnimation != animation || !activeAnimation.isRunning()) {
			this.activeAnimation = animation;
			animation.start();
		}
	}
}
