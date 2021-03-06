package model.sprites;

import java.beans.PropertyChangeListener;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.items.weapons.Weapon;
import model.other.Saveable;
import model.other.WorldObject;
import model.items.Item;

/**
 * 
 * @author Jonatan Magnusson, Martin Calleberg
 *
 */
public interface Sprite extends WorldObject, Saveable {
	
	/**
	 * Name of the event fired when the sprite is reloading its weapon.
	 */
	public static final String EVENT_RELOADING = "reloading";
	/**
	 * Name of the event fired when the sprite is using its weapon.
	 */
	public static final String EVENT_USE_WEAPON = "useWeapon";
	
	/**
	 * Adds the specified listener to listen to this sprite.
	 * @param pcl the listener.
	 */
	public void addListener(PropertyChangeListener pcl);
	
	/**
	 * Forces the sprite to fire the specified event.
	 * @param event the event to fire.
	 */
	public void fireEvent(String event);
	
	/**
	 * The Sprite's current moving direction.
	 */
	public static enum State{RUNNING, WALKING, STANDING;
	
		/**
		 * Gives the State which has the same text as the text provided.
		 * @param text the text to check with.
		 * @return the Type which has the same text as the text provided.
		 */
		public static State fromString(String text) {
			if (text != null) {
				for (State b : State.values()) {
					if (text.equalsIgnoreCase(b.toString())) {
						return b;
					}
				}
			}
			throw new IllegalArgumentException("No enum with name " + text + " found");
		}
	}
	
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
	 * Return the collisionBox used when moving.
	 * @return the collisionBox used when moving.
	 */
	public CollisionBox getMoveBox();
		
	/**
	 * Return the hit box of the sprite.
	 * @return the hit box of the sprite.
	 */
	public CollisionBox getHitBox();
	
	/**
	 * Gives the position of where to spawn projectiles from.
	 * @return the position of where to spawn projectiles from.
	 */
	public Position getProjectileSpawn();
	
	/**
	 * Moves the sprite back one step.
	 */
	public void moveBack();
	
	/**
	 * Gives the position of the center of the sprite.
	 * @return the position of the center of the sprite.
	 */
	public Position getCenter();
	
	/**
	 * Sets the center of the sprite.
	 * @param center the center of the sprite.
	 */
	public void setCenter(Position center);
	
	/**
	 * picks up an item if the Sprite is eligible
	 * @return if the Sprite is eligible
	 */
	public boolean pickUpItem(Item i);
}
