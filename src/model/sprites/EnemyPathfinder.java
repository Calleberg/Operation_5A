package model.sprites;

import java.util.ArrayList;
import java.util.List;

import model.geometrical.Position;
import model.world.Tile;

public class EnemyPathfinder {
	private List<PathfindingNode> openTileList/* = new ArrayList<PathfindingNode>()*/;
	private List<PathfindingNode> closedTileList/* = new ArrayList<PathfindingNode>()*/;
	private PathfindingNode goal;
	private PathfindingNode currentNode;
	private PathfindingNode[][] nodes;
	
	public EnemyPathfinder(Tile[][] tiles){
		nodes = new PathfindingNode[tiles.length][tiles[0].length];
		for(int i = 0; i<tiles.length; i++){
			for(int j = 0; j<tiles[i].length; j++){
				this.nodes[i][j] = new PathfindingNode(tiles[i][j], 0);//TODO samma som ovan
			}
		}
	}
	public List<PathfindingNode> findWay(Tile t, Tile goal, Tile[][] tiles){
		for(int i = 0; i<tiles.length; i++){
			for(int j = 0; j<tiles[i].length; j++){
				this.nodes[i][j].setDistanceToGoal(getDistance(tiles[i][j], goal));//TODO samma som ovan
			}
		}
		closedTileList = new ArrayList<PathfindingNode>();
		openTileList = new ArrayList<PathfindingNode>();
		this.goal = new PathfindingNode(goal, 0);//TODO kolla över andra "0"
//		nodes = new PathfindingNode[tiles.length][tiles[0].length];
//		for(int i = 0; i<tiles.length; i++){
//			for(int j = 0; j<tiles[i].length; j++){
//				this.nodes[i][j] = new PathfindingNode(tiles[i][j],
//						getDistance(tiles[i][j], this.goal.getTile()), 0);//TODO samma som ovan
//			}
//		}
//		openTileList.add(new PathfindingNode(t, getDistance(t, this.goal.getTile()), 0));
		openTileList.add(nodes[(int)t.getX()][(int)t.getY()]);
		currentNode = openTileList.get(0);
		
//		while(Math.abs(currentNode.getTile().getX() - this.goal.getTile().getX()) > 0.05 ||
//				Math.abs(currentNode.getTile().getY() - this.goal.getTile().getY()) > 0.05){
////			System.out.println("test");
////			System.out.println("currentX: " + currentNode.getTile().getX());
////			System.out.println("goalX: " + this.goal.getTile().getX());
////			System.out.println("currentY: " + currentNode.getTile().getY());
////			System.out.println("goalY: " + this.goal.getTile().getY());		
//			findWay(t);
//		}
		while(currentNode.getTile().getX() != this.goal.getTile().getX() &&
				currentNode.getTile().getY() != this.goal.getTile().getY()){
			findWay(t);
		}
		
		if(closedTileList.size() > 2){
			closedTileList.remove(0);//removes the tile the enemy currently stands on.
			closedTileList.remove(0);//removes the tile the enemy currently stands on.
			closedTileList.remove(0);//removes the tile the enemy currently stands on.
		}
		for(PathfindingNode n : closedTileList){
			System.out.println("X: " + n.getTile().getX() + "Y: " + n.getTile().getY());
		}
		for(PathfindingNode n : closedTileList){
//			System.out.println("n " + n.getTile().getY());
		}
		return closedTileList;
	}
	private void findWay(Tile t){
//		this.tiles = tiles;
//		this.goal = new PathfindingNode(goal, 0);
		
		
		
//		float lowF = openTileList.get(0).getF();
		PathfindingNode lowF = openTileList.get(0);
//		int lowFIndex = 0;
		for(PathfindingNode node : openTileList){
//			System.out.println(node.getPathfindingDistance());
//			System.out.println(node.getDistanceFromStart());
//			System.out.println(node.getDistanceToGoal());
//			node.calculateG();
			if(currentNode != null){
				node.setDistanceFromParent(getDistance(node.getTile(), currentNode.getTile()));
			}
			if(node.getPathfindingDistance() < lowF.getPathfindingDistance() && node != currentNode){
				
				lowF = node;
//				lowFIndex = i;
			}
		}
//		currentNode = openTileList.get(lowFIndex);
		lowF.setParentNode(currentNode);
		currentNode = lowF;
//		System.out.println("currentNode: " + currentNode.getTile().getX() + " " + currentNode.getTile().getY());
		closedTileList.add(currentNode);
		openTileList.remove(lowF);
		
		openTileList.addAll(surroundingTiles(currentNode));
	}
	private List<PathfindingNode> surroundingTiles(PathfindingNode t){
//		System.out.println("F" + currentNode.getPathfindingDistance());
//		System.out.println(currentNode.getTile().getX() + " " + currentNode.getTile().getY());
		List<PathfindingNode> surroundingTiles = new ArrayList<PathfindingNode>();
		for(float x = currentNode.getTile().getX() - 1; x < currentNode.getTile().getX() + 2; x++){
			for(float y = currentNode.getTile().getY() - 1; y < currentNode.getTile().getY() + 2; y++){
				//TODO if blocked aswell
//				System.out.println("x: " + nodes[(int)x][(int)y].getTile().getX() + "y: " + nodes[(int)x][(int)y].getTile().getY());
				if(!(closedTileList.contains(nodes[(int)x][(int)y])) && true){//&& walkable?
//					if(openTileList.contains(nodes[(int)x][(int)y])){
//						if(nodes[(int)x][(int)y].getPathfindingDistance() > ){
//							//TODO check G-score	
//						}
//					}else{
//					System.out.println("Test " + x + " " + y);
						openTileList.add(nodes[(int)x][(int)y]);
//					}
					nodes[(int)x][(int)y].setParentNode(currentNode);
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
