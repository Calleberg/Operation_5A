package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import model.geometrical.Position;
import model.sprites.Player;
import model.sprites.Sprite;
import controller.IO.Resources;

/**
 * A class which renders a player.
 * 
 * @author 
 *
 */
public class PlayerView implements ObjectRenderer<Player> {

	private Player p;
	private BufferedImage[] texture = Resources.splitImages("player.png", 5, 4);
	
	private Animation walkAnimation = new Animation(new int[]{0,1,2,3,4,5,6,7}, 50, true);
	private int standImage = 10;
	
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
			int x = (int)(p.getMoveBox().getPosition().getX() * scale + offset.getX());
			int y = (int)(p.getMoveBox().getPosition().getY() * scale + offset.getY());
			int rX = (int)(p.getCenter().getX() * scale + offset.getX());
			int rY = (int)(p.getCenter().getY() * scale + offset.getY());
			
			//Rotates the graphics around the center of the sprite.
			Graphics2D g2d = (Graphics2D)g;
			

			//			Draws thelegs			
			AffineTransform transformer;
			if(p.getState() == Sprite.State.MOVING) {
				transformer = (AffineTransform)g2d.getTransform().clone();
				transformer.concatenate(AffineTransform.getRotateInstance(-p.getMoveDir(), rX, rY));
				transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
				transformer.concatenate(AffineTransform.getScaleInstance(p.getMoveBox().getWidth(), 
						p.getMoveBox().getHeight()));
				g2d.drawImage(texture[walkAnimation.getFrame()], transformer, null);
			}
			
			//Draws the body
			transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getRotateInstance(-p.getDirection(), rX, rY));
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			transformer.concatenate(AffineTransform.getScaleInstance(p.getMoveBox().getWidth(), 
					p.getMoveBox().getHeight()));
			g2d.drawImage(texture[standImage], transformer, null);
			
			//Draws data
			g2d.setColor(Color.RED);
			g2d.drawString(p.getHealth() + "hp", x, y);
			g2d.fillRect((int)(p.getProjectileSpawn().getX() * scale + offset.getX()),
					(int)(p.getProjectileSpawn().getY() * scale + offset.getY()), 2, 2);
			g2d.fillRect((int)(p.getCenter().getX() * scale + offset.getX()),
					(int)(p.getCenter().getY() * scale + offset.getY()), 2, 2);
			
//			GamePanel.renderCollisionBox(g, offset, scale, p.getHitBox(), Color.RED, false, null);
//			GamePanel.renderCollisionBox(g, offset, scale, p.getMoveBox(), Color.ORANGE, false, null);
		}
	}

}
