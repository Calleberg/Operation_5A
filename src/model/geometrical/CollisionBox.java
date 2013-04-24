package model.geometrical;

/**
 * An interface for collision boxes.
 * Every collision box can check if another collision box is
 * intersecting with it.
 * 
 * @author Calleberg
 *
 */
public interface CollisionBox {
		
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
	 * Gives an array of all the rectangles that builds this shape.
	 * @return an array of all the rectangles that builds this shape.
	 */
	public java.awt.geom.Rectangle2D[] getRectangles();
	
	/**
	 * Moves the collision box back to its previous position.
	 * @return <code>true</code> if the collision box could move back.
	 */
	public boolean moveBack();
}
