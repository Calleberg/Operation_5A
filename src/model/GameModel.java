package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import sprite.Player;
import world.World;
import world.WorldBuilder;

/**
 * 
 * 
 * @author
 *
 */
public class GameModel {
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final World world;
	private final Player player;
	
	/**
	 * Creates a new default game model.
	 */
	public GameModel(){
		world = new World();
		WorldBuilder wb = new WorldBuilder();
		world.setTiles(wb.getNewWorld(40, 40));
		player = new Player(1,1);
		world.addSprite(player);
	}
	
	/**
	 * Adds the specified listener.
	 * @param pcl the new listener.
	 */
	public void addListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Removes the specified listener.
	 * @param pcl the listener to remove.
	 */
	public void removeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
	}
	
	/**
	 * Gives the player this model holds.
	 * @return the player this model holds.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Gives the world this model holds.
	 * @return the world this model holds.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Updates the model.
	 */
	public void update() {
		world.update();
		pcs.firePropertyChange("", 1, 0);
	}
	
	/**
	 * The Player fire his weapon.
	 */
	public void playerShoot(){
		//this.world.createProjectile(player);
	}
}
