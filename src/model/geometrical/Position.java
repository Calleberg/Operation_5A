package model.geometrical;

/**
 * A class which holds a position as float values.
 * 
 * @author Jonatan Magnusson
 *
 */
public class Position implements Cloneable{
	
	private float x;
	private float y;
	
	/**
	 * Creates a new position at the specified position.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gives the x-coordinate of the position.
	 * @return the x-coordinate of the position.
	 */
	public float getX(){
		return x;
	}
	
	/**
	 * Gives the y-coordinate of the position.
	 * @return the y-coordinate of the position.
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * Sets the x-coordinate of the position.
	 * @param x the x-coordinate.
	 */
	public void setX(float x){
		this.x = x;
	}
	
	/**
	 * Sets the y-coordinate of the position.
	 * @param y the y-coordinate.
	 */
	public void setY(float y){
		this.y = y;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	}
	
	@Override
	public Object clone(){
		return new Position(x,y);
	}
}
