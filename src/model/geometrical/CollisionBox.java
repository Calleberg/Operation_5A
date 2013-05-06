package model.geometrical;

import java.awt.geom.Line2D;
import java.util.List;

/**
 * An interface for collision boxes.
 * Every collision box should support <code>intersects(CollisionBox b)</code>.
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
	 * Moves the collision box to the specified position.
	 * @param pos the new position of the collision box.
	 */
	public void setPosition(Position pos);
	
	/**
	 * Moves the collision box the specified amount.
	 * @param dx the amount to move along the X axis.
	 * @param dy the amount to move along the Y axis.
	 */
	public void move(float dx, float dy);
	
	/**
	 * Checks if this collision box intersects the specified one.
	 * @param box the collision box to check against.
	 * @return <code>true</code> if the two collision boxes is intersecting.
	 */
	public boolean intersects(CollisionBox box);
	
	/**
	 * Gives a list of all the segments of the collision box.
	 * @return  a list of all the segments of the collision box.
	 */
	public List<Line2D> getLines();
			
	/**
	 * Moves the collision box back to its previous position.
	 * @return <code>true</code> if the collision box could move back.
	 */
	public boolean moveBack();
}
