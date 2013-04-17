package sprite;

import world.WorldObject;

public interface Sprite extends WorldObject {
	
	public void move();
	public float getDirection();
	public void setDirection(float direction);
	public float getX();
	public float getY();
	public void setX(float x);
	public void setY(float y);

}
