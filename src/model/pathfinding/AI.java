package model.pathfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.geometrical.Position;
import model.sprites.Enemy;
import model.sprites.Player;
import model.sprites.Sprite;
import model.sprites.Sprite.State;
import model.world.Tile;
import model.world.World;

public class AI {
	private World world;
	private Player player;
	private EnemyPathfinder pathfinder;
	int pathfindingUpdateTick;
	
	/**
	 * The amount of updates in the gameModel it is between two pathfinding updates for an enemy.
	 */
	private final static int PATHFINDING_UPDATE_INTERVAL = 20;
	
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
	 * Update the movement and actions on all players.
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
					Enemy e = (Enemy) s;
					if(e.getState() == Enemy.State.RUNNING){
						if(getDistance(e.getCenter(), player.getCenter()) < 15){
							e.setWay(pathfinder.findWay(e.getCenter(), player.getCenter()));
						}else{
							//when an enemys pathfindinglist = null it will randomwalk
							e.setPathfindingList(null);
						}
					}else{
						if(getDistance(e.getCenter(), player.getCenter()) < 8 && 
								world.canMove(e.getCenter(), player.getCenter())){
							e.setState(Enemy.State.RUNNING);
							e.setWay(pathfinder.findWay(e.getCenter(), player.getCenter()));
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
	 * If the player is in range on an enemy's weapon, the enemy will shoot.
	 */
	private void enemyShoot() {
		List<Player> players = new ArrayList<Player>();
		for(Sprite s : world.getSprites()){
			if(s instanceof Player){
				players.add((Player)s);
			}else{//All players will be in the first places in sprites -> all players are in 
				//list players before we get into else
				for(Player p : players){
					float dx = s.getX() - p.getX();
					float dy = s.getY() - p.getY();
					float distance = (float) Math.sqrt(dx*dx+dy*dy);
					if(distance <= s.getActiveWeapon().getRange() + p.getHitBox().getWidth() && 
							world.canMove(s.getCenter(), p.getCenter())){
						world.addProjectile(s.getActiveWeapon().createProjectile(s.getDirection(), 
								s.getProjectileSpawn()));
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
