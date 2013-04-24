package model.world;

import java.awt.image.BufferedImage;

import model.geometrical.Position;



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
