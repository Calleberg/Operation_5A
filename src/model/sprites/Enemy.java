package model.sprites;

import java.awt.image.BufferedImage;
import java.util.List;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.items.weapons.Projectile;
import model.items.weapons.Weapon;
import model.sprites.Sprite.State;
import model.world.Tile;

public class Enemy implements Sprite{

	private State state;
	private float direction;
	private float speed;
	private Weapon weapon;
	private int health;
	private BufferedImage image;
	private CollisionBox collisionBox;
	private List<PathfindingNode> list;
	private int pathfindingListIndex;
	

	protected Enemy(Position position, float speed, Weapon weapon, int health){
		state = State.MOVING;//TODO setState
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
			this.setDirectionTowardsList();
			
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
	public void testPathfinding(List<PathfindingNode> list){
//		this.state = State.MOVING;
		this.list = list;
	}
	private void setDirectionTowardsList(){
//		System.out.println("d " + direction);
//		System.out.println(list.size());
		
		if(list.size() <= pathfindingListIndex){
			return;//TODO varf�r beh�vs detta?
		}
		
//		System.out.println("x " + this.collisionBox.getPosition().getX() + "y " + this.collisionBox.getPosition().getY());
//		System.out.println("Lx " + list.get(pathfindingListIndex).getTile().getX() + "Ly " + list.get(pathfindingListIndex).getTile().getY());
//		System.out.println("Lx1 " + list.get(1).getTile().getX() + "Ly " + list.get(1).getTile().getY());
//		System.out.println("Lx2 " + list.get(2).getTile().getX() + "Ly " + list.get(2).getTile().getY());
		
//		for(PathfindingNode n : list){
//			System.out.println("x: " + n.getTile().getX() + "y: " + n.getTile().getY());
//		}
		if(Math.abs(this.collisionBox.getPosition().getX() - 
				list.get(pathfindingListIndex).getTile().getX()) > 0.02
				|| Math.abs(this.collisionBox.getPosition().getY() - 
				list.get(pathfindingListIndex).getTile().getY()) > 0.02){
			System.out.println("setdirectiontowardslist");
			System.out.println(this.collisionBox.getPosition().getX() + " "
					+ list.get(pathfindingListIndex).getTile().getX() +
					" " +  this.collisionBox.getPosition().getY() + " " + 
					list.get(pathfindingListIndex).getTile().getY());
//			System.out.println("index " + pathfindingListIndex);
			float dx = this.collisionBox.getPosition().getX() - list.get(pathfindingListIndex).getTile().getX();
			float dy = this.collisionBox.getPosition().getY() - list.get(pathfindingListIndex).getTile().getY();
			float sin = (float) Math.asin((float) (dy/Math.sqrt(dx*dx+dy*dy)));
//			System.out.println("x: " + this.collisionBox.getPosition().getX() + " " + list.get(pathfindingListIndex).getTile().getX());
			if(dx>0){
				System.out.println("n " + (Math.PI - sin));
				this.setDirection((float)Math.PI - sin);
			}else{
				this.setDirection(sin);
			}
//			System.out.println(pathfindingListIndex);
		}else{
//			System.out.println("else");
//			System.out.println("test");
//			System.out.println(this.collisionBox.getPosition().getX() + " "
//					+ list.get(pathfindingListIndex).getTile().getX() +
//					" " +  this.collisionBox.getPosition().getY() + " " + 
//					list.get(pathfindingListIndex).getTile().getY());
//			System.out.println("index " + pathfindingListIndex);
//			pathfindingListIndex++;
//			pathfindingListIndex++;
//			System.out.println("size: " + list.size());
//			System.out.println("p: " + pathfindingListIndex);
			
			if(!(list.size()<=pathfindingListIndex)){
//				System.out.println(list.size() + " " + pathfindingListIndex);
				float dx = this.collisionBox.getPosition().getX() - 
						list.get(pathfindingListIndex).getTile().getX();
				float dy = this.collisionBox.getPosition().getY() - 
						list.get(pathfindingListIndex).getTile().getY();
				float sin = (float) Math.asin((float) (dy/Math.sqrt(dx*dx+dy*dy)));
//				System.out.println(list.get(pathfindingListIndex).getTile().getX() + " " + list.get(pathfindingListIndex).getTile().getY());
//				System.out.println(this.collisionBox.getPosition().getX() + " test");
//				System.out.println(list.get(pathfindingListIndex).getTile().getX() + " test");
//				System.out.println("dy: " + dy);
//				System.out.println("dx: " + dx);
//				System.out.println("sin " + sin);
				this.setDirection(sin);
			}else{
				
				System.out.println("standing");
				state = State.STANDING;
				pathfindingListIndex = 0;
			}
			
		}
	}

	@Override
	public Position getCenter() {
		return new Position(getX() + getCollisionBox().getWidth()/2, getY() + getCollisionBox().getHeight()/2);
	}

}
