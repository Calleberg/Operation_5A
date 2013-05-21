package model.pathfinding;

import model.geometrical.Position;
import model.world.Tile;
/**
 * Hold all data for a node which is needed to calculate pathfinding.
 * 
 * @author
 *
 */
public class PathfindingNode {
	private Tile tile;
	private PathfindingNode parentNode;
	private float distanceFromStart;
	private float distanceToGoal;
	
	/**
	 * Create a new node with data needed to calculate the shortest path between two positions.
	 * @param t the tile this node represents.
	 */
	public PathfindingNode(Tile t){
		this.tile = t;
	}
	
	/**
	 * Set the parent node.
	 * @param n the parentNode
	 */
	public void setParentNode(PathfindingNode n){
		this.parentNode = n;
		if(parentNode != null){
			this.distanceFromStart = getDistance(n.getTile(), tile) + n.getDistanceFromStart();
		}else{
			distanceFromStart = 0;
		}
	}
	
	/**
	 * Return the parent node.
	 * @return the parent node.
	 */
	public PathfindingNode getParentNode(){
		return parentNode;
	}
	
	/**
	 * Return the tile this node represents.
	 * @return the tile this node represents.
	 */
	public Tile getTile(){
		return tile;
	}
	
	/**
	 * Set the distance to goal.
	 * @param goal the pathfindingNode which is the goal.
	 */
	public void setDistanceToGoal(PathfindingNode goal){
		this.distanceToGoal = getDistance(tile, goal.getTile());
	}
	
	/**
	 * Return the pathfinding distance.
	 * @return the pathfinding distance.
	 */
	public float getPathfindingDistance(){
		return distanceFromStart+distanceToGoal;
	}
	
	/**
	 * Return the distance to the goal.
	 * @return the distance to the goal.
	 */
	public float getDistanceToGoal(){
		return distanceToGoal;
	}
	
	/**
	 * Return the distance from start.
	 * @return the distance from start.
	 */
	public float getDistanceFromStart(){
		return distanceFromStart;
	}
	
	/**
	 * Return the distance between two tiles.
	 * @param t1 one of the tiles to calculate the distance between.
	 * @param t2 the other tile to calculate the distance between.
	 * @return the distance between two tiles.
	 */
	private float getDistance(Tile t1, Tile t2){
		float dx = Math.abs(t1.getPosition().getX() - t2.getPosition().getX());
		float dy = Math.abs(t1.getPosition().getY() - t2.getPosition().getY());
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
	
	/**
	 * Return the center of the Node.
	 * @return the center of the Node.
	 */
	public Position getCenter(){
		return new Position(tile.getPosition().getX() + 0.5f, 
				tile.getPosition().getY() + 0.5f);
	}
}
