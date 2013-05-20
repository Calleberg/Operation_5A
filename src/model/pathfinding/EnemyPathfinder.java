package model.pathfinding;
//TODO if adjustmovesprite doesnt work when an enemy take a straight path, test adjustedmove and
//set a point at half distance with adjustedmovesprite, repeat until free spot or close distance
import java.util.ArrayList;
import java.util.List;

import model.geometrical.Position;
import model.sprites.Sprite;
import model.world.Tile;
import model.world.World;

public class EnemyPathfinder {
	private List<PathfindingNode> openTileList;
	private List<PathfindingNode> closedTileList;
	private PathfindingNode goal;
	private PathfindingNode currentNode;
	private PathfindingNode[][] nodes;
	private World world;
	private boolean noWayFound = false;
	private Sprite[] excludingSprites;
	private Position start;
	
	/**
	 * This class is used to find the fastest way between two positions.
	 * @param world the world in which path should be found.
	 */
	public EnemyPathfinder(World world){
		this.world = world;
		excludingSprites = new Sprite[2];
		Tile[][] tempTiles = world.getTiles();
		nodes = new PathfindingNode[tempTiles.length][tempTiles[0].length];
		for(int i = 0; i<tempTiles.length; i++){
			for(int j = 0; j<tempTiles[i].length; j++){
				this.nodes[i][j] = new PathfindingNode(tempTiles[i][j]);
			}
		}
	}
	
	public List<Position> findWay(Sprite start, Sprite goal){
		excludingSprites[0] = start;
		excludingSprites[1] = goal;
		List<Position> temp = findWay(start.getCenter(), goal.getCenter());
		excludingSprites[0] = null;
		excludingSprites[1] = null;
		
		return temp;
	}
	
	/**
	 * Return a list of Positions containing the fastest way from p to goal. A way will
	 * never contain a position which is longer than 25 away from the start position.
	 * @param start the start position.
	 * @param goal The end position.
	 * @return a list of Positions containing the fastest way from start to goal.
	 */
	public List<Position> findWay(Position start, Position goal){
		this.start = start;
		noWayFound = false;
		closedTileList = new ArrayList<PathfindingNode>();
		openTileList = new ArrayList<PathfindingNode>();
		this.goal = nodes[(int)goal.getX()][(int)goal.getY()];
		openTileList.add(nodes[(int)start.getX()][(int)start.getY()]);
		currentNode = openTileList.get(0);
		currentNode.setParentNode(null);
		
		
		//Looping through the tiles to find the fastest way. End the loop if the fastest way is found, 
		//no way was found or if there is a straight way found towards the goal.
		while(((currentNode.getTile().getX() != this.goal.getTile().getX() ||
				currentNode.getTile().getY() != this.goal.getTile().getY())) && !noWayFound && 
				!(adjustedCanMove(currentNode.getCenter(), this.goal.getCenter()))){
			findWay(start);
		}
		
		//if noWayFound set the direction straight towards the goal. This won't solve the problem but
		//in most cases if will be better than standing stil.
		if(noWayFound){
			List<Position> l = new ArrayList<Position>();
			l.add(goal);
			return l;
		}
		
		//if there exist a straight way found from the currentNode towards the goal, 
		//add the goal as currentNode.
		if(currentNode.getTile().getX() != this.goal.getTile().getX() || 
				currentNode.getTile().getY() != this.goal.getTile().getY()){
			this.goal.setParentNode(currentNode);
			currentNode = this.goal;
		}
		
		List<Position> correctList = createListOfPositions();
		
		//This is necessary to stop enemies from getting stuck next to some props. 
		if(!adjustedCanMove(start, correctList.get(0))){
			correctList.remove(0);
		}
		
		//Currently the last position is the center of the node the player stands on(this.goal, last index),
		//switch the last position the the playerCenter(goal) instead 
		correctList.remove(correctList.size()-1);
		correctList.add(goal);
		
		removeUnnecessaryPositions(correctList);
		
		return correctList;
	}
	
	/**
	 * Calculates the next currentNode. Also updates openTileList and closedTileList.
	 * @param t the starting position
	 */
	private void findWay(Position t){
		//if all possible tiles are tried openTileList will be empty -> noWayFound.
		if(openTileList.size() == 0){
			noWayFound = true;
			return;
		}
		
		//picks out the pathfindingNode with the lowest pathfindingDistance.
		PathfindingNode lowPathfindingDistance = openTileList.get(0);
		for(PathfindingNode node : openTileList){
			if(currentNode != null){
				node.setDistanceToGoal(goal);
			}
			if(node.getPathfindingDistance() < lowPathfindingDistance.getPathfindingDistance() && 
					node != currentNode){
				lowPathfindingDistance = node;
			}
		}
		
		currentNode = lowPathfindingDistance;
		closedTileList.add(currentNode);
		openTileList.remove(currentNode);
		
		openTileList.addAll(surroundingTiles(currentNode));
	}
	
