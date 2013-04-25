package model.items;

import model.geometrical.Position;
/**
 * 
 * @author Vidar
 *
 */
public class Supply extends Item {
	
	public enum Type {
		FOOD (1, "Food"),
		AMMO (2, "Ammo"),
		HEALTH (3, "Health");
		
		private final int iconNumber;
		private final String name;
		Type (int i, String s){
			this.iconNumber=i;
			this.name=s;
		}

		private int getIconNumber() {
			return this.iconNumber;
		}
		
		public String toString(){
			return name;
		}
	}
	
	private int amount;
	private Type type;

	/**
	 * 
	 * @param amount
	 * @param type
	 * @param position
	 * @param image
	 */
	public Supply(int amount, Type type, Position position){
		super(position, type.getIconNumber());
		this.amount=amount;
		this.type=type;
	}
	
	/**
	 * 
	 * @return the amount of food gained from the Supply
	 */
	public int getAmount(){
		return amount;
	}
	
	public Type getType(){
		return type;
		
	}
	public String toString(){
		return amount + " " + type.toString();
	}
	
}
