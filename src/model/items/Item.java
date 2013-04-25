package model.items;

import model.geometrical.Position;
import model.world.WorldObject;

//Empty interface created to group Items
public class Item implements WorldObject{
	private Position pos;
	
	public Item(Position position) {
		this.pos=position;
	}
	
	
	@Override
	public void setX(float x) {
		this.pos.setX(x);
	}
	
	@Override
	public float getX() {
		return this.pos.getX();
	}
	
	@Override
	public void setY(float y) {
		this.pos.setY(y);
	}	
	
	@Override
	public float getY() {
		return this.pos.getY();
	}
	
	@Override
	public Position getPosition() {
		return pos;
	}
	
	@Override
	public void setPosition(Position p) {
		this.pos =p;
	}
	
}
