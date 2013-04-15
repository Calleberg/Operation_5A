package world;

/**
 * An interface used when creating objects that will populate a world.
 * 
 * @author
 *
 */
public interface WorldObject {

	/**
	 * Sets the x-coordinate of this object.
	 * @param x the x-coordinate.
	 */
	public void setX(float x);
	
	/**
	 * Gives the x-coordinate of this object.
	 * @return the x-coordinate of this object.
	 */
	public float getX();
	
	/**
	 * Sets the y-coordinate of this object.
	 * @param y the y-coordinate.
	 */
	public void setY(float y);
	
	/**
	 * Gives the y-coordinate of this object.
	 * @return the y-coordinate of this object.
	 */
	public float getY();
	
//	/**
//	 * Sets the collision box of this object.
//	 * @param box the collision box to use.
//	 */
//	public void setCollisionBox(CollisionBox box);
//	
//	/**
//	 * Gives the collision box of this object.
//	 * @return the collision box of this object.
//	 */
//	public CollisionBox getCollisionBox();
}
