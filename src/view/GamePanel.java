package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import controller.GameController;

import model.GameModel;
import model.geometrical.CollisionBox;
import model.geometrical.Line;
import model.geometrical.Position;
import model.items.weapons.Projectile;
import model.sprites.Sprite;

/**
 * 
 * 
 * @author 
 *
 */
public class GamePanel extends JPanel implements PropertyChangeListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	private GameController controller;
	private long tick = 0;
	private TileView[][] tiles;
	
	/**
	 * Creates a new panel with the specified model and controller.
	 * @param model the model to display.
	 * @param controller the controller to use.
	 */
	public GamePanel(GameModel model, GameController controller) {
		super();
		this.model = model;
		this.controller = controller;
		this.addMouseMotionListener(this);
	}
	
	/**
	 * Draws everything.
	 */
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		tick++;
		
		if(tiles == null && model.getWorld() != null) {
			tiles = new TileView[model.getWorld().getTiles().length][model.getWorld().getTiles()[0].length];
			for(int i = 0; i < model.getWorld().getTiles().length; i++) {
				for(int j = 0; j < model.getWorld().getTiles()[i].length; j++) {
					tiles[i][j] = new TileView(model.getWorld().getTiles()[i][j]);
				}
			}
		}
		
		//TODO: gör till individuella vy-rutor
		for(int i = 0; i < model.getWorld().getTiles().length; i++) {
			for(int j = 0; j < model.getWorld().getTiles()[i].length; j++) {
				tiles[i][j].render(g, new Position(0, 0), 40);
			}
		}
		
		//TODO to bort det mesta nedan..............
		for(Sprite s : model.getWorld().getSprites()) {
			g.setColor(Color.BLACK);
			g.fillRect((int)(s.getX()*40), (int)(s.getY()*40), 40, 40);
			g.setColor(Color.RED);
			this.renderCollisionBox(g, 40, s.getCollisionBox(), Color.RED, false, null);
//			DEV_CollisionBoxRenderer.render(g, 40, s.getCollisionBox(), Color.RED, true, Color.BLUE);
		}
		
		for(Projectile p : model.getWorld().getProjectiles()) {
			g.setColor(Color.BLACK);
			g.fillRect((int)(p.getPosition().getX() * 40), (int)(p.getPosition().getY() * 40), 4, 4);
			this.renderCollisionBox(g, 40, p.getCollisionBox(), Color.RED, false, null);
		}
		//data:
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(0, 0, 500, 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		g.drawString("World size: " + model.getWorld().getTiles().length + "x" + model.getWorld().getTiles()[0].length, 10, 20);
		g.drawString("Number of updates since start: " + tick, 10, 40);
		g.drawString("Number of projectiles: " + model.getWorld().getProjectiles().size(), 10, 60);
		g.drawString("Number of characters/sprites: " + model.getWorld().getSprites().size(), 10, 80);
		//TODO ta bort det mest över....................
	}
	
	/**
	 * Draws the collision box.
	 * @param g the graphics instance to use when drawing.
	 * @param scale the scale to draw at.
	 * @param box the collision box to draw.
	 * @param color the colour to draw in.
	 * @param renderPosition specify if the position of each line should be marked.
	 * @param colourPosition the colour of the position mark.
	 */
	public void renderCollisionBox(Graphics g, int scale, CollisionBox box, Color colour, 
			boolean renderPosition, Color colourPosition) {
		for(Line l : box.getPolygonSegments()) {
			g.setColor(colour);
			g.drawLine((int)(l.getX1() * scale), (int)(l.getY1() * scale), 
					(int)(l.getX2() * scale), (int)(l.getY2() * scale));
			if(renderPosition) {
				g.setColor(colourPosition);
				g.fillRect((int)(l.getPosition().getX() * scale),
						(int)(l.getPosition().getY() * scale), 3, 3);
			}
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		//Not used.
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//TODO: use camera scale in the future
		this.controller.handleMouseAt(e.getX()/40, e.getY()/40);
	}
}
