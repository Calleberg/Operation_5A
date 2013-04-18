package model.sprites;

import model.items.weapons.Weapon;
import model.world.WorldObject;


public interface Sprite extends WorldObject {
	
	
	/**
	 * The Sprite's current moving direction.
	 */
	public static enum State{FORWARD, BACKWARDS, RIGHT, LEFT, STANDING};
	
	/**
	 * Move the sprite in it's current direction.
	 */
	public void move();
	
	/**
	 * Returns the direction of the Sprite.
	 * @return the direction of the Sprite.
	 */
	public float getDirection();
	
	/**
	 * Set the direction of the Sprite.
	 * @param direction the direction of the Sprite.
	 */
	public void setDirection(float direction);
	
	/**
	 * Returns the active weapon of the Sprite.
	 * @return the active weapon of the Sprite.
	 */
	public Weapon getActiveWeapon();
}
