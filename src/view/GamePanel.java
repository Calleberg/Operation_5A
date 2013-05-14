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

import controller.GameController;

import model.GameModel;
import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.items.weapons.Projectile;
import model.sprites.Player;
import model.sprites.Sprite;
import model.items.Item;
import model.items.Supply;

/**
 * 
 * 
 * @author 
 *
 */
public class GamePanel extends JPanel implements PropertyChangeListener, MouseMotionListener, Runnable {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	private GameController controller;
	private long tick = 0;
	private TileView[][] tiles;
	private List<ObjectRenderer<?>> objects;
	private Camera camera;
	private final int SLEEP = 1000 / 60;
	private volatile boolean paused = false;
	private volatile boolean isRunning = true;
	
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
	}
	
	@Override
	public void run() {
		while(isRunning) {
			runThread();
		}
	}
	private synchronized void runThread(){
		if (!paused) {
			repaint();
			tick++;
			try{
				Thread.sleep(SLEEP);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try{
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
        
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        Minimap minimap = new Minimap(model);
        minimap.setPreferredSize(new Dimension(200, 200));
        minimap.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        add(minimap, gridBagConstraints);
        
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(new BarPanel(model.getPlayer()), gridBagConstraints);
        
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(new ScorePanel(controller), gridBagConstraints);
        
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(new DevPanel(model, controller), gridBagConstraints);
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
		for(Item i : this.model.getWorld().getItems()){
			//objects.add(new SupplyView(i));

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
		camera.setToCenter(model.getPlayer().getCenter(), getSize());
		for(int x = Math.max((int)drawMin.getX(), 0); x < Math.min(model.getWorld().getWidth(), drawMax.getX()); x++) {
			for(int y = Math.max((int)drawMin.getY(), 0); y < Math.min(model.getWorld().getHeight(), drawMax.getY()); y++) {
				tiles[x][y].render(g2d, camera.getOffset(), camera.getScale());
			}
		}
		
		//Draws all the dynamic items.
		for(int i = objects.size() - 1; i >= 0; i--) {
			objects.get(i).render(g2d, camera.getOffset(), camera.getScale());
		}
		
		//data:
		g.setColor(Color.BLACK);
		g.drawString("Number of updates since start (view): " + tick 
<<<<<<< HEAD
				+ ", average: " + tick/(int)(1 + controller.getMsSinceStart()/1000) + "/s", 10, 150);
=======
				+ ", average: " + tick/(int)(1 + controller.getNumbersOfUpdates()/1000) + "/s", 10, 150);
>>>>>>> origin/Vidar
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
			if(e.getNewValue() instanceof Player) {
				this.objects.add(new PlayerView((Player)e.getNewValue()));
			}else{
				this.objects.add(new SpriteView((Sprite)e.getNewValue()));
			}
		}else if(e.getPropertyName().equals(GameModel.REMOVED_OBJECT)) {
			for(ObjectRenderer<?> or : this.objects) {
				if(or.getObject() == e.getOldValue()) {
					objects.remove(or);
					break;
				}
			}
		}else if(e.getPropertyName().equals(GameModel.ADDED_PROJECTILE)) {
			this.objects.add(new ProjectileView((Projectile)e.getNewValue()));
		}else if(e.getPropertyName().equals(GameModel.ADDED_SUPPLY)){
			this.objects.add(new ItemView((Item)e.getNewValue()));
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
	/**
	 * Pauses the thread from a running state. To resume the thread call <code>resumeThread()</code>.
	 */
	public synchronized void pauseThread(){
		paused=true;
	}
	/**
	 * Resumes the thread to a running state. To resume the thread call <code>pauseThread()</code>.
	 */
	public synchronized void resumeThread(){
		paused=false;
		notify();
	}

	public synchronized void stopThread() {
		isRunning=false;
		notify();		
	}
}
