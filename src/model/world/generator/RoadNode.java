package model.world.generator;

public class RoadNode {

	public static enum Direction{NORTH, SOUTH, WEST, EAST};
	private int x, y;
	private int goalX, goalY;
	private Direction dir;

	public RoadNode(int startX, int startY, int goalX, int goalY, Direction dir) {
		this.x = startX;
		this.y = startY;
		this.goalX = goalX;
		this.goalY = goalY;
		this.dir = dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public Direction getDirection() {
		return this.dir;
	}
	
	public void turn() {
		if(x > goalX && dir != Direction.WEST) {
			this.setDirection(Direction.WEST);
		}else if(x < goalX && dir != Direction.EAST) {
			this.setDirection(Direction.EAST);
		}else if(y > goalY && dir != Direction.NORTH) {
			this.setDirection(Direction.NORTH);
		}else if(y < goalY && dir != Direction.SOUTH) {
			this.setDirection(Direction.SOUTH);
		}
	}
	
	public int getX(){
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public boolean isDone(int margin) {
		return (Math.abs(x - goalX) < margin && Math.abs(y - goalY) < margin);
	}
	
	public void moveStep() {
		switch(dir){
		case EAST:	this.x++;
			break;
		case NORTH:	this.y--;
			break;
		case SOUTH:	this.y++;
			break;
		case WEST:	this.x--;
			break;
		default:
			break;
		
		}
	}
}

