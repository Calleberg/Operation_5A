package model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import model.pathfinding.EnemyPathfinder;
import model.pathfinding.PathfindingNode;
import model.sprites.Enemy;
import model.sprites.Player;
import model.sprites.Sprite;
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
	private Player player;
	private EnemyPathfinder pathfinder;
	int tick = 30;
	int max = 30;
	
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
		pathfinder = new EnemyPathfinder(world);
		world.setSpawnPoints(wb.getSpawnPoints());
	}
	
	/**
	 * Sets the player of the game.
	 * @param player the player of the game.
	 */
	public void setPlayer(Player player) {
		world.removeSprite(player);
		this.player = player;
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
		if(tick >= max){
			this.pathfindingUpdate();
			tick = 0;
		}
		tick++;
		//at the moment every enemy attack with a melee weapon as often as possible
		for (Sprite s : world.getSprites()){
			if(s instanceof Enemy){
				Enemy e = (Enemy) s;
				enemyShoot(e);
			}
		}
		world.update();
	}
	
	/**
	 * The Player fire his weapon.
	 */
	public void playerShoot(){
		world.addProjectile(player.getActiveWeapon().createProjectile(player.getDirection(), 
				player.getProjectileSpawn()));
	}
	
	/**
	 * An enemy uses his weapon
	 */
	private void enemyShoot(Enemy e){
		world.addProjectile(e.getActiveWeapon().createProjectile(e.getDirection(), 
				e.getProjectileSpawn()));
	}
	
	private void pathfindingUpdate(){
		for(Sprite s : world.getSprites()){
			if(s instanceof Enemy){
				List<PathfindingNode> list = pathfinder.findWay(world.getTiles()[(int)s.getX()][(int)s.getY()], 
						world.getTiles()[(int)player.getCenter().getX()][(int)player.getCenter().getY()], world.getTiles());
				Enemy e = (Enemy) s;
				e.setWay(list);
			}
		}
	}
}
