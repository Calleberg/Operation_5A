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
 * @author Calleberg, Jonatan Magnusson
 *
 */
public class Tile implements WorldObject {

	private int floor;
	private boolean northWall, westWall;
	private Position position;
	private List<Prop> props;
	private ComplexShape box;
	private Property property;
	
	public static enum Property{
		HEALTH_SPAWN, FOOD_SPAWN, AMMO_SPAWN, WEAPON_SPAWN, UNWALKABLE, NONE;
	}
	
	/**
	 * Creates a new tile with the specified floor.
	 * @param pos the position of the tile
	 * @param floor the floor to use.
	 */
	public Tile(Position pos, int floor) {
		this(pos, floor, false, false, Property.NONE);
	}
	
	/**
	 * Creates a new tile with the specified floor and north and/or
	 * east walls if specified.
	 * @param pos the position of the tile.
	 * @param floor the floor to use.
	 * @param northWall specify if this tile has a north wall.
	 * @param westWall specify if this tile has an west wall.
	 */
	public Tile(Position pos, int floor, boolean northWall, boolean westWall, Property property) {
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
				box.addShape(new Rectangle(getX(), getY(), 1f, 0.1f));
			}
			if(hasWestWall()) {
				box.addShape(new Rectangle(getX(), getY(), 0.1f, 1f));
			}
			for(int i = 0; i < this.props.size(); i++) {
				box.addShape(props.get(i).getCollisionBox());
			}
		}
		
		if(box.getRectangles().length == 0) {
			return null;
		}else{
			return box;
		}
	}

	/**
	 * Sets the property of the Tile to the property given
	 * @param property the property given
	 */
	public void setProperty(Tile.Property property){
		this.property = property;
	}
	
	/**
	 * returns the property of the Tile
	 * @return the property of the Tile
	 */
	public Property getProperty(){
		return this.property;
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
