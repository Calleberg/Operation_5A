package model.items.weapons;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.ScanLine;
import model.other.Saveable;
import model.sprites.Sprite;


public class Projectile implements Saveable {
	
	private int damage;
	private float speed;
	private float range;
	private float direction;
	private Position startingPosition;
	private CollisionBox collisionBox;
//	private Sprite owner;

	public Projectile(int damage, float speed, float range, float direction, Position position) {
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.direction = direction;
		this.collisionBox = new ScanLine(position.getX(), position.getY());
		this.startingPosition = new Position(collisionBox.getPosition().getX(), collisionBox.getPosition().getY());
	}
	
//	/**
//	 * Sets the owner of the projectile.
//	 * The owner of a projectile cannot be hurt by the projectile.
//	 * @param sprite the new owner.
//	 */
//	public void setOwner(Sprite sprite) {
//		this.owner = sprite;
//	}
	
//	/**
//	 * Gives the owner of the projectile.
//	 * @return the owner of the projectile.
//	 */
//	public Sprite getOwner() {
//		return this.owner;
//	}
	
	/**
	 * Updates the projectile's position.
	 */
	public void move() {
		collisionBox.setPosition( 
				new Position (collisionBox.getPosition().getX() + (float)(Math.cos(direction)*speed),
				collisionBox.getPosition().getY() - (float)(Math.sin(direction)*speed)));
		
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
		return collisionBox.getPosition();
	}
	/**
	 * Sets the parameter as the new position of the projectile
	 * @param pos is the new position
	 */
	public void setPosition(Position pos){
		collisionBox.setPosition(pos);
	}

	/**
	 * Return the collisionBox of the projectile.
	 * @return the collisionBox of the projectile.
	 */
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}
	
	/**
	 * Returns true if max range is reached, false otherwise.
	 * @return true if max range is reached, false otherwise.
	 */
	public boolean isMaxRangeReached(){
		float dx = startingPosition.getX() - collisionBox.getPosition().getX();
		float dy = startingPosition.getY() - collisionBox.getPosition().getY();
		float distanceTravelled = (float)Math.sqrt(dx*dx+dy*dy);
		if(distanceTravelled >= range){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void restore(String[] data) {
		this.damage = Integer.parseInt(data[0]);
		this.direction = Float.parseFloat(data[1]);
		this.range = Float.parseFloat(data[2]);
		this.speed = Float.parseFloat(data[3]);
		Position pos = new Position(Float.parseFloat(data[4]), Float.parseFloat(data[5]));
		this.startingPosition = pos;
		Position currentPos = new Position(Float.parseFloat(data[6]), Float.parseFloat(data[7]));
		this.collisionBox.move(currentPos.getX(), currentPos.getY());
	}

	@Override
	public String[] getData() {
		return new String[] {
				this.damage + "",
				this.direction + "",
				this.range + "",
				this.speed + "",
				this.startingPosition.getX() + "",
				this.startingPosition.getY() + "",
				this.collisionBox.getPosition().getX() + "",
				this.collisionBox.getPosition().getY() + ""
		};
	}
}
