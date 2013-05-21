package model.items;

import model.geometrical.CollisionBox;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.other.Saveable;
import model.other.WorldObject;


public abstract class Item implements WorldObject, Saveable{
	
	private Position pos;
	private int iconNumber;
	private CollisionBox cBox;
	
	/**
	 * Creates a new item at the specified position and with the specified icon.
	 * @param position the position.
	 * @param iconNumber the number of the icon to use.
	 */
	public Item(Position position, int iconNumber) {
		this.pos=position;
		this.iconNumber=iconNumber;
		if(position != null){
			cBox = new Rectangle(pos.getX(),pos.getY(),1,1);
		}
	}
	
	
//	@Override
//	public void setX(float x) {
//		this.pos.setX(x);
//	}
//	
//	@Override
//	public float getX() {
//		return this.pos.getX();
//	}
//	
//	@Override
//	public void setY(float y) {
//		this.pos.setY(y);
//	}	
//	
//	@Override
//	public float getY() {
//		return this.pos.getY();
//	}
	
	@Override
	public Position getPosition() {
		return pos;
	}
	
	@Override
	public void setPosition(Position p) {
		this.pos =p;
		if(cBox != null){
			this.cBox.setPosition(p);
		}else{
			this.cBox = new Rectangle(p.getX(),p.getY(),1,1);
		}
	}

	/**
	 * 
	 * @return the icon number of the item.
	 */
	public int getIconNumber() {
		return iconNumber;
	}
	
	/**
	 * Sets the icon which should be displayed when rendered.
	 * @param icon the icon to render.
	 */
	public void setIconNumber(int icon) {
		this.iconNumber = icon;
	}
	
	/**
	 * Return the collisionBox of the item.
	 * @return the collisionBox of the item.
	 */
	public CollisionBox getCollisionBox() {
		return this.cBox;
	}
	
}
