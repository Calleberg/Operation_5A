package model.sprites;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.items.Supply;
import model.items.weapons.Weapon;
import model.other.WorldObject;


public interface Sprite extends WorldObject {
	
	
	/**
	 * The Sprite's current moving direction.
	 */
	public static enum State{MOVING, STANDING};
	
	/**
	 * Moves the sprite along the X axis.
	 */
	public void moveXAxis();
	
	/**
	 * Moves the sprite along the Y axis.
	 */
	public void moveYAxis();
	
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
	 * Sets the state of the sprite.
	 * @param state the new state.
	 */
	public void setState(State state);
	
	/**
	 * Gives the state of the sprite.
	 * @return the state of the sprite.
	 */
	public State getState();
	
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
	
	/**
	 * Gives the position of where to spawn projectiles from.
	 * @return the position of where to spawn projectiles from.
	 */
	public Position getProjectileSpawn();
	
	/**
	 * Gives the position of the center of the sprite.
	 * @return the position of the center of the sprite.
	 */
	public Position getCenter();
	/**
	 * picks up an item if the Sprite is eligible
	 * @return if the Sprite is eligible
	 */
	public boolean pickUpItem(Supply s);
}
