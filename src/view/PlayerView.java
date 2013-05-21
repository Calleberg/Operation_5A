package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.geometrical.Position;
import model.items.weapons.Weapon;
import model.other.Animation;
import model.sprites.Player;
import model.sprites.Sprite;

/**
 * A class which renders a player.
 * 
 * @author 
 *
 */
public class PlayerView implements ObjectRenderer<Player>, PropertyChangeListener {

	private Player p;
	private static BufferedImage[] playerTexture = Resources.splitImages("player.png", 5, 6);
	private static BufferedImage[] weapons = Resources.splitImages("weaponIcon.png", 10, 10);
	
	//Leg animations
	private Animation walkAnimation = new Animation(new int[]{0,1,2,3,4,5,6,7}, 50, true);
	//Body animatios
	private Animation reloadAnimation = new Animation(new int[]{25,26,27,28,29}, 50, false, false);
	private Animation meleeAnimation = new Animation(new int[]{15,16,17,18,19}, 40, false, false);
	private Animation fistAnimation = new Animation(new int[]{20,21,22,23,24}, 40, false, false);
	private Animation activeAnimation;
	private int armsHolding = 11;
	private int armsFists = 20;
	//Main image
	private int standImage = 10;
		
	/**
	 * Creates a new player view with the specified player to render.
	 * @param p the player to render.
	 */
	public PlayerView(Player p) {
		this.setObject(p);
	}
	
	@Override
	public Player getObject() {
		return this.p;
	}

	@Override
	public void setObject(Player object) {
		this.p = object;
		object.addListener(this);
	}
	
	@Override
	public int getLayer() {
		return 4;
	}

	@Override
	public void render(Graphics g, Position offset, int scale) {
		this.render(g, offset, scale, null);
	}
	
	public void render(Graphics g, Position offset, int scale, Position forcedPos) {
		if(p != null) {
			//Saves some values for quick access.
			int rX, rY;
			int x, y;
			if(forcedPos != null) {
				rX = (int)forcedPos.getX();
				rY = (int)forcedPos.getY();
				x = (int)rX - playerTexture[0].getWidth()/2;
				y = (int)rY - playerTexture[0].getHeight()/2;
			}else{
				rX = (int)(p.getCenter().getX() * scale + offset.getX());
				rY = (int)(p.getCenter().getY() * scale + offset.getY());
				x = rX - 20;
				y = rY - 20;
			}
					
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;		

			//Draws thelegs			
			AffineTransform transformer;
			if(p.getState() == Sprite.State.RUNNING) {
				transformer = (AffineTransform)g2d.getTransform().clone();
				transformer.concatenate(AffineTransform.getRotateInstance(-p.getMoveDir(), rX, rY));
				transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
				g2d.drawImage(playerTexture[walkAnimation.getFrame()], transformer, null);
			}
			
			transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getRotateInstance(-p.getDirection(), rX, rY));
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			
			//Draw the arms
			if(activeAnimation != null && activeAnimation.isRunning()) {
				g2d.drawImage(playerTexture[activeAnimation.getFrame()], transformer, null);
				this.renderWeapon(g2d, rX, rY, scale, p.getDirection(), 
						(float)activeAnimation.getFrameIndex()/activeAnimation.getLength());
			}else{
				switch(p.getActiveWeapon().getType()) {
				case FISTS: 
					g2d.drawImage(playerTexture[armsFists], transformer, null);
					break;
				case MELEE: case GUN:
					g2d.drawImage(playerTexture[armsHolding], transformer, null);
					break;				
				}
				this.renderWeapon(g2d, rX, rY, scale, p.getDirection(), 0f);
			}
			
			//Draws the body
			g2d.drawImage(playerTexture[standImage], transformer, null);
		}
	}
	
	private void renderWeapon(Graphics g, int x, int y, int scale, float dir, float percentage) {		
		Graphics2D g2d = (Graphics2D)g;
		
		float angle = (float) (-dir + Math.PI/4);
		if(percentage < 0.50f) {
			angle -= (Math.PI/2) * (percentage/0.50f);
		}else{
			angle -= (Math.PI/2) * ((1-percentage)/0.50f);
		}
		if(p.getActiveWeapon().getType() == Weapon.Type.GUN) {
			angle = -dir;
		}
		
		AffineTransform transformer = (AffineTransform)g2d.getTransform().clone();
		transformer.concatenate(AffineTransform.getRotateInstance(angle, x, y));
		transformer.concatenate(AffineTransform.getTranslateInstance(x - scale/4, y - scale/2));
		
		g2d.drawImage(weapons[p.getActiveWeapon().getIconNumber()], transformer, null);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Player.EVENT_RELOADING)) {
			if(p.getActiveWeapon().getAmmunitionInMagazine() != Weapon.UNLIMITED_AMMO) {
				this.runAnimation(reloadAnimation);
			}
		}else if(evt.getPropertyName().equals(Player.EVENT_USE_WEAPON)) {
			switch(p.getActiveWeapon().getType()) {
			case FISTS:
				this.runAnimation(fistAnimation);
				break;
			case GUN:
				//No animation
				break;
			case MELEE:
				this.runAnimation(meleeAnimation);
				break;				
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
