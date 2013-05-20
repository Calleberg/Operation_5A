package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;



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
	private static BufferedImage[] props = Resources.splitImages("props.png", 10, 20);
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
	 * Gives all the textures used when drawing the props.
	 * @return an array of all the textures used when drawing the props.
	 */
	public static BufferedImage[] getPropImages() {
		return props;
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
			for(int i = 0; i < t.getProps().size(); i++) {
				g.drawImage(props[t.getProps().get(i).getImageNbr()], x, y, scale, scale, null);
			}
//			GamePanel.renderCollisionBox(g, offset, scale, t.getCollisionBox(), new Color(255, 0, 0, 100), false, null);
			
			//TODO: fixa lite 
			if(t.hasNorthWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(x, y - scale/20, scale, scale/10);
			}
			if(t.hasWestWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(x - scale/20, y, scale/10, scale);
			}
		}
	}
}
