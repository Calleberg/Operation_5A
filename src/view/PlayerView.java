package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.geometrical.Position;
import model.sprites.Player;

/**
 * A class which renders a player.
 * 
 * @author 
 *
 */
public class PlayerView implements ObjectRenderer<Player> {

	private Player p;
	
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
			
			//Draws the legs
			g2d.setColor(Color.BLACK);
			g2d.rotate(-p.getMoveDir(), rX, rY);
			g2d.fillRect(x, y, (int)(p.getCollisionBox().getWidth() * scale), 
					(int)(p.getCollisionBox().getHeight() * scale));
			g2d.setColor(new Color(255, 0, 0, 100));
			g2d.fillRect(x + (int)(p.getCollisionBox().getWidth()*9/10 * scale), y, (int)(p.getCollisionBox().getWidth()/10 * scale), 
					(int)(p.getCollisionBox().getHeight() * scale));
			g2d.rotate(p.getMoveDir(), rX, rY);
			
			//Draws the upper body
			g2d.setColor(Color.BLACK);
			g2d.rotate(-p.getDirection(), rX, rY);
			g2d.setColor(new Color(0, 0, 0, 100));
			g2d.fillRect(x, y, (int)(p.getCollisionBox().getWidth() * scale), 
					(int)(p.getCollisionBox().getHeight() * scale));
			g2d.rotate(p.getDirection(), rX, rY);
			
			//Draws angle independent data!
			g2d.setColor(Color.RED);
			g2d.drawString(p.getHealth() + "hp", x, y);
			g2d.fillRect((int)(p.getProjectileSpawn().getX() * scale + offset.getX()),
					(int)(p.getProjectileSpawn().getY() * scale + offset.getY()), 2, 2);
			g2d.fillRect((int)(p.getCenter().getX() * scale + offset.getX()),
					(int)(p.getCenter().getY() * scale + offset.getY()), 2, 2);
		}
	}

}
