package model.sprites;

import model.geometrical.Position;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponFactory;
import model.items.weapons.WeaponFactory.Level;
import model.items.weapons.WeaponFactory.Type;

public class EnemyFactory {
	/**
	 * Creates an easy enemy
	 * @return an easy enemy
	 */
	public static Enemy createEasyEnemy(Position pos){
		return new Enemy(pos, 0.1f, 
			WeaponFactory.CreateWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.RUSTY), 50);
	}
	/**
	 * Creates a medium enemy
	 * @return a medium enemy
	 */
	public static Enemy createMediumEnemy(Position pos){
		return new Enemy(pos, 0.2f, WeaponFactory.CreateWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.LARGE), 100);
	}
	/**
	 * Creates a hard enemy
	 * @return a hard enemy
	 */
	public static Enemy createHardEnemy(Position pos){
		return new Enemy(pos, 0.25f, WeaponFactory.CreateWeapon(WeaponFactory.Type.FISTS, 
				WeaponFactory.Level.EPIC), 150);
	}

}
