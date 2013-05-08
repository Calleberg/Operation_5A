package model.items;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.other.WorldObject;

//Empty interface created to group Items
public class Item implements WorldObject{
	private Position pos;
	private final int iconNumber;
	private CollisionBox cBox;
	
	public Item(Position position, int iconNumber) {
		this.pos=position;
		this.iconNumber=iconNumber;
		if(position != null){
			cBox = new Rectangle(pos.getX(),pos.getY(),1,1);
		}
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

	/**
	 * 
	 * @return the icon number of the item.
	 */
	public int getIconNumber() {
		return iconNumber;
	}
	
	/**
	 * Return the collisionBox of the item.
	 * @return the collisionBox of the item.
	 */
	public CollisionBox getCollisionBox() {
		return this.cBox;
	}
	
}
