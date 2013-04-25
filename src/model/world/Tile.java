package model.world;

import model.geometrical.CollisionBox;
import model.geometrical.ComplexShape;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.other.WorldObject;

/**
 * Holds the data of one tile in a world.
 * 
 * @author Calleberg
 *
 */
public class Tile implements WorldObject {

	private int floor;
	private boolean northWall, westWall;
	private Position position;
	
	/**
	 * Creates a new tile with the specified floor.
	 * @param pos the position of the tile
	 * @param floor the floor to use.
	 */
	public Tile(Position pos, int floor) {
		this(pos, floor, false, false);
	}
	
	/**
	 * Creates a new tile with the specified floor and north and/or
	 * east walls if specified.
	 * @param pos the position of the tile.
	 * @param floor the floor to use.
	 * @param northWall specify if this tile has a north wall.
	 * @param westWall specify if this tile has an west wall.
	 */
	public Tile(Position pos, int floor, boolean northWall, boolean westWall) {
		this.position = pos;
		this.floor = floor;
		this.northWall = northWall;
		this.westWall = westWall;
	}
	
	/**
	 * Sets the floor on this tile.
	 * @param floor the new floor on this tile.
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/**
	 * Gives the floor on this tile.
	 * @return the floor on this tile.
	 */
	public int getFloor() {
		return this.floor;
	}
	
	/**
	 * Gives <code>true</code> if this tile has a north wall.
	 * @return <code>true</code> if this tile has a north wall.
	 */
	public boolean hasNorthWall() {
		return this.northWall;
	}
	
	/**
	 * Sets if this tile has a north wall.
	 * @param nortWall specify if this tile has a north wall.
	 */
	public void setNorthWall(boolean northWall) {
		this.northWall = northWall;
	}
	
	/**
	 * Gives <code>true</code> if this tile has an west wall.
	 * @return <code>true</code> if this tile has a west wall.
	 */
	public boolean hasWestWall() {
		return this.westWall;
	}
	
	/**
	 * Sets if this tile has an west wall.
	 * @param westWall specify if this tile has an west wall.
	 */
	public void setWestWall(boolean westWall) {
		this.westWall = westWall;
	}
	
	/**
	 * Gives the collision box of this tile. This collision box will consist of
	 * the walls of this tile and any props that may be placed on the tile.
	 * @return
	 */
	public CollisionBox getCollisionBox() {
		ComplexShape cs = new ComplexShape();
		if(hasNorthWall()) {
			cs.addShape(new Rectangle(getX(), getY(), 1f, 0.1f));
		}
		if(hasWestWall()) {
			cs.addShape(new Rectangle(getX(), getY(), 0.1f, 1f));
		}
		if(cs.getRectangles().length == 0) {
			return null;
		}else{
			return cs;
		}
	}

	@Override
	public void setX(float x) {
		this.position.setX(x);
	}

	@Override
	public float getX() {
		return this.position.getX();
	}

	@Override
	public void setY(float y) {
		this.position.setY(y);
	}

	@Override
	public float getY() {
		return this.position.getY();
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Position p) {
		this.position = p;
	}
}
