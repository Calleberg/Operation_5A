package model;

import static org.junit.Assert.*;

import model.GameModel;
import model.world.World;
import model.items.weapons.WeaponFactory;
import model.items.weapons.WeaponFactory.Level;
import model.items.weapons.WeaponFactory.Type;
import model.sprites.Player;

import org.junit.Test;

public class GameModelTest {
	
	GameModel model = new GameModel();
	World w = model.getWorld();
	Player p = new Player(1,1);

	@Test
	public void playerShoot() {
		int size = w.getProjectiles().size();
		model.setPlayer(p);
		p.setWeapon(WeaponFactory.createWeapon(Type.PISTOL, Level.LARGE));
		model.playerShoot();
		
		//actually add a projectile
		assertTrue(w.getProjectiles().size() == size + 1);
		
		//projectile is created on the correct position
		assertTrue(p.getProjectileSpawn().getX() == w.getProjectiles().get(size).getPosition().getX());
		assertTrue(p.getProjectileSpawn().getY() == w.getProjectiles().get(size).getPosition().getY());
		
		//projectile is created with the correct direction
		assertTrue(p.getDirection() == w.getProjectiles().get(size).getDirection());
	}
	
	@Test
	public void getPlayer(){
		model.setPlayer(p);
		assertTrue(model.getPlayer() != null);
	}
	
	@Test
	public void getWorld(){
		assertTrue(model.getWorld() != null);
	}
	
	@Test
	public void setPlayer(){
		model.setPlayer(p);
		assertTrue(w.getSprites().size() == 1 && w.getSprites().contains(p));
	}

}
