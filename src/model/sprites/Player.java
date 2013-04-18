package model.sprites;

import java.awt.image.BufferedImage;

import model.geometrical.Position;
import model.items.weapons.Weapon;




public class Player implements Sprite {
	/**
	 * The player's current state. E.g. forward or backward.
	 */
	public static enum State{FORWARD, BACKWARDS, RIGHT, LEFT, STANDING};
	private State state;
	private Position position;
	private float direction;
	private float speed;
	private Weapon weapon;
	private BufferedImage image;
	
	/**
	 * Creates a new player with a specific position.
	 * @param x The x-coordinate for the player's position.
	 * @param y The y-coordinate for the player's position.
	 */
	public Player(float x, float y){
		state = State.STANDING;
		position = new Position(x,y);
		this.speed = 0.2f;
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
		position.setX(position.getX() + (float)(Math.cos(d)*speed));
		position.setY(position.getY() - (float)(Math.sin(d)*speed));
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
		return position.getX();
	}
	/**
	 * Returns the y-coordinate of the player's position.
	 */
	public float getY(){
		return position.getY();
	}
	/**
	 * Set the x-coordinate of the player's position.
	 */
	public void setX(float x){
		position.setX(x);
	}
	/**
	 * Set the y-coordinate of the player's position.
	 */
	public void setY(float y){
		position.setY(y);
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
	
}
