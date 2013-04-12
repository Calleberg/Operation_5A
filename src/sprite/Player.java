package sprite;

import geometrical.Position;
import java.awt.Point;

public class Player {
	private Position position;
	private float direction;
	private float speed;
	
	public Player(float x, float y){
		position = new Position(x,y);
		this.speed = 0.2f;
	}
	
	public void move(){
		position.setX(position.getX() + (float)(Math.cos(direction)*speed));
		position.setY(position.getY() - (float)(Math.sin(direction)*speed));
	}
	public float getDirection(){
		return direction;
	}
	public void setDirection(float direction){
		this.direction = direction;
	}
	public float getX(){
		return position.getX();
	}
	public float getY(){
		return position.getY();
	}
	
	
}
