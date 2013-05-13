package view.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.GameModel;
import model.geometrical.Position;
import view.Camera;
import view.TileView;

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
		this.setBackground(new Color(19,64,141));
		this.model = model;
		this.camera = new Camera(1);
		minimap = createMinimap();
	}
	
	/*
	 * Creates a small version of the map and saves it as an image.
	 */
	private BufferedImage createMinimap() {
		BufferedImage minimap = new BufferedImage(model.getWorld().getTiles().length , 
				model.getWorld().getTiles()[0].length, BufferedImage.OPAQUE);
		Graphics2D g2d = (Graphics2D)minimap.getGraphics();
		for(int x = 0; x < model.getWorld().getTiles().length; x++) {
			for(int y = 0; y < model.getWorld().getTiles()[0].length; y++) {
				TileView tv = new TileView(model.getWorld().getTiles()[x][y]);
				tv.render(g2d, new Position(0, 0), 1);
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
		g.setColor(Color.RED);
		g.fillRect((int)(model.getPlayer().getX() - 1 + camera.getX()), 
				(int)(model.getPlayer().getY() - 1 + camera.getY()), 3, 3);
	}
}
