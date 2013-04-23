package model.sprites;

import java.awt.image.BufferedImage;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.items.weapons.Projectile;
import model.items.weapons.Weapon;




public class Player implements Sprite {
	
	private State state;
	private float direction;
	private float speed;
	private Weapon weapon;
	private int health;
	private BufferedImage image;
	private CollisionBox collisionBox;
	
	/**
	 * Creates a new player with a specific position.
	 * @param x The x-coordinate for the player's position.
	 * @param y The y-coordinate for the player's position.
	 */
	public Player(float x, float y){
		state = State.STANDING;
		this.speed = 0.2f;
		this.health = 100;
		collisionBox = new Rectangle(x, y, 1, 1);
	}
	
	/**
	 * The player moves in specific direction and length depending on which key is pressed
	 * and the player's speed.
	 */
	public void move(){
		if(state == State.FORWARD) {
			changePosition(direction);
		}else if(state == State.LEFT) {
			changePosition(direction+Math.PI/2);
		}else if(state == State.BACKWARDS) {
			changePosition(direction+Math.PI);
		}else if(state == State.RIGHT) {
			changePosition(direction-Math.PI/2);
		}
	}
	
	/**
	 * Changes the position with a specific direction.
	 */
	private void changePosition(double d){
		collisionBox.setPosition(new Position(collisionBox.getPosition().getX() + (float)(Math.cos(d)*speed), 
				collisionBox.getPosition().getY() - (float)(Math.sin(d)*speed)));
	}
	
	/**
	 * Set the state for the player.
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * Returns the direction of the player.
	 */
	public float getDirection(){
		return direction;
	}
	
	/**
	 * Set the direction of the player.
	 */
	public void setDirection(float direction){
		this.direction = direction;
	}
	
	/**
	 * Returns the x-coordinate of the player's position.
	 */
	public float getX(){
		return collisionBox.getPosition().getX();
	}
	
	/**
	 * Returns the y-coordinate of the player's position.
	 */
	public float getY(){
		return collisionBox.getPosition().getY();
	}
	
	/**
	 * Set the x-coordinate of the player's position.
	 */
	public void setX(float x){
		collisionBox.getPosition().setX(x);
	}
	
	/**
	 * Set the y-coordinate of the player's position.
	 */
	public void setY(float y){
		collisionBox.getPosition().setY(y);
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
	
	@Override
	public Position getPosition() {
		return collisionBox.getPosition();
	}
	
	@Override
	public void setPosition(Position p) {
		this.collisionBox.setPosition(p);
	}
	@Override
	public void setImage(BufferedImage i) {
		this.image = i;
		
	}
	@Override
	public BufferedImage getImage() {
		return image;
	}
	@Override
	public void SpriteHitbyProjectile(Projectile p) {
		health = health - p.getDamage();
		System.out.println("Enemy health: " + health);
	}
	
	/**
	 * Increase the player's health.
	 * @param i The amount of health of which the player's health increases with.
	 */
	public void increaseHealth(int i){
		this.health = this.health + i;
	}
	@Override
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}
	
}
