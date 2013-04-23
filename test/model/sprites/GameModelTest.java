package model.sprites;

import static org.junit.Assert.*;

import model.GameModel;
import model.world.World;

import org.junit.Test;

public class GameModelTest {

	@Test
	public void playerShoot() {
		GameModel model = new GameModel();
		World w = model.getWorld();
		Player p = model.getPlayer();

		int size = w.getProjectiles().size();
		model.playerShoot();
		
		//actually add a projectile
		assertTrue(w.getProjectiles().size() == size + 1);
		
		//projectile is created on the correct position
		assertTrue(p.getPosition().getX() == w.getProjectiles().get(size).getPosition().getX());
		assertTrue(p.getPosition().getY() == w.getProjectiles().get(size).getPosition().getY());
		
		//projectile is created with the correct direction
		assertTrue(p.getDirection() == w.getProjectiles().get(size).getDirection());
		p.setDirection((float) Math.PI);
		model.playerShoot();
		assertTrue(p.getDirection() == w.getProjectiles().get(size+1).getDirection());
	}
	
	@Test
	public void update(){
		//TODO ska den testas?
	}

}
