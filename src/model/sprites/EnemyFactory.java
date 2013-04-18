package model.sprites;

import model.geometrical.Position;

public class EnemyFactory {
	/**
	 * Creates an easy enemy
	 * @return an easy enemy
	 */
	public Enemy createEasyEnemy(){
		return new Enemy(new Position(1,1), 0.1f, null, 50);
	}
	/**
	 * Creates a medium enemy
	 * @return a medium enemy
	 */
	public Enemy createMediumEnemy(){
		return new Enemy(new Position(1,1), 0.2f, null, 100);
	}
	/**
	 * Creates a hard enemy
	 * @return a hard enemy
	 */
	public Enemy createHardEnemy(){
		return new Enemy(new Position(1,1), 0.25f, null, 150);
	}

}