	/**
	 * Return all surrounding tiles around t which is possible to be currentNode.
	 * @param t the pathfindingNode to get all surrounding tiles around.
	 * @return all surrounding tiles around t which is possible to be currentNode
	 */
	private List<PathfindingNode> surroundingTiles(PathfindingNode t){//TODO comments
		List<PathfindingNode> surroundingTiles = new ArrayList<PathfindingNode>();
		for(float x = currentNode.getTile().getX() - 1; x < currentNode.getTile().getX() + 2; x++){
			for(float y = currentNode.getTile().getY() - 1; y < currentNode.getTile().getY() + 2; y++){
				if(x >= 0 && y >= 0 && getDistance(nodes[(int)x][(int)y].getTile().getPosition(), 
						goal.getTile().getPosition()) < 15){
					if(!(closedTileList.contains(nodes[(int)x][(int)y])) && 
							adjustedCanMove(currentNode.getCenter(), 
									nodes[(int)x][(int)y].getCenter())){
						if((openTileList.contains(nodes[(int)x][(int)y]))){
							if(currentNode.getDistanceFromStart()+getDistance(
									currentNode.getTile().getPosition(), 
									nodes[(int)x][(int)y].getTile().getPosition()) < 
									nodes[(int)x][(int)y].getDistanceFromStart()){
								nodes[(int)x][(int)y].setParentNode(currentNode);
							}
						}else{
							nodes[(int)x][(int)y].setParentNode(currentNode);
							openTileList.add(nodes[(int)x][(int)y]);
						}
					}
				}
			}
		}
		return surroundingTiles;
	}
	
	//TODO dynamic pathWidth.
	/*
	 * Returns if there is a straight path between two positions with a 0.7 width.
	 * Does not take other sprites into account.
	 * @param start the start position.
	 * @param goal the end position.
	 * @return true if there is a path, false otherwise.
	 */
	private boolean adjustedCanMove(Position start, Position goal){
		float halfPathWidth = 0.35f;
		//The enemy will only consider sprites very close to him and will not get it's path
		//changed because of another sprite far away. Without this enemies would act strange
		//if another enemy chased the player through a door.
//		if(this.start.getX() - start.getX() < 0.5 && this.start.getY() - start.getY() < 0.5  && 
//				getDistance(this.start, goal) < 1){
//			return adjustedCanMoveSprites(start, goal);
//		}
		
		//If every corner of the enemy can move from start to goal, the path is wide enough.
		//also check if center can move to see if there is a small prop which could be between
		//the lines between the corners.
		return(world.canMove(getAdjustedPosition(start, halfPathWidth, halfPathWidth), 
				getAdjustedPosition(goal, halfPathWidth, halfPathWidth)) &&
				world.canMove(getAdjustedPosition(start, halfPathWidth, -halfPathWidth), 
						getAdjustedPosition(goal, halfPathWidth, -halfPathWidth)) &&
				world.canMove(getAdjustedPosition(start, -halfPathWidth, halfPathWidth), 
						getAdjustedPosition(goal, -halfPathWidth, halfPathWidth)) &&
				world.canMove(getAdjustedPosition(start, -halfPathWidth, -halfPathWidth), 
						getAdjustedPosition(goal, -halfPathWidth, -halfPathWidth)) &&
				world.canMove(start, goal));
	}
	
	/*
	 * Returns if there is a straight way between two positions with a 0.7 width.
	 * @param start the start position.
	 * @param goal the end position.
	 * @return true if there is a path, false otherwise.
	 */
	private boolean adjustedCanMoveSprites(Position start, Position goal){
		float halfPathWidth = 0.35f;
		return(world.canMove(getAdjustedPosition(start, halfPathWidth, halfPathWidth), 
				getAdjustedPosition(goal, halfPathWidth, halfPathWidth), excludingSprites) &&
				world.canMove(getAdjustedPosition(start, halfPathWidth, -halfPathWidth), 
						getAdjustedPosition(goal, halfPathWidth, -halfPathWidth), excludingSprites) &&
				world.canMove(getAdjustedPosition(start, -halfPathWidth, halfPathWidth), 
						getAdjustedPosition(goal, -halfPathWidth, halfPathWidth), excludingSprites) &&
				world.canMove(getAdjustedPosition(start, -halfPathWidth, -halfPathWidth), 
						getAdjustedPosition(goal, -halfPathWidth, -halfPathWidth), excludingSprites) &&
				world.canMove(start, goal, excludingSprites));
	}
	
	private Position getAdjustedPosition(Position p, float dx, float dy){
		return new Position((float) p.getX()+dx, (float) p.getY()+dy);
	}
	
	
	private float getDistance(Position t1, Position t2){
		float dx = Math.abs(t1.getX() - t2.getX());
		float dy = Math.abs(t1.getY() - t2.getY());
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
	private List<Position> createListOfPositions(){
		//Create a list of the chain of parentNodes, starting from currentNode. The list
		//will be a path starting with goal and ending the the start.
		List<PathfindingNode> correctList = new ArrayList<PathfindingNode>();
		correctList.add(currentNode);
		while(currentNode.getParentNode() != null){
			correctList.add(currentNode.getParentNode());
			currentNode = currentNode.getParentNode();
		}
		
		//Invert the list and convert to Positions, begin with start and end with goal.
		List<Position> correctListInverted = new ArrayList<Position>();
		correctListInverted.add(start);//start isn't the exact same position as found in 
		//correctList(last) and the list should be from start to goal as written in the javadoc.
		for(int i = correctList.size()-1; i>-1; i--){
			correctListInverted.add(correctList.get(i).getCenter());
		}
		return correctListInverted;
	}
	
	private void removeUnnecessaryPositions(List<Position> list){
		for(int i = 0; i<list.size()-2; i++){
			if(adjustedCanMove(list.get(i), list.get(i+2))){
				list.remove(i+1);
				i--;
			}
		}
	}
	
	
}
