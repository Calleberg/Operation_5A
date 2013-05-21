package model.other;

import model.geometrical.Position;




/**
 * An interface used when creating objects that will populate a world.
 * 
 * @author
 *
 */
public interface WorldObject {

	/**
	 * Gives the position of this object.
	 * @return the position of this object.
	 */
	public Position getPosition();
	
	/**
	 * Sets the position of this object.
	 * @param p the position of this object.
	 */
	public void setPosition(Position p);
}
