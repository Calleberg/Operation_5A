package model.sprites;

import model.geometrical.CollisionBox;
import model.items.weapons.Projectile;
import model.items.weapons.Weapon;
import model.other.WorldObject;


public interface Sprite extends WorldObject {
	
	
	/**
	 * The Sprite's current moving direction.
	 */
	public static enum State{MOVING, STANDING};
	
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
	 * Returns the current health of the sprite.
	 * @return the current health of the sprite.
	 */
	public int getHealth();
	
	
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
	
	/**
	 * The Sprite get hit by a projectile.
	 * @param p The projectile the sprite get hit by.
	 */
	public void reduceHealth(int i);
	
	/**
	 * Return the collisionBox of the player.
	 * @return the CollisionBox of the player.
	 */
	public CollisionBox getCollisionBox();
}
