package model.items;

import model.geometrical.Position;
import model.geometrical.*;

/**
 * 
 * @author Vidar
 *
 */
public class Supply extends Item {
	
	/**
	 * Describes the different types of supplys.
	 * @author Vidar
	 *
	 */
	public static enum Type {
		FOOD (1, "Food"),
		AMMO (2, "Ammo"),
		HEALTH (3, "Health");
		
		private final int iconNumber;
		private final String name;
		Type (int i, String s){
			this.iconNumber=i;
			this.name=s;
		}

		public int getIconNumber() {
			return this.iconNumber;
		}
		
		public String toString(){
			return name;
		}
	}
	
	private int amount;
	private Type type;
	private Position pos;
	private CollisionBox cBox;

	/**
	 * 
	 * @param amount the amount the Supply holds
	 * @param type the type the Supply is
	 * @param position
	 */
	protected Supply(int amount, Type type, Position pos){
		super(null, type.getIconNumber());
		this.amount=amount;
		this.type=type;
		this.pos = pos;
		cBox = new Rectangle(pos.getX(),pos.getY(),1,1);
	}
	
	/**
	 * returns the amount gained from the Supply.
	 * @return the amount gained from the Supply.
	 */
	public int getAmount(){
		return amount;
	}
	
	/**
	 * returns the type of Supply.
	 * @return the type of Supply.
	 */
	public Type getType(){
		return type;
		
	}
	/**
	 * Gives the name of the supply.
	 */
	public String toString(){
		return amount + " " + type.toString();
	}
	
	/**
	 * returns the position of the supply
	 * @return the position of the supply
	 */
	public Position getPosition(){
		return this.pos;
	}

	public CollisionBox getCollisionBox() {
		return this.cBox;
	}
}
