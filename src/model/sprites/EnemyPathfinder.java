package model.sprites;

import java.util.ArrayList;
import java.util.List;

import model.geometrical.Position;
import model.world.Tile;

public class EnemyPathfinder {
	private List<PathfindingNode> openTileList;
	private List<PathfindingNode> closedTileList;
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
//		for(int i = 0; i<tiles.length; i++){
//			for(int j = 0; j<tiles[i].length; j++){
//				this.nodes[i][j].setDistanceToGoal(getDistance(tiles[i][j], goal));//TODO samma som ovan
//			}
//		}
		closedTileList = new ArrayList<PathfindingNode>();
		openTileList = new ArrayList<PathfindingNode>();
		this.goal = new PathfindingNode(goal, 0);//TODO kolla över andra "0"
		openTileList.add(nodes[(int)t.getX()][(int)t.getY()]);
		currentNode = openTileList.get(0);
		
		while(currentNode.getTile().getX() != this.goal.getTile().getX() &&
				currentNode.getTile().getY() != this.goal.getTile().getY()){
			findWay(t);
		}
		
		if(closedTileList.size() > 2){
			closedTileList.remove(0);//removes the tile the enemy currently stands on.
			closedTileList.remove(0);//removes the tile the enemy currently stands on.
			closedTileList.remove(0);//removes the tile the enemy currently stands on.
		}

		return closedTileList;
	}
	private void findWay(Tile t){

		PathfindingNode lowF = openTileList.get(0);
		for(PathfindingNode node : openTileList){
			if(currentNode != null){
				node.setDistanceFromParent(getDistance(node.getTile(), currentNode.getTile()));
				node.setDistanceToGoal(getDistance(node.getTile(), goal.getTile()));
			}
			if(node.getPathfindingDistance() < lowF.getPathfindingDistance() && node != currentNode){
				lowF = node;
			}
		}
		lowF.setParentNode(currentNode);
		currentNode = lowF;
		closedTileList.add(currentNode);
		openTileList.remove(lowF);
		
		openTileList.addAll(surroundingTiles(currentNode));
	}
	private List<PathfindingNode> surroundingTiles(PathfindingNode t){
		List<PathfindingNode> surroundingTiles = new ArrayList<PathfindingNode>();
		for(float x = currentNode.getTile().getX() - 1; x < currentNode.getTile().getX() + 2; x++){
			for(float y = currentNode.getTile().getY() - 1; y < currentNode.getTile().getY() + 2; y++){
				//TODO if blocked aswell
				if(!(closedTileList.contains(nodes[(int)x][(int)y])) && true){//&& walkable?
						openTileList.add(nodes[(int)x][(int)y]);
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
