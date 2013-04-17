package projectile;

import Item.Weapon;
import sprite.Player;
import geometrical.Position;

public class Projectile {
	
	private int damage;
	private float speed;
	private float range;
	private float direction;
	private Position position;
	
	public Projectile(Player player, Weapon weapon){
		this.damage = weapon.getDamage();
		this.speed = weapon.getSpeed();
		this.range = weapon.getRange();
		this.direction = player.getDirection();
		this.position = new Position(player.getX(), player.getY());
	}
	
	public Projectile(int damage, float speed, float range, float direction, Position position) {
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.direction = direction;
		this.position = position;
	}
	
	/**
	 * Updates the projectile's position.
	 */
	public void move() {
		position.setX(position.getX() + (float)(Math.cos(direction)*speed));
		position.setY(position.getY() - (float)(Math.sin(direction)*speed));
	}
	
	/**
	 * Returns the amount of damage the projectile causes
	 * @return the damage of the projectile
	 */
	public int getDamage(){
		return damage;
	}
	/**
	 * Returns the speed of the projectile
	 * @return the speed of the projectile
	 */
	public float getSpeed(){
		return speed;
	}
	/**
	 * Returns the range the projectile can travel, the range will be the same for 
	 * all ranged weapons but differ for melee weapons
	 * @return the range of the projectile
	 */
	public float getRange(){
		return range;
	}
	/**
	 * Returns the direction in which the projectile will travel, 
	 * the value is in radians
	 * @return the direction of the projectile
	 */
	public float getDirection(){
		return direction;
	}
	
	/**
	 * Returns the position of the projectile
	 * @return the position of the projectile
	 */
	public Position getPosition(){
		return position;
	}
	/**
	 * Sets the parameter as the new position of the projectile
	 * @param pos is the new position
	 */
	public void setPosition(Position pos){
		position = pos;
	}


}
