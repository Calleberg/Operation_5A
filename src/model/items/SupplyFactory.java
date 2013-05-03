package model.items;

import model.geometrical.Position;
import model.items.Supply;
import model.items.Supply.Type;

/**
 * 
 * @author Vidar Eriksson, Jonatan Magnusson
 *
 */
public final class SupplyFactory {
	
	/**
	 * Will create a completely random supply.
	 * @return a new random supply.
	 */
	public static Supply createRandomSupply(Position pos){
		Supply s;
		int random=(int)Math.random()*100;
		if (random<33){
			s=createRandomAmmo(pos);
		} else if (random < 66){
			s=createRandomFood(pos);
		} else {
			s=createRandomHealth(pos);
		}
		return s;
	}
	/**
	 * Creates an Ammo with a random value between 0 and 18
	 * @return an Ammo with a random value
	 */
	public static Supply createRandomAmmo(Position pos) {
		return new Supply((int)Math.random()*18, Type.AMMO, pos);
	}
	
	/**
	 * Creates a Food with a random value between 0 and 50
	 * @return a Food with a random value
	 */
	public static Supply createRandomFood(Position pos) {
		return new Supply((int)Math.random()*50, Type.FOOD, pos);
	}
	
	/**
	 * Creates a Health with a random value between 0 and 50
	 * @return a Health with a random value
	 */
	public static Supply createRandomHealth(Position pos) {
		return new Supply((int)Math.random()*50, Type.HEALTH, pos);
	}
	
	/**
	 * Creates an Ammo with the given value
	 * @param value the value given
	 * @return an Ammo with a given value
	 */
	public static Supply createAmmo(int value, Position pos){
		return new Supply(value, Type.AMMO, pos);
	}
	
	/**
	 * Creates a Food with the given value
	 * @param value the value given
	 * @return a Food with a given value
	 */
	public static Supply createFood(int value, Position pos){
		return new Supply(value, Type.FOOD, pos);
	}
	
	/**
	 * Creates a Health with the given value
	 * @param value the value given
	 * @return a Health with a given value
	 */
	public static Supply createHealth(int value, Position pos){
		return new Supply(value, Type.HEALTH, pos);
	}
}