package model.sprites;

import model.geometrical.Circle;
import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.items.weapons.Weapon;

/**
 * Models a player which can populate a world.
 * 
 * @author
 *
 */
public class Player implements Sprite {
	
	private State state;
	private float faceDir;
	private float moveDir; //TODO mer!
	private float speed;
	private Weapon weapon;
	private int health;
	private CollisionBox collisionBox;
	private int ammo;
	private int food;
	
	/**
	 * Creates a new player with a specific position.
	 * @param x The x-coordinate for the player's position.
	 * @param y The y-coordinate for the player's position.
	 */
	public Player(float x, float y){
		state = State.STANDING;
		this.speed = 0.15f;
		this.health = 100;
		collisionBox = new Rectangle(x, y, 0.6f, 0.6f);
		this.food = 100;
		this.ammo = 500;
	}
	
	@Override
	public void moveXAxis(){
		if(this.state == Sprite.State.MOVING) {
			collisionBox.setPosition(new Position(collisionBox.getPosition().getX() + (float)(Math.cos(moveDir)*speed), 
					collisionBox.getPosition().getY()));
		}
	}

	@Override
	public void moveYAxis(){
		if(this.state == Sprite.State.MOVING) {
			collisionBox.setPosition(new Position(collisionBox.getPosition().getX(), 
					collisionBox.getPosition().getY() - (float)(Math.sin(moveDir)*speed)));
		}
	}
	
	/**
	 * Sets the angle of which to move at. 
	 * @param d the new angle in radians.
	 */
	public void setMoveDir(float d) {
		this.setState(Sprite.State.MOVING);
		this.moveDir = d;
	}
	
	@Override
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * Returns the direction of the player.
	 * @return the direction of the player. 
	 */
	public float getDirection(){
		return faceDir;
	}
	
	/**
	 * Sets the direction the player is facing.
	 * @param faceDir the new direction, in radians.
	 */
	public void setDirection(float faceDir){
		this.faceDir = faceDir;
	}
	
	/**
	 * Returns the x-coordinate of the player's position.
	 * @return the x-coordinate of the player's position.
	 */
	public float getX(){
		return collisionBox.getPosition().getX();
	}
	
	/**
	 * Returns the y-coordinate of the player's position.
	 * @return the y-coordinate of the player's position.
	 */
	public float getY(){
		return collisionBox.getPosition().getY();
	}
	
	/**
	 * Set the x-coordinate of the player's position.
	 * @param x the new x-coordinate.
	 */
	public void setX(float x){
		this.collisionBox.setPosition(new Position(x, this.getY()));
	}
	
	/**
	 * Set the y-coordinate of the player's position.
	 * @param y the new y-coordinate.
	 */
	public void setY(float y){
		this.collisionBox.setPosition(new Position(this.getX(),y));
	}
	
	/**
	 * Returns the player's weapon
	 * @return the player's weapon
	 */
	public Weapon getActiveWeapon(){
		return this.weapon;
	}
	
	/**
	 * Set the player's weapon
	 * @param w The player's weapon
	 */
	public void setWeapon(Weapon w){
		this.weapon = w;
	}
	
	@Override
	public int getHealth() {
		return health;
	}
	
	/**
	 * Gives the position of the player.
	 * @return the position of the player.
	 */
	public Position getPosition() {
		return collisionBox.getPosition();
	}
	
	@Override
	public Position getProjectileSpawn() {
		return new Position(getX() + getHitBox().getWidth()/2 + (float)(Math.cos(faceDir)*1f), 
				getY() + getHitBox().getHeight()/2 - (float)(Math.sin(faceDir)*1f));
	}
	
	@Override
	public Position getCenter() {
		return new Position(getX() + getHitBox().getWidth()/2, getY() + getHitBox().getHeight()/2);
	}
	
	/**
	 * Sets the position of the player.
	 * @param p the position of the player.
	 */
	public void setPosition(Position p) {
		this.collisionBox.setPosition(p);
	}

	@Override
	public void reduceHealth(int damage) {
		health = health - damage;
		System.out.println("Enemy health: " + health);
	}
	
	/**
	 * Increase the player's health.
	 * @param i the amount of health of which the player's health increases with.
	 */
	public void increaseHealth(int i){
		this.health = this.health + i;
	}
	
	@Override
	public CollisionBox getHitBox() {
		return collisionBox;
	}
	
	/**
	 * Returns the amount of ammo the player is carrying.
	 * @return the amount of ammo the player is carrying.
	 */
	public int getAmmoAmount(){
		return ammo;
	}
	
	/**
	 * Adds the specified amount of ammo to the player.
	 * @param ammo the ammo to add.
	 */
	public void increaseAmmo(int pickedUpAmmo){
		this.ammo += pickedUpAmmo;
	}
	
	/**
	 * Removes the specified amount of ammo from the player.
	 * @param ammo the amount of ammo to be reloaded
	 * @return <code>false</code> if the ammo was reduced past 0. However,
	 * the players ammo will be set to 0.
	 */
	public boolean reduceAmmo(int ammo){
		this.ammo -= ammo;
		if(ammo < 0) {
			ammo = 0;
			return false;
		}else{
			return true;
		}
	}
	
	/** 
	 * Reload the active weapon and reduces the player's ammunition by the amount
	 * needed to reload the weapon.
	 */
	public void reloadActiveWeapon(){
		ammo = weapon.reload(ammo);
	}
	/**
	 * Return the direction the player is currently moving
	 * @return the direction of movement
	 */
	public float getMoveDir(){
		return moveDir;
	}

	@Override
	public State getState() {
		return this.state;
	}
	/**
	 * Adds the amount of food specified to the food level of the player
	 * @param foodToAdd the amount of food to add
	 */
	public void addFood(int foodToAdd){
		this.food += foodToAdd;
	}
	/**
	 * returns the food level of the player
	 * @return the food level of the player
	 */
	public int getFood(){
		return this.food;
	}
	/**
	 * Removes the amount of food specified from the food level of the player
	 * @param foodToRemove the amount of food to remove
	 */
	public void removeFood(int foodToRemove){
		this.food -= foodToRemove;
	}
}
