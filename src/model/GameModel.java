package model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import model.geometrical.Position;
import model.pathfinding.EnemyPathfinder;
import model.sprites.Enemy;
import model.sprites.Player;
import model.sprites.Sprite;
import model.world.Tile;
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
	int pathfindingUpdateTick;
	private List<Tile> spawnPoints;
	
	/**
	 * The amount of updates in the gameModel it is between two pathfinding updates for an enemy.
	 */
	private final static int PATHFINDING_UPDATE_INTERVAL = 20;
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
	 * Creates a new default game model.
	 */
	public GameModel(){
		world = new World();
		WorldBuilder wb = new WorldBuilder();
		world.setTiles(wb.getNewWorld(500, 500));
		pathfinder = new EnemyPathfinder(world);
		spawnPoints = wb.getSpawnPoints();
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
		this.pathfindingUpdate();
		
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
	 * Return the spawnPoints for items.
	 * @return the spawnPoints for items.
	 */
	public List<Tile> getSpawnPoints(){
		return spawnPoints;
	}
	
	/**
	 * An enemy uses his weapon
	 */
	private void enemyShoot(Enemy e){
		world.addProjectile(e.getActiveWeapon().createProjectile(e.getDirection(), 
				e.getProjectileSpawn()));
	}
	
	private void pathfindingUpdate(){
		if(pathfindingUpdateTick < PATHFINDING_UPDATE_INTERVAL){
			for(Sprite s : world.getSprites()){
				if(s instanceof Enemy && (world.getSprites().indexOf(s)+pathfindingUpdateTick)%
						PATHFINDING_UPDATE_INTERVAL == 0){
					Enemy e = (Enemy) s;
					List<Position> list = pathfinder.findWay(e.getCenter(), player.getCenter());
					e.setWay(list);
				}
			}
		}else{
			pathfindingUpdateTick = 0;
		}
//		int enemyIndex = pathfindingUpdateTick%world.getSprites().size();
//		if(world.getSprites().get(enemyIndex) instanceof Enemy){
//			List<Position> list = pathfinder.findWay(world.getSprites().get(enemyIndex).getCenter(), 
//					player.getCenter());
//			Enemy e = (Enemy) world.getSprites().get(enemyIndex);
//			e.setWay(list);
//		}
//		pathfindingUpdateEnemyIndex++;
		pathfindingUpdateTick++;
	}
}
