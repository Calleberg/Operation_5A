package model;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import model.sprites.Player;
import model.world.Tile;
import model.world.World;



/**
 * This is the main model class. This will hold all data.
 * 
 * @author
 *
 */
public class GameModel implements PropertyChangeListener {
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private World world;
	private List<Tile> spawnPoints;
	private int score;
	private long gameTime;
	
	/**
	 * The message sent when a new sprite is added.
	 */
	public final static String ADDED_SPRITE = "addedsprite";
	/**
	 * The message sent when an object is removed.
	 */
	public final static String REMOVED_OBJECT = "removeobject";
	/**
	 * The message sent when a projectile is added.
	 */
	public final static String ADDED_PROJECTILE = "addedprojectile";
	/**
	 * The message sent when a food supply is added
	 */
	public final static String ADDED_SUPPLY = "addedsupply";

	/**
	 * Creates a new model with the specified world.
	 * @param world the world in this model.
	 */
	public GameModel(World world) {
		this.world = world;
		this.world.addListener(this);
	}
	
	/**
	 * Sets a list of all the spawn points in the world.
	 * @param spawns the spawn points.
	 */
	public void setSpawns(List<Tile> spawns) {
		this.spawnPoints = spawns;
	}
	
	/**
	 * Gives the current score of the game.
	 * @return the score of the game.
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Adds the specified score to the total score.
	 * @param score the score to add.
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * Gives the total time the game has been running.
	 * @return the total time the game has been running.
	 */
	public long getGameTime() {
		return this.gameTime;
	}
	
	/**
	 * Sets the time the game has been running.
	 * @param gameTime the time the game has been running.
	 */
	public void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}
	
	/**
	 * Sets the player of the game.
	 * @param player the player of the game.
	 */
	public void setPlayer(Player player) {
		world.setPlayer(player);
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
		return world.getPlayer();
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
	 * Return the spawnPoints for items.
	 * @return the spawnPoints for items.
	 */
	public List<Tile> getSpawnPoints(){
		return spawnPoints;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//Sends the event down
		this.pcs.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
	}
}
