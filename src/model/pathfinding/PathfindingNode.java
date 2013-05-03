package model.pathfinding;

import model.world.Tile;

public class PathfindingNode {
	private Tile tile;
	private PathfindingNode parentNode;
	private float distanceFromStart;
	private float distanceToGoal;
	
	public PathfindingNode(Tile t, /*float distanceToGoal,*/ float distanceFromParent){
		this.tile = t;
		setDistanceFromParent(distanceFromParent);
	}
	public void setParentNode(PathfindingNode n){
		this.parentNode = n;
		if(parentNode != null){
			this.distanceFromStart = getDistance(n.getTile(), tile) + n.getDistanceFromStart();
		}else{
			distanceFromStart = 0;
		}
	}
	
	public PathfindingNode getParentNode(){
		return parentNode;
	}
	public Tile getTile(){
		return tile;
	}
	public void setDistanceToGoal(float i){
		this.distanceToGoal = i;
	}
	
	public float getPathfindingDistance(){
		return distanceFromStart+distanceToGoal;
	}
	
	public float getDistanceToGoal(){
		return distanceToGoal;
	}
	public void setDistanceFromParent(float d){
		
	}
	public float getDistanceFromStart(){
		return distanceFromStart;
	}
	private float getDistance(Tile t1, Tile t2){
		float dx = Math.abs(t1.getX() - t2.getX());
		float dy = Math.abs(t1.getY() - t2.getY());
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
}
