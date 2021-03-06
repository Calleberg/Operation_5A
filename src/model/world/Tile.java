package model.world;

import java.util.ArrayList;
import java.util.List;

import model.geometrical.CollisionBox;
import model.geometrical.ComplexShape;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.other.WorldObject;
import model.world.props.Prop;

/**
 * Holds the data of one tile in a world.
 * 
 * @author Martin Calleberg, Jonatan Magnusson
 *
 */
public class Tile implements WorldObject {

	private int floor;
	private boolean northWall, westWall;
	private Position position;
	private List<Prop> props;
	private ComplexShape box; //only needs to be compiled once
	private int property;
	
	/**
	 * Specifies that the tile does not have any special property.
	 */
	public static final int NONE = 0;
	/**
	 * Specifies that the tile is a health spawn point.
	 */
	public static final int HEALTH_SPAWN = 1;
	/**
	 * Specifies that the tile is a food spawn point.
	 */
	public static final int FOOD_SPAWN = 2;
	/**
	 * Specifies that the tile is a ammo spawn point.
	 */
	public static final int AMMO_SPAWN = 3;
	/**
	 * Specifies that the tile is a weapon spawn point.
	 */
	public static final int WEAPON_SPAWN = 4;
	/**
	 * Specifies that the tile can not be walked on.
	 */
	public static final int UNWALKABLE = 5;
	
	/**
	 * Creates a new tile with the specified floor.
	 * @param pos the position of the tile
	 * @param floor the floor to use.
	 */
	public Tile(Position pos, int floor) {
		this(pos, floor, false, false, NONE);
	}
	
	/**
	 * Creates a new tile with the specified floor and north and/or
	 * east walls if specified.
	 * @param pos the position of the tile.
	 * @param floor the floor to use.
	 * @param northWall specify if this tile has a north wall.
	 * @param westWall specify if this tile has an west wall.
	 * @param property the special property of this tile.
	 */
	public Tile(Position pos, int floor, boolean northWall, boolean westWall, int property) {
		this.position = pos;
		this.floor = floor;
		this.northWall = northWall;
		this.westWall = westWall;
		this.props = new ArrayList<Prop>();
		this.property = property;
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
	 * Adds a props to this tile.
	 * @param prop the prop.
	 */
	public void addProp(Prop prop) {
		this.props.add(prop);	
	}
	
	/**
	 * Removes the specified prop from this tile.
	 * @param prop the prop to remove.
	 */
	public void removeProp(Prop prop) {
		this.props.remove(prop);
	}
	
	/**
	 * Gives a list of all the props on this tile.
	 * @return a list of all the props on this tile.
	 */
	public List<Prop> getProps() {
		return this.props;
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
	 * Gives <code>true</code> if the tile has at least one prop.
	 * @return <code>true</code> if the tile has at least one prop.
	 */
	public boolean hasProps() {
		return !(this.props.size() == 0);
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
		if(box == null) {
			box = new ComplexShape();
			if(hasNorthWall()) {
				box.addShape(new Rectangle(this.getPosition().getX(), this.getPosition().getY() - 
						0.05f, 1f, 0.1f));
			}
			if(hasWestWall()) {
				box.addShape(new Rectangle(this.getPosition().getX() - 0.05f, 
						this.getPosition().getY(), 0.1f, 1f));
			}
			for(int i = 0; i < this.props.size(); i++) {
				box.addShape(props.get(i).getCollisionBox());
			}
		}
		
		if(box.getLines().size() == 0) {
			return null;
		}else{
			return box;
		}
	}
	
	/**
	 * Checks if the specified collisionbox intersects the tile.
	 * @param box the box to check.
	 * @return <code>true</code> if collision box provided intersects this tile.
	 */
	public boolean intersects(CollisionBox box) {
		return box.intersects(new Rectangle(this.getPosition().getX(), 
				this.getPosition().getY(), 1f, 1f));
	}

	/**
	 * Sets the property of the Tile to the property given
	 * @param property the property given
	 */
	public void setProperty(int property){
		this.property = property;
	}
	
	/**
	 * returns the property of the Tile
	 * @return the property of the Tile
	 */
	public int getProperty(){
		return this.property;
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
