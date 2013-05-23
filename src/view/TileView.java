package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;



import model.geometrical.Position;
import model.world.Tile;

/**
 * Used to render a single tile.
 * 
 * @author Martin Calleberg
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
	 * @param defaultSize the default size of objects.
	 * @param scale the scale to render at.
	 */
	public void render(Graphics g, Position offset, int defaultSize, float scale) {
		if(t != null) {
			int x = (int)(t.getPosition().getX() * (defaultSize*scale) + offset.getX()); 
			int y = (int)(t.getPosition().getY() * (defaultSize*scale) + offset.getY());
			g.drawImage(floors[t.getFloor()], x, y, (int)(defaultSize*scale), (int)(defaultSize*scale), null);
			for(int i = 0; i < t.getProps().size(); i++) {
				g.drawImage(props[t.getProps().get(i).getImageNbr()], x, y, (int)(defaultSize*scale), (int)(defaultSize*scale), null);
			}
			
			if(t.hasNorthWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(x, (int)(y - (defaultSize*scale)/20), (int)(defaultSize*scale), (int)((defaultSize*scale)/10));
			}
			if(t.hasWestWall()) {
				g.setColor(Color.BLACK);
				g.fillRect((int)(x - (defaultSize*scale)/20), y, (int)((defaultSize*scale)/10), (int)(defaultSize*scale));
			}
		}
	}
}
