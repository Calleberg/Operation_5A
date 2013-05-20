package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.GameModel;
import model.world.Tile;
import model.world.World;
import model.items.weapons.WeaponFactory;
import model.items.weapons.WeaponFactory.Level;
import model.items.weapons.WeaponFactory.Type;
import model.sprites.Player;

import org.junit.Test;

public class GameModelTest {
	
	World w = new World();
	GameModel model = new GameModel(w);
	Player p = new Player(1,1);
	//TODO update, propertyChange cant be tested?
	
	
	@Test
	public void setSpawns(){
		List<Tile> list = new ArrayList<Tile>();
		model.setSpawns(list);
		assertTrue(model.getSpawnPoints() == list);
	}
	
	@Test
	public void getSpawnPoints(){
		List<Tile> list = new ArrayList<Tile>();
		model.setSpawns(list);
		assertTrue(model.getSpawnPoints() == list);
	}
	
	@Test
	public void getPlayer(){
		model.setPlayer(p);
		assertTrue(model.getPlayer() == p);
	}
	
	@Test
	public void getWorld(){
		assertTrue(model.getWorld() == w);
	}
	
	@Test
	public void setPlayer(){
		model.setPlayer(p);
		assertTrue(model.getPlayer() == p);
	}

}
