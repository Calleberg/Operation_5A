package model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.items.weapons.WeaponFactory;
import model.sprites.Player;
import model.world.World;
import model.world.WorldBuilder;



/**
 * This is the main model class. This will hold all data.
 * 
 * @author
 *
 */
public class GameModel {
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final World world;
	private final Player player;
	
	/**
	 * The message sent when a new sprite is added.
	 */
	public final static String ADDED_SPRITE = "addedsprite";
	/**
	 * The message sent when a sprite is removed.
	 */
	public final static String REMOVED_SPRITE = "removedsprit";
	/**
	 * The message sent when a projectile is added.
	 */
	public final static String ADDED_PROJECTILE = "addedprojectile";
	/**
	 * The message sent when a projectile is removed.
	 */
	public final static String REMOVED_PROJECTILE = "removedprojectile";

	/**
	 * Creates a new default game model.
	 */
	public GameModel(){
		world = new World();
		WorldBuilder wb = new WorldBuilder();
		world.setTiles(wb.getNewWorld(500, 500));
		player = new Player(50,50);
		player.setWeapon(WeaponFactory.createTestWeapon());
		world.addSprite(player);
	}
	
	/**
	 * Adds the specified listener.
	 * @param pcl the new listener.
	 */
	public void addListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
		this.world.addListener(pcl);
	}
	
	/**
	 * Removes the specified listener.
	 * @param pcl the listener to remove.
	 */
	public void removeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
		this.world.removeListener(pcl);
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
	}
	
	/**
	 * The Player fire his weapon.
	 */
	public void playerShoot(){
		world.addProjectile(player.getActiveWeapon().createProjectile(player.getDirection(), 
				player.getProjectileSpawn()));
	}
}
