package model.geometrical;

/**
 * 
 * 
 * @author Calleberg
 *
 */
public interface CollisionBox {

	/**
	 * Rotates the collision box the specified amount.
	 * @param direction the amount to rotate in radians.
	 */
	public void rotate(float direction);
	
	/**
	 * Gives the rotation of this collision box.
	 * @return the rotation of this collision box.
	 */
	public float getRotation();
	
	/**
	 * Gives the width of the collision box.
	 * @return the  width of the collision box.
	 */
	public float getWidth();
	
	/**
	 * Gives the height of the collision box.
	 * @return the  height of the collision box.
	 */
	public float getHeight();
	
	/**
	 * Gives the position of the collision box.
	 * @return the position of the collision box.
	 */
	public Position getPosition();
	
	/**
	 * Sets the position of the collision box.
	 * @param pos the new position of the collision box.
	 */
	public void setPosition(Position pos);
	
	/**
	 * Checks if this collision box intersects the specified one.
	 * @param box the collision box to check against.
	 * @return <code>true</code> if the two collision boxes is intersecting.
	 */
	public boolean intersects(CollisionBox box);
	
	/**
	 * Moves the collision box back to its previous position.
	 * @return <code>true</code> if the collision box could move back.
	 */
	public boolean moveBack();
	
	/**
	 * Gives all the lines this shape consists of.
	 * @return all the lines this shape consists of.
	 */
	public Line[] getPolygon();
}
