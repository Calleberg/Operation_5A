package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import base.Resources;

import model.geometrical.Position;
import model.world.Tile;

/**
 * Used to render a single tile.
 * 
 * @author Calleberg
 *
 */
public class TileView {

	private static BufferedImage[] floors = Resources.splitImages("floor.png", 10, 10);
	private Tile t;
	
	/**
	 * Creates a new tile view which will render the specified tile.
	 * @param t the tile to render.
	 */
	public TileView(Tile t) {
		this.t = t;
	}
	
	/**
	 * Gives all the textures used when drawing the floor.
	 * @return an array of all the textures used when drawing the floor.
	 */
	public static BufferedImage[] getFloorImages() {
		return floors;
	}
	
	/**
	 * Renders the tile.
	 * @param g the graphics instance to draw to.
	 * @param offset the offset to render at.
	 * @param scale the scale to render at.
	 */
	public void render(Graphics g, Position offset, int scale) {
		if(t != null) {
			int x = (int)(t.getX() * scale + offset.getX()); 
			int y = (int)(t.getY() * scale + offset.getY());
			g.drawImage(floors[t.getFloor()], x, y, scale, scale, null);
			GamePanel.renderCollisionBox(g, offset, scale, t.getCollisionBox(), Color.RED, false, null);
//			if(t.hasNorthWall()) {
//				g.setColor(Color.BLACK);
//				g.fillRect(x, y, scale, scale/10);
//			}
//			if(t.hasWestWall()) {
//				g.setColor(Color.BLACK);
//				g.fillRect(x, y, scale/10, scale);
//			}
		}
	}
}
