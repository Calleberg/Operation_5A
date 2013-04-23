package model.world;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.items.weapons.Projectile;
import model.sprites.EnemyFactory;
import model.sprites.Player;
import model.sprites.Sprite;

import org.junit.Test;

public class WorldTest {

	@Test
	public void testGetSetAndRemoveSprite() {
		World w = new World(null);
		Sprite s1 = new Player(1f, 1f);
		EnemyFactory ef = new EnemyFactory();
		Sprite s2 = ef.createEasyEnemy(new Position(2f, 2f));
		w.addSprite(s1);
		assertTrue(w.getSprites().size() == 1);
		
		w.addSprite(s2);
		assertTrue(w.getSprites().size() == 2);
		
		assertTrue(w.getSprites().contains(s2));
		
		w.removeSprite(s2);
		assertFalse(w.getSprites().contains(s2));
		
		assertTrue(w.getSprites().size() == 1);
	}
	
	@Test
	public void testGetSetAndRemoveProjectiles() {
		World w = new World(null);
		Projectile p = new Projectile(1, 1f, 0f, 0f, new Position(1f, 1f));
		w.addProjectile(p);
		assertTrue(w.getProjectiles().size() == 1);
		w.addProjectile(new Projectile(1, 1f, 0f, 0f, new Position(1f, 1f)));
		assertTrue(w.getProjectiles().size() == 2);
		
		assertTrue(w.getProjectiles().contains(p));
		
		w.removeProjectile(p);
		assertTrue(w.getProjectiles().size() == 1);
		assertFalse(w.getProjectiles().contains(p));
	}
	
	@Test
	public void testUpdate() {
		World w = new World(null);
		Projectile p = new Projectile(1, 1f, 0f, 0f, new Position(1f, 1f));
		w.addProjectile(p);
		w.update();
		assertTrue(p.getPosition().getX() == 2f);
	}

}
