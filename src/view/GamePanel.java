package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import view.panels.Minimap;
import view.panels.PlayerPanel;

import controller.GameController;

import model.GameModel;
import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.items.weapons.Projectile;
import model.sprites.Player;
import model.sprites.Sprite;
import model.items.Supply;

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
	private List<ObjectRenderer<?>> objects;
	private Camera camera;
	private final int SLEEP = 1000 / 60;
	private Thread t;
	
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
		this.camera = new Camera(40);
		this.buildLayout();
		
		this.initObjectList();
		this.initTileList();
		t = new Thread() {
			@Override
			public void run() {
				while(true) {
					repaint();
					tick++;
					try{
						Thread.sleep(SLEEP);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	/**
	 * The panel will start rendering.
	 */
	public void start() {
		t.start();
	}
	
	/*
	 * Builds the layout of the game panel. (Adds all the GUI bits)
	 */
	private void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        add(new PlayerPanel(model.getPlayer()), gridBagConstraints);
        
//        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        Minimap minimap = new Minimap(model);
        minimap.setPreferredSize(new Dimension(200, 200));
        minimap.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        add(minimap, gridBagConstraints);
	}
	
	/*
	 * Creates the list which holds all the sprite views, and loads it with the 
	 * sprites already in the world.
	 */
	private void initObjectList() {
		objects = new ArrayList<ObjectRenderer<?>>();
		for(Sprite s : this.model.getWorld().getSprites()) {
			if(s instanceof Player) {
				objects.add(new PlayerView((Player)s));
			}else{
				objects.add(new SpriteView(s));
			}
		}
		for(Projectile p : this.model.getWorld().getProjectiles()) {
			objects.add(new ProjectileView(p));
		}
		for(Supply s : this.model.getWorld().getSupplies()){
			objects.add(new SupplyView(s));
		}
	}
	
	/*
	 * Creates the list which holds all the renderers of the tiles.
	 */
	private void initTileList() {
		tiles = new TileView[model.getWorld().getTiles().length][model.getWorld().getTiles()[0].length];
		for(int i = 0; i < model.getWorld().getTiles().length; i++) {
			for(int j = 0; j < model.getWorld().getTiles()[i].length; j++) {
				tiles[i][j] = new TileView(model.getWorld().getTiles()[i][j]);
			}
		}
	}
	
	private Position translatePos(Position pos) {
		return new Position((pos.getX() - camera.getX())/camera.getScale(), 
				(pos.getY() - camera.getY())/camera.getScale());
	}
	
	
	private int lastTime;
	private int lastFPS;
	private int tickFPS;
	private int getFPS() {
		int newTime = (int)(controller.getMsSinceStart()/1000);
		tickFPS++;
		if(lastTime != newTime) {
			lastFPS = tickFPS;
			tickFPS = 0;
			lastTime = newTime;
		}
		return lastFPS;
	}

	/**
	 * Draws everything.
	 */
	@Override
	public void paintComponent(Graphics g) {	
		//super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//Turn on anti alignment
		//Note: setting the hints to KEY_ANTIALIASING won't render the rotated images as such!
		g2d.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//Draws all the static world objects.
		Position drawMin = translatePos(new Position(0, 0));
		Position drawMax = translatePos(new Position(getWidth(), getHeight()));
		int tilesDrawn = 0;
		camera.setToCenter(model.getPlayer().getCenter(), getSize());
		for(int x = Math.max((int)drawMin.getX(), 0); x < Math.min(model.getWorld().getWidth(), drawMax.getX()); x++) {
			for(int y = Math.max((int)drawMin.getY(), 0); y < Math.min(model.getWorld().getHeight(), drawMax.getY()); y++) {
				tiles[x][y].render(g2d, camera.getOffset(), camera.getScale());
				tilesDrawn++;
			}
		}
		
		//Sets the player to the center of the screen.
		camera.setToCenter(model.getPlayer().getCenter(), getSize());
		//Draws all the dynamic items.
		for(ObjectRenderer<?> or : objects) {
			or.render(g2d, camera.getOffset(), camera.getScale());
		}
		//data:
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(0, 0, 600, 220);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		g.drawString("World size: " + model.getWorld().getTiles().length + "x" + model.getWorld().getTiles()[0].length, 10, 20);
		g.drawString("Number of updates since start (ctr): " + controller.getNbrOfUpdates() 
				+ ", average: " + controller.getNbrOfUpdates()/(int)(1 + controller.getMsSinceStart()/1000) + "/s", 10, 40);
		g.drawString("Number of updates since start (view): " + tick 
				+ ", average: " + tick/(int)(1 + controller.getMsSinceStart()/1000) + "/s", 10, 60);
		g.drawString("Number of projectiles in model: " + model.getWorld().getProjectiles().size(), 10, 80);
		g.drawString("Number of characters/sprites: " + model.getWorld().getSprites().size(), 10, 100);
		g.drawString("Time: " + (int)(controller.getMsSinceStart()/1000) + " s", 10, 120);
		g.drawString("Number of ObjectRenderers: " + this.objects.size(), 10, 140);
		g.drawString("Number of tiles drawn: " + tilesDrawn + "/" + (tiles.length*tiles[0].length), 10, 160);
		Position camPos = translatePos(new Position(getWidth()/2, getHeight()/2));
		g.drawString("Camera position: (" + (int)camPos.getX() + "," + (int)camPos.getY() + ")", 10, 180);
		g.drawString("FPS: " + this.getFPS() + " (max: ~" + 1000/SLEEP + ")" , 10, 200);
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
	public static void renderCollisionBox(Graphics g, model.geometrical.Position pos, int scale, CollisionBox box, Color colour, 
			boolean renderPosition, Color colourPosition) {
		g.setColor(colour);
		if(box != null) {
			for(java.awt.geom.Line2D r : box.getLines()) {
				g.drawLine((int)(r.getX1() * scale + pos.getX()), (int)(r.getY1() * scale + pos.getY()), 
						(int)(r.getX2() * scale + pos.getX()), (int)(r.getY2() * scale + pos.getY()));
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getPropertyName().equals(GameModel.ADDED_SPRITE)) {
			System.out.println("Added sprite caught by GamePanel");
			if(e.getNewValue() instanceof Player) {
				this.objects.add(new PlayerView((Player)e.getNewValue()));
			}else{
				this.objects.add(new SpriteView((Sprite)e.getNewValue()));
			}
		}else if(e.getPropertyName().equals(GameModel.REMOVED_SPRITE) ||
				e.getPropertyName().equals(GameModel.REMOVED_PROJECTILE)) {
			System.out.println("Removed sprite caught by GamePanel");
			for(ObjectRenderer<?> or : this.objects) {
				if(or.getObject() == e.getOldValue()) {
					objects.remove(or);
					break;
				}
			}
		}else if(e.getPropertyName().equals(GameModel.ADDED_PROJECTILE)) {
			System.out.println("Added projectile caught by GamePanel");
			this.objects.add(new ProjectileView((Projectile)e.getNewValue()));
		}else if(e.getPropertyName().equals(GameModel.ADDED_FOOD)){
			this.objects.add(new SupplyView((Supply)e.getNewValue()));
		}else if(e.getPropertyName().equals(GameModel.ADDED_AMMO)){
			this.objects.add(new SupplyView((Supply)e.getNewValue()));
		}else if(e.getPropertyName().equals(GameModel.ADDED_HEALTH)){
			this.objects.add(new SupplyView((Supply)e.getNewValue()));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.controller.handleMouseAt((float)(e.getX()-camera.getX())/camera.getScale(), 
				(float)(e.getY()-camera.getY())/camera.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.controller.handleMouseAt((float)(e.getX()-camera.getX())/camera.getScale(), 
				(float)(e.getY()-camera.getY())/camera.getScale());
	}
}
