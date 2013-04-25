package model;

import static org.junit.Assert.*;

import model.GameModel;
import model.world.World;
import model.sprites.Player;

import org.junit.Test;

public class GameModelTest {
	
	GameModel model = new GameModel();
	World w = model.getWorld();
	Player p = model.getPlayer();

	@Test
	public void playerShoot() {
		int size = w.getProjectiles().size();
		model.playerShoot();
		
		//actually add a projectile
		assertTrue(w.getProjectiles().size() == size + 1);
		
		//projectile is created on the correct position
		assertTrue(p.getPosition().getX() == w.getProjectiles().get(size).getPosition().getX());
		assertTrue(p.getPosition().getY() == w.getProjectiles().get(size).getPosition().getY());
		
		//projectile is created with the correct direction
		assertTrue(p.getDirection() == w.getProjectiles().get(size).getDirection());
	}
	
	@Test
	public void getPlayer(){
		assertTrue(model.getPlayer() != null);
	}
	
	@Test
	public void getWorld(){
		assertTrue(model.getWorld() != null);
	}

}
