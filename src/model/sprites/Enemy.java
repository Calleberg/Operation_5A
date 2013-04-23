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
	
	public Enemy(Position position, float speed, Weapon weapon, int health/*, 
			BufferedImage image*/){
		state = State.STANDING;//TODO setState
		this.speed = speed;
		this.weapon = weapon;
		this.health = health;
		this.image = image;
		collisionBox = new Rectangle(position.getX(), position.getY(), 1, 1);
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
	public void move() {
		if(state == State.FORWARD) {
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

	@Override
	public float getX() {
		return this.collisionBox.getPosition().getX();
	}

	@Override
	public float getY() {
		return this.collisionBox.getPosition().getY();
	}

	@Override
	public void setX(float x) {
		this.collisionBox.getPosition().setX(x);
	}

	@Override
	public void setY(float y) {
		this.collisionBox.getPosition().setY(y);
	}

	@Override
	public Weapon getActiveWeapon() {
		return weapon;
	}
	
	@Override
	public void SpriteHitbyProjectile(Projectile p) {
		health = health - p.getDamage();
		System.out.println("Enemy health: " + health);
	}


	@Override
	public CollisionBox getCollisionBox() {
		return collisionBox;
	}

}
