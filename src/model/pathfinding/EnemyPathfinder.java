package model.pathfinding;

import java.util.ArrayList;
import java.util.List;

import model.geometrical.Position;
import model.world.Tile;
import model.world.World;

public class EnemyPathfinder {
	private List<PathfindingNode> openTileList;
	private List<PathfindingNode> closedTileList;
	private PathfindingNode goal;
	private PathfindingNode currentNode;
	private PathfindingNode[][] nodes;
	private World world;
	int test = 0;
	
	public EnemyPathfinder(World world){
		this.world = world;
		Tile[][] tempTiles = world.getTiles();
		nodes = new PathfindingNode[tempTiles.length][tempTiles[0].length];
		for(int i = 0; i<tempTiles.length; i++){
			for(int j = 0; j<tempTiles[i].length; j++){
				this.nodes[i][j] = new PathfindingNode(tempTiles[i][j], 0);//TODO samma som ovan
			}
		}
	}
	public List<PathfindingNode> findWay(Tile t, Tile goal, Tile[][] tiles){
		closedTileList = new ArrayList<PathfindingNode>();
		openTileList = new ArrayList<PathfindingNode>();
//		this.goal = new PathfindingNode(goal, 0);//TODO kolla över andra "0"
		this.goal = nodes[(int)goal.getX()][(int)goal.getY()];
		openTileList.add(nodes[(int)t.getX()][(int)t.getY()]);
		currentNode = openTileList.get(0);
		currentNode.setParentNode(null);
		
		System.out.print("current x " + currentNode.getTile().getX());
		System.out.print("current y " + currentNode.getTile().getY());
		System.out.print("goal x " + this.goal.getTile().getX());
		System.out.print("goal y " + this.goal.getTile().getY());
		
		while(currentNode.getTile().getX() != this.goal.getTile().getX() ||
				currentNode.getTile().getY() != this.goal.getTile().getY()){
			findWay(t);
		}
		
//		if(closedTileList.size() > 2){
//			closedTileList.remove(0);//removes the tile the enemy currently stands on.
//			closedTileList.remove(0);//removes the tile the enemy currently stands on.
//			closedTileList.remove(0);//removes the tile the enemy currently stands on.
//		}
		List<PathfindingNode> correctList = new ArrayList<PathfindingNode>();
		correctList.add(currentNode);
		while(currentNode.getParentNode() != null){
			correctList.add(currentNode.getParentNode());
			currentNode = currentNode.getParentNode();
		}
		
		List<PathfindingNode> correctList2 = new ArrayList<PathfindingNode>();
		for(int i = correctList.size()-1; i>-1; i--){
			correctList2.add(correctList.get(i));
		}
		System.out.println(correctList.size());
		for(PathfindingNode n : correctList2){
			System.out.println("x " + n.getTile().getX() + " y " + n.getTile().getY() + " goal " + n.getDistanceToGoal());
		}
		System.out.println("antal varv " + test);
		return correctList2;
//		return closedTileList;
	}
	private void findWay(Tile t){
		test++;
		PathfindingNode lowF = openTileList.get(0);
		for(PathfindingNode node : openTileList){
			if(currentNode != null){
//				node.setDistanceFromParent(getDistance(node.getTile(), currentNode.getTile()));
				node.setDistanceToGoal(getDistance(node.getTile(), goal.getTile()));
			}
			if(node.getPathfindingDistance() < lowF.getPathfindingDistance() && 
					node != currentNode){
				lowF = node;
			}
		}
		System.out.println("pathfindingdistance " + lowF.getPathfindingDistance());
		System.out.println("pathfinding goal " + lowF.getDistanceToGoal());
		System.out.println("pathfinding start " + lowF.getDistanceFromStart());
//		lowF.setParentNode(currentNode);
//		if(lowF.getParentNode() != currentNode){
//			int index = closedTileList.indexOf(lowF.getParentNode());
//			index = index + 1;
//		}
		currentNode = lowF;
		System.out.println("currentnode " + currentNode.getTile().getX() + " y " + currentNode.getTile().getY());
//		System.out.println("openlist size " + openTileList.size());
		closedTileList.add(currentNode);
		openTileList.remove(lowF);
		//ändra väg om inlurad i hörn
		openTileList.addAll(surroundingTiles(currentNode));
	}
	private List<PathfindingNode> surroundingTiles(PathfindingNode t){
		List<PathfindingNode> surroundingTiles = new ArrayList<PathfindingNode>();
		for(float x = currentNode.getTile().getX() - 1; x < currentNode.getTile().getX() + 2; x++){
			for(float y = currentNode.getTile().getY() - 1; y < currentNode.getTile().getY() + 2; y++){
//				System.out.println("openlist possible" + x + " y " + y);
				//TODO if blocked aswell
				if(!(closedTileList.contains(nodes[(int)x][(int)y])) && 
						world.canMove(currentNode.getTile().getPosition(), nodes[(int)x][(int)y].getTile().getPosition())){//&& walkable?
//					System.out.println("openlist possible2" + x + " y " + y);
					if((openTileList.contains(nodes[(int)x][(int)y]))){
						if(world.canMove(currentNode.getTile().getPosition(), nodes[(int)x][(int)y].getTile().getPosition())
								&& currentNode.getDistanceFromStart()+
								getDistance(currentNode.getTile(), nodes[(int)x][(int)y].getTile()) < nodes[(int)x][(int)y].getDistanceFromStart()){
							nodes[(int)x][(int)y].setParentNode(currentNode);
							nodes[(int)x][(int)y].setDistanceFromParent(getDistance(currentNode.getTile(), nodes[(int)x][(int)y].getTile()));
						}
					}else{
//						System.out.println("openlist add" + x + " y " + y);
						nodes[(int)x][(int)y].setParentNode(currentNode);
						openTileList.add(nodes[(int)x][(int)y]);
					}
				}
			}
		}
		return surroundingTiles;
	}
	
	
	private float getDistance(Tile t1, Tile t2){
		float dx = Math.abs(t1.getX() - t2.getX());
		float dy = Math.abs(t1.getY() - t2.getY());
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
	
	
}
