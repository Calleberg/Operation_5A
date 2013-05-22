package model.sprites;

import model.geometrical.Position;
import model.items.weapons.WeaponFactory;

/**
 * A factory which creates enemies.
 * 
 * @author 
 *
 */
public class EnemyFactory {
	
	/**
	 * Creates an easy enemy
	 * @return an easy enemy
	 */
	public static Enemy createEasyEnemy(Position pos){
		return new Enemy(pos, 0.045f, 
			WeaponFactory.createWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.NORMAL), 50);
	}
	
	/**
	 * Creates a medium enemy
	 * @return a medium enemy
	 */
	public static Enemy createMediumEnemy(Position pos){
		return new Enemy(pos, 0.08f, WeaponFactory.createWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.LARGE), 100);
	}
	
	/**
	 * Creates a hard enemy
	 * @return a hard enemy
	 */
	public static Enemy createHardEnemy(Position pos){
		return new Enemy(pos, 0.09f, WeaponFactory.createWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.BADASS), 150);
	}

}
