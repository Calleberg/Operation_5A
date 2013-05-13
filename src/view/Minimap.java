package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.GameModel;
import model.geometrical.Position;

/**
 * 
 * 
 * @author
 *
 */
public class Minimap extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	private Camera camera;
	private BufferedImage minimap;
	
	/**
	 * Creates a new minimap which will render the specified model.
	 * @param model the model to render.
	 */
	public Minimap(GameModel model) {
		super();
		this.setBackground(new Color(30, 135, 254));
		this.model = model;
		this.camera = new Camera(2);
		minimap = createMinimap();
	}
	
	/*
	 * Creates a small version of the map and saves it as an image.
	 */
	private BufferedImage createMinimap() {
		BufferedImage minimap = new BufferedImage(model.getWorld().getTiles().length * camera.getScale() , 
				model.getWorld().getTiles()[0].length * camera.getScale(), BufferedImage.OPAQUE);
		Graphics2D g2d = (Graphics2D)minimap.getGraphics();
		for(int x = 0; x < model.getWorld().getTiles().length; x++) {
			for(int y = 0; y < model.getWorld().getTiles()[0].length; y++) {
				TileView tv = new TileView(model.getWorld().getTiles()[x][y]);
				tv.render(g2d, new Position(0, 0), camera.getScale());
			}
		}
		return minimap;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		camera.setToCenter(model.getPlayer().getCenter(), this.getSize());
		//Draws the map
		if(minimap != null) {
			g.drawImage(minimap, (int)camera.getX(), (int)camera.getY(), null);
		}
		//Draws a small player.
		int x = (int)(model.getPlayer().getX() * camera.getScale() + camera.getX());
		int y = (int)(model.getPlayer().getY() *camera.getScale() + camera.getY());
		
		g.setColor(Color.RED);
		g.drawLine(x - camera.getScale()*3, y, x + camera.getScale()*3, y);
		g.drawLine(x, y - camera.getScale()*3, x, y + camera.getScale()*3);
	}
}
