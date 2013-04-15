package sprite;

import geometrical.Position;

public class Player implements Sprite {
	
	public static enum State{FORWARD, BACKWARDS, RIGHT, LEFT, STANDING};
	private State state;
	private Position position;
	private float direction;
	private float speed;
	
	public Player(float x, float y){
		state = State.STANDING;
		position = new Position(x,y);
		this.speed = 0.2f;
	}
	
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
	
	private void changePosition(double d){
		position.setX(position.getX() + (float)(Math.cos(d)*speed));
		position.setY(position.getY() - (float)(Math.sin(d)*speed));
	}
	
	public void setState(State state) {
		this.state = state;
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
	public void setX(float x){
		position.setX(x);
	}
	public void setY(float y){
		position.setY(y);
	}
	
	
}
