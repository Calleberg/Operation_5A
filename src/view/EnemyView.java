package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import model.geometrical.Position;
import model.other.Animation;
import model.sprites.Enemy;
import model.sprites.Sprite;

/**
 * A class which will take and then draw a specific Sprite instance.
 * 
 * @author Jonatan Magnusson, Martin Calleberg
 *
 */
public class EnemyView implements ObjectRenderer<Enemy>, PropertyChangeListener {

	private Enemy sprite;
	private StatusBar hpbar;
	private static BufferedImage[][] texture = {
		Resources.splitImages("zombie00.png", 5, 4),
		Resources.splitImages("zombie01.png", 5, 4),
		Resources.splitImages("zombie02.png", 5, 4)
	};
	
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
	public EnemyView(Enemy sprite) {
		this.setObject(sprite);
	}
	
	@Override
	public Enemy getObject() {
		return this.sprite;
	}

	@Override
	public void setObject(Enemy object) {
		this.sprite = object;
		this.hpbar = new StatusBar(0.1f, 1f, object.getHealth());
		object.addListener(this);
	}
	
	@Override
	public int getLayer() {
		return 3;
	}
	
	@Override
	public void render(Graphics g, Position offset, int defaultSize, float scale) {
		if(sprite != null) {
			//Saves some values for quick access.
			int rX = (int)(sprite.getCenter().getX() * defaultSize * scale + offset.getX());
			int rY = (int)(sprite.getCenter().getY() * defaultSize * scale + offset.getY());
			int x = rX - (int)(defaultSize*scale/2);
			int y = rY - (int)(defaultSize*scale/2);
			
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;

			//Draws the body
			AffineTransform transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getRotateInstance(-sprite.getDirection(), rX, rY));
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			transformer.concatenate(AffineTransform.getScaleInstance(scale, scale));
			
			if(sprite.getState() == Sprite.State.RUNNING) {
				g2d.drawImage(texture[sprite.getImageNbr()][runAnimation.getFrame()], transformer, null);
			}else if(sprite.getState() == Sprite.State.WALKING) {
				g2d.drawImage(texture[sprite.getImageNbr()][walkAnimation.getFrame()], transformer, null);
			}
			g2d.drawImage(texture[sprite.getImageNbr()][standImage], transformer, null);
			
			if(activeAnimation != null && activeAnimation.isRunning()) {
				g2d.drawImage(texture[sprite.getImageNbr()][activeAnimation.getFrame()], transformer, null);
			}else{
				g2d.drawImage(texture[sprite.getImageNbr()][idleArms], transformer, null);
			}
			
			//Draws dev data
			hpbar.setValue(sprite.getHealth());
			hpbar.render(g2d, x, y - (int)(defaultSize*scale/10), (int)(defaultSize*scale));
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
