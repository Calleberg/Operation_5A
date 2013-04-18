package model.sprites;

import java.awt.image.BufferedImage;

import model.geometrical.Position;
import model.items.weapons.Weapon;
import model.sprites.Sprite.State;

public class Enemy implements Sprite{

	private State state;
	private Position position;
	private float direction;
	private float speed;
	private Weapon weapon;
	private BufferedImage image;
	
	
	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position p) {
		this.position = p;
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
			position.setX(position.getX() + (float)(Math.cos(direction)*speed));
			position.setY(position.getY() - (float)(Math.sin(direction)*speed));
		}
	}
	

	@Override
	public float getDirection() {
		return direction;
	}

	@Override
	public void setDirection(float direction) {
		this.direction = direction;
	}

	@Override
	public float getX() {
		return this.position.getX();
	}

	@Override
	public float getY() {
		return this.position.getY();
	}

	@Override
	public void setX(float x) {
		this.position.setX(x);
	}

	@Override
	public void setY(float y) {
		this.position.setY(y);
	}

	@Override
	public Weapon getActiveWeapon() {
		return weapon;
	}

}
