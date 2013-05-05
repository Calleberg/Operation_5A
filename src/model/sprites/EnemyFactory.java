package model.sprites;

import model.geometrical.Position;
import model.items.weapons.WeaponFactory;

public class EnemyFactory {
	/**
	 * Creates an easy enemy
	 * @return an easy enemy
	 */
	public static Enemy createEasyEnemy(Position pos){
		return new Enemy(pos, 0.03f, 
			WeaponFactory.createWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.RUSTY), 50);
	}
	/**
	 * Creates a medium enemy
	 * @return a medium enemy
	 */
	public static Enemy createMediumEnemy(Position pos){
		return new Enemy(pos, 0.02f, WeaponFactory.createWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.LARGE), 100);
	}
	/**
	 * Creates a hard enemy
	 * @return a hard enemy
	 */
	public static Enemy createHardEnemy(Position pos){
		return new Enemy(pos, 0.25f, WeaponFactory.createWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.EPIC), 150);
	}

}
