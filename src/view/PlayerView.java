package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import model.geometrical.Position;
import model.sprites.Player;
import controller.IO.Resources;

/**
 * A class which renders a player.
 * 
 * @author 
 *
 */
public class PlayerView implements ObjectRenderer<Player> {

	private Player p;
	private BufferedImage[] texture = Resources.splitImages("player.png", 5, 2);
	
	private Animation walkAnimation = new Animation(new int[]{0,1,2,3,4}, 100, true);
	private int standImage = 5;
	
	/**
	 * Creates a new player view with the specified player to render.
	 * @param p the player to render.
	 */
	public PlayerView(Player p) {
		this.p = p;
	}
	
	@Override
	public Player getObject() {
		return this.p;
	}

	@Override
	public void setObject(Player object) {
		this.p = object;
	}

	@Override
	public void render(Graphics g, Position offset, int scale) {
		if(p != null) {
			//Saves some values for quick access.
			int x = (int)(p.getX() * scale + offset.getX());
			int y = (int)(p.getY() * scale + offset.getY());
			int rX = (int)(p.getCenter().getX() * scale + offset.getX());
			int rY = (int)(p.getCenter().getY() * scale + offset.getY());
			
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;
			
//			Draws the legs
//			g2d.setColor(Color.BLACK);
//			g2d.rotate(-p.getMoveDir(), rX, rY);
//			g2d.fillRect(x, y, (int)(p.getCollisionBox().getWidth() * scale), 
//					(int)(p.getCollisionBox().getHeight() * scale));
//			g2d.setColor(new Color(255, 0, 0, 100));
//			g2d.fillRect(x + (int)(p.getCollisionBox().getWidth()*9/10 * scale), y, (int)(p.getCollisionBox().getWidth()/10 * scale), 
//					(int)(p.getCollisionBox().getHeight() * scale));
//			g2d.rotate(p.getMoveDir(), rX, rY);
//			
			//Draws the body
			AffineTransform transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getRotateInstance(-p.getDirection(), rX, rY));
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			transformer.concatenate(AffineTransform.getScaleInstance((p.getCollisionBox().getWidth() * scale)/scale, 
					(p.getCollisionBox().getHeight() * scale)/scale));
			switch(p.getState()) {
			case MOVING:
				g2d.drawImage(texture[walkAnimation.getFrame()], transformer, null);
				break;
			case STANDING:
				g2d.drawImage(texture[standImage], transformer, null);
				break;
			default:
				break;
			}
			
			//Draws data
			g2d.setColor(Color.RED);
			g2d.drawString(p.getHealth() + "hp", x, y);
			g2d.fillRect((int)(p.getProjectileSpawn().getX() * scale + offset.getX()),
					(int)(p.getProjectileSpawn().getY() * scale + offset.getY()), 2, 2);
			g2d.fillRect((int)(p.getCenter().getX() * scale + offset.getX()),
					(int)(p.getCenter().getY() * scale + offset.getY()), 2, 2);
			
			GamePanel.renderCollisionBox(g, offset, scale, p.getCollisionBox(), Color.RED, false, null);
		}
	}

}
