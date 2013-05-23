package model.pathfinding;

import model.geometrical.Position;
import model.items.weapons.Projectile;
import model.sprites.Enemy;
import model.sprites.Player;
import model.sprites.Sprite;
import model.world.World;

/**
 * A class handling the enemy pathfinding as well as calculating when an enemy should attack.
 * 
 * @author Linus Hagvall
 *
 */
public class AI {
	private World world;
	private Player player;
	private EnemyPathfinder pathfinder;
	int pathfindingUpdateTick;
	
	/**
	 * The amount of updates in the gameModel it is between two pathfinding updates for an enemy.
	 */
	private final static int PATHFINDING_UPDATE_INTERVAL = 30;
	
	/**
	 * Handles all AI required for enemies. Only implemented to handle one player.
	 * @param world the world
	 * @param player the player
	 */
	public AI(World world, Player player){
		this.world = world;
		this.player = player;
		this.pathfinder = new EnemyPathfinder(world);
	}
	
	/**
	 * Update the movement and actions on all enemies.
	 */
	public void updateEnemies(){
		this.updateMovement();
		this.enemyShoot();
	}

	private void updateMovement(){
		if(pathfindingUpdateTick < PATHFINDING_UPDATE_INTERVAL){
			for(Sprite s : world.getSprites()){
				if(s instanceof Enemy && (world.getSprites().indexOf(s)+pathfindingUpdateTick)%
						PATHFINDING_UPDATE_INTERVAL == 0){
					//All enemies will update once every 20 updates, all enemies will have 
					//their updates spread out as much as possible.
					Enemy e = (Enemy) s;
					//If the Enemy is running, it won't loose track of the player until the 
					//distance is 15.
					if(e.getState() == Enemy.State.RUNNING){
						if(getDistance(e.getCenter(), player.getCenter()) < 15){
							e.setWay(pathfinder.findWay(e, player));
						}else{
							//when an enemys pathfindinglist = null it will randomwalk
							e.setPathfindingList(null);
						}
					}else{//If an enemy isn't running it wont discover the player until the 
						//distance is less than eight and there is a clear sight of the player.
						if(getDistance(e.getCenter(), player.getCenter()) < 8 && 
								world.canMove(e.getCenter(), player.getCenter())){
							e.setState(Enemy.State.RUNNING);
							e.setWay(pathfinder.findWay(e, player));
						}else{
							//when an enemys pathfindinglist = null it will randomwalk
							e.setPathfindingList(null);
						}
					}
				}
			}
		}else{
			pathfindingUpdateTick = 0;
		}
		pathfindingUpdateTick++;
	}
	
	/**
	 * If the player is in range on an enemy's weapon and there is a clear sight between
	 * the enemy and the player, the enemy will shoot.
	 */
	private void enemyShoot() {
		for(Sprite s : world.getSprites()){
			if(s instanceof Enemy){
				if(getDistance(s.getPosition(), player.getPosition()) <= 
						s.getActiveWeapon().getRange() + player.getHitBox().getWidth() &&
						world.canMove(s.getCenter(), player.getCenter())){
					Projectile projectile = s.getActiveWeapon().createProjectile(s.getDirection(), 
							s.getProjectileSpawn());
					if(projectile != null) {
						world.addProjectile(projectile);
						s.fireEvent(Sprite.EVENT_USE_WEAPON);
					}
				}
			}
		}
	}
	
	private float getDistance(Position p1, Position p2){
		float dx = Math.abs(p1.getX() - p2.getX());
		float dy = Math.abs(p1.getY() - p2.getY());
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
}
