package model.pathfinding;

import model.world.Tile;

public class PathfindingNode {
	private Tile tile;
	private PathfindingNode parentNode;
	private float distanceFromStart;
	private float distanceToGoal;
	
	public PathfindingNode(Tile t, /*float distanceToGoal,*/ float distanceFromParent){
		this.tile = t;
//		this.distanceToGoal = distanceToGoal;
//		this.distanceFromStart = calculateG(this);
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
//	public void calculateG(){
//		distanceFromStart = calculateG(this);
//	}
//	private float calculateG(PathfindingNode node){
//		float g = 0f;
//		PathfindingNode tmpParent = node.getParentNode();
//		while(tmpParent != null){
////			System.out.println("calculateG " + node.getParentNode().getTile().getX());
//			g = g + calculateG(node.getParentNode());
//		}
////		return g;
//		return 0f;
//	}
	
	public float getDistanceToGoal(){
		return distanceToGoal;
	}
	public void setDistanceFromParent(float d){
//		if(this.getParentNode() != null){
//			distanceFromStart = d + this.getParentNode().getDistanceFromStart();
//		}
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
