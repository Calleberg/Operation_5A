package model.pathfinding;
//TODO lose track of player
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
//TODO remove distance check in pathfinder and change maxdistance way to 15 from 25
	private void updateMovement(){
		if(pathfindingUpdateTick < PATHFINDING_UPDATE_INTERVAL){
			for(Sprite s : world.getSprites()){
				if(s instanceof Enemy && (world.getSprites().indexOf(s)+pathfindingUpdateTick)%
						PATHFINDING_UPDATE_INTERVAL == 0){
					Enemy e = (Enemy) s;
					if(e.getState() == Enemy.State.RUNNING){
						if(getDistance(e.getCenter(), player.getCenter()) < 15){
							e.setWay(pathfinder.findWay(e.getCenter(), player.getCenter()));
							setDirectionTowardsList(e);
						}else{
							setRandomDirection(e);
						}
					}else{
						if(getDistance(e.getCenter(), player.getCenter()) < 8 && 
								world.canMove(e.getCenter(), player.getCenter())){
							e.setState(Enemy.State.RUNNING);
							e.setWay(pathfinder.findWay(e.getCenter(), player.getCenter()));
							setDirectionTowardsList(e);
						}else{
							setRandomDirection(e);
						}
					}
//					if(e.getPathfindingList() != null){
//						setDirectionTowardsList(e);
//					}
				}
			}
		}else{
			pathfindingUpdateTick = 0;
		}
		pathfindingUpdateTick++;
	}
	
	
	private void setDirectionTowardsList(Enemy e){
		//If the enemy have moved to the end of the list, stand still.
		if(e.getPathfindingList().size() <= e.getPathfindingListIndex()){
			e.setState(Enemy.State.STANDING);
			return;//TODO varför behövs detta?
		}
		
		//If the enemy is close to the current position in the pathfindingList set the direction,
		//otherwise increase the pathfindingListIndex and the set the direction.
		if(Math.abs(e.getCenter().getX() - 
				(e.getPathfindingList().get(e.getPathfindingListIndex()).getX())) > 0.02
				|| Math.abs(e.getCenter().getY() - 
				(e.getPathfindingList().get(e.getPathfindingListIndex()).getY())) > 0.02){
			setDirection(e);
		}else{
			e.setPathfindingListIndex(e.getPathfindingListIndex() + 1);
			
			//if the pathfindingListIndex increases over size of list, stand still.
			if(!(e.getPathfindingList().size()<=e.getPathfindingListIndex())){
				setDirection(e);
			}else{
				e.setState(Enemy.State.STANDING);
			}
		}
	}
	
	private void setDirection(Enemy e){
		float dx = (float) (e.getCenter().getX() - 
				(e.getPathfindingList().get(e.getPathfindingListIndex()).getX()));
		float dy = (float) (e.getCenter().getY() - 
				(e.getPathfindingList().get(e.getPathfindingListIndex()).getY()));
		float sin = (float) Math.asin((float) (dy/Math.sqrt(dx*dx+dy*dy)));
		if(dx>0){
			e.setDirection((float)Math.PI - sin);
		}else{
			e.setDirection(sin);
		}
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
	
	private void setRandomDirection(Enemy e){
		Random random = new Random();
		if(random.nextFloat() > 0.95){
			int randomNumber = random.nextInt(100);
			float randomDirection = (float) (randomNumber * (2*Math.PI/(100)));
			e.setDirection(randomDirection);
			e.setState(Enemy.State.WALKING);

		}else if(random.nextFloat() > 0.98){
			e.setState(Enemy.State.STANDING);
		}
	}
}
