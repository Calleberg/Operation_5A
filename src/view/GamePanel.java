package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import controller.GameController;



import model.GameModel;
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
		
		//TODO to bort
		for(Sprite s : model.getWorld().getSprites()) {
			g.setColor(Color.BLACK);
			g.fillRect((int)(s.getX()*40), (int)(s.getY()*40), 40, 40);
			g.setColor(Color.RED);
			g.drawRect((int)(s.getCollisionBox().getPosition().getX() * 40),
					(int)(s.getCollisionBox().getPosition().getY() * 40), 
					(int)(s.getCollisionBox().getWidth() * 40), 
					(int)(s.getCollisionBox().getHeight() * 40));
		}
		
		//
		g.setColor(Color.BLACK);
		for(Projectile p : model.getWorld().getProjectiles()) {
			g.fillRect((int)(p.getPosition().getX() * 40), (int)(p.getPosition().getY() * 40), 4, 4);
		}
		
		g.drawString("Number of updates since start: " + tick, 10, 10);
		g.drawString("Number of projectiles: " + model.getWorld().getProjectiles().size(), 10, 20);
		g.drawString("Number of characters/sprite: " + model.getWorld().getSprites().size(), 10, 30);
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
