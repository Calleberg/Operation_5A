package model.sprites;

import java.util.List;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.items.Item;
import model.items.Supply;
import model.items.weapons.Weapon;
import model.pathfinding.PathfindingNode;

public class Enemy implements Sprite{

	private State state;
	private float direction;
	private float speed;
	private Weapon weapon;
	private int health;
	private CollisionBox collisionBox;
	private CollisionBox hitBox;
	private List<PathfindingNode> list;
	private int pathfindingListIndex;
	

	protected Enemy(Position position, float speed, Weapon weapon, int health){
		this.setState(Sprite.State.STANDING);
		this.speed = speed;
		this.weapon = weapon;
		this.health = health;
		collisionBox = new Rectangle(0, 0, 0.8f, 0.8f);
		hitBox = new Rectangle(position.getX(), position.getY(), 0.6f, 0.6f);
	}
	
	/**
	 * Gives the position of the enemy.
	 * @return the position of the enemy.
	 */
	public Position getPosition() {
		return hitBox.getPosition();
	}
	
	@Override
	public Position getProjectileSpawn() {
		return new Position(getX() + getHitBox().getWidth()/2 + (float)(Math.cos(direction)*0.5f), 
				getY() + getHitBox().getHeight()/2 - (float)(Math.sin(direction)*0.5f));
	}

	/**
	 * Sets the position of the enemy.
	 * @param p the position of the enemy.
	 */
	public void setPosition(Position p) {
		this.hitBox.setPosition(p);
	}

	@Override
	public void moveXAxis(){
		if(this.state == Sprite.State.MOVING) {
			this.setDirectionTowardsList();
			hitBox.setPosition(new Position(hitBox.getPosition().getX() + (float)(Math.cos(direction)*speed), 
					hitBox.getPosition().getY()));
		}
	}

	@Override
	public void moveYAxis(){
		if(this.state == Sprite.State.MOVING) {
			hitBox.setPosition(new Position(hitBox.getPosition().getX(), 
					hitBox.getPosition().getY() - (float)(Math.sin(direction)*speed)));
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
		return this.hitBox.getPosition().getX();
	}

	/**
	 * Gives the y-coordinate.
	 * @return the y-coordinate.
	 */
	public float getY() {
		return this.hitBox.getPosition().getY();
	}

	/**
	 * Sets the x-coordinate.
	 * @param x the x-coordinate.
	 */
	public void setX(float x) {
		this.hitBox.setPosition(new Position(x, this.getY()));
	}

	/**
	 * Sets the y-coordinate.
	 * @param y the y-coordinate.
	 */
	public void setY(float y) {
		this.hitBox.setPosition(new Position(this.getX(),y));
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

	public void setWay(List<PathfindingNode> list){
		this.state = State.MOVING;
		pathfindingListIndex = 0;
		this.list = list;
		list.remove(0);//removes the tile the enemy currently stands on
		
		}
	private void setDirectionTowardsList(){
		
		if(list.size() <= pathfindingListIndex){
			this.state = State.STANDING;
			return;//TODO varför behövs detta?
		}
		
		if(Math.abs(this.getCenter().getX() - 
				(list.get(pathfindingListIndex).getTile().getX()+0.5)) > 0.05
				|| Math.abs(this.getCenter().getY() - 
				(list.get(pathfindingListIndex).getTile().getY()+0.5)) > 0.05){
			float dx = (float) (this.getCenter().getX() - (list.get(pathfindingListIndex).getTile().getX()+0.5));
			float dy = (float) (this.getCenter().getY() - (list.get(pathfindingListIndex).getTile().getY()+0.5));
			float sin = (float) Math.asin((float) (dy/Math.sqrt(dx*dx+dy*dy)));
			if(dx>0){
				this.setDirection((float)Math.PI - sin);
			}else{
				this.setDirection(sin);
			}
		}else{
			pathfindingListIndex++;
			
			if(!(list.size()<=pathfindingListIndex)){
				float dx = this.collisionBox.getPosition().getX() - 
						list.get(pathfindingListIndex).getTile().getX();
				float dy = this.collisionBox.getPosition().getY() - 
						list.get(pathfindingListIndex).getTile().getY();
				float sin = (float) Math.asin((float) (dy/Math.sqrt(dx*dx+dy*dy)));
				if(dx>0){
					this.setDirection((float)Math.PI - sin);
				}else{
					this.setDirection(sin);
				}
			}else{
				
				System.out.println("standing");
				state = State.STANDING;
			}
			
		}
	}

	@Override
	public Position getCenter() {
		return new Position(getX() + getHitBox().getWidth()/2, getY() + getHitBox().getHeight()/2);
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public boolean pickUpItem(Item i) {
		return false;
	}

	@Override
	public CollisionBox getMoveBox() {
		this.collisionBox.setPosition(new Position(this.getX() + (hitBox.getWidth() - collisionBox.getWidth()) / 2
				, this.getY() + (hitBox.getHeight() - collisionBox.getHeight()) / 2));
		return this.collisionBox;
	}
	
	@Override
	public CollisionBox getHitBox() {
		return this.hitBox;
	}

	@Override
	public void moveBack() {
		this.hitBox.moveBack();
	}

}
