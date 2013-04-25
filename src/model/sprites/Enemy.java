package model.sprites;

import java.awt.image.BufferedImage;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.items.weapons.Projectile;
import model.items.weapons.Weapon;
import model.sprites.Sprite.State;

public class Enemy implements Sprite{

	private State state;
	private float direction;
	private float speed;
	private Weapon weapon;
	private int health;
	private BufferedImage image;
	private CollisionBox collisionBox;
	
	protected Enemy(Position position, float speed, Weapon weapon, int health){
		state = State.STANDING;//TODO setState
		this.speed = speed;
		this.weapon = weapon;
		this.health = health;
		collisionBox = new Rectangle(position.getX(), position.getY(), 1, 1);
	}
	
	/**
	 * Gives the position of the enemy.
	 * @return the position of the enemy.
	 */
	public Position getPosition() {
		return collisionBox.getPosition();
	}
	
	@Override
	public Position getProjectileSpawn() {
		return this.collisionBox.getPosition();
	}

	/**
	 * Sets the position of the enemy.
	 * @param p the position of the enemy.
	 */
	public void setPosition(Position p) {
		this.collisionBox.setPosition(p);
	}

	@Override
	public void move(){ 
		if(state == Sprite.State.MOVING) {

			collisionBox.setPosition(new Position(collisionBox.getPosition().getX() + 
					(float)(Math.cos(direction)*speed), collisionBox.getPosition().getY() - 
					(float)(Math.sin(direction)*speed)));
		}
	}
	

	@Override
	public float getDirection() {
		return direction;
	}
	
	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setDirection(float direction) {
		this.direction = direction;
	}

	/**
	 * Gives the x-coordinate.
	 * @return the x-coordinate.
	 */
	public float getX() {
		return this.collisionBox.getPosition().getX();
	}

	/**
	 * Gives the y-coordinate.
	 * @return the y-coordinate.
	 */
	public float getY() {
		return this.collisionBox.getPosition().getY();
	}

	/**
	 * Sets the x-coordinate.
	 * @param x the x-coordinate.
	 */
	public void setX(float x) {
		this.collisionBox.setPosition(new Position(x, this.getY()));
	}

	/**
	 * Sets the y-coordinate.
	 * @param y the y-coordinate.
	 */
	public void setY(float y) {
		this.collisionBox.setPosition(new Position(this.getX(),y));
	}

	@Override
	public Weapon getActiveWeapon() {
		return weapon;
	}
	
	@Override
	public void reduceHealth(int damage) {
		health = health - damage;
		System.out.println("Enemy health: " + health);
	}


	@Override
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

	@Override
	public Position getCenter() {
		return new Position(getX() + getCollisionBox().getWidth()/2, getY() + getCollisionBox().getHeight()/2);
	}

}
