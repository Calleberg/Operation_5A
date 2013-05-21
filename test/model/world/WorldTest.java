package model.world;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import model.geometrical.Position;
import model.items.Item;
import model.items.SupplyFactory;
import model.items.weapons.Projectile;
import model.sprites.EnemyFactory;
import model.sprites.Player;
import model.sprites.Sprite;
import model.world.props.PropFactory;

import org.junit.Test;

public class WorldTest {
	
	@Test
	public void setGetPlayerTest() {
		World w = new World();
		assertTrue(w.getPlayer() == null);
		
		Player p = new Player(1, 2);
		w.setPlayer(p);
		assertTrue(w.getPlayer() == p);
		
		w.setPlayer(null);
		assertTrue(w.getPlayer() == null);
	}
		
	@Test
	public void getTiles(){
		Tile[][] tiles = new Tile[10][10];
		for (int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		
		World w = new World(tiles);
		
		Tile[][] testTiles= w.getTiles();
		
		assertTrue(testTiles.length == 10 && testTiles[9][9] != null);
	}
	
	@Test
	public void setTiles(){
		Tile[][] tiles = new Tile[10][10];
		for (int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		
		World w = new World(null);
		
		w.setTiles(tiles);
		
		assertTrue(w.getTiles().length == 10 && w.getTiles()[9][9] != null);
	}
	
	@Test
	public void addSprite(){
		World w = new World(null);
		Sprite s = new Player(1,1);
		w.addSprite(s);
		
		assertTrue(w.getSprites().size() == 1 && w.getSprites().contains(s));
	}
	
	@Test
	public void removeSprite(){
		World w = new World(null);
		Sprite s = new Player(1,1);
		w.addSprite(s);
		w.removeSprite(s);
		assertTrue(w.getSprites().size() == 0);
	}
	
	@Test
	public void removeSprites(){
		World w = new World(null);
		List<Sprite> sprites = new ArrayList<Sprite>(); 
		sprites.add(EnemyFactory.createEasyEnemy(new Position(1,1))); 
		sprites.add(EnemyFactory.createEasyEnemy(new Position(1,2)));
		
		for(int i = 0; i < sprites.size(); i++){
			w.addSprite(sprites.get(i));
		}
		
		assertTrue(w.getSprites().size() == 2);

		w.removeSprites(sprites);
		
		assertTrue(w.getSprites().size() == 0);
	}
	
	@Test
	public void getSprites(){
		World w = new World(null);
		Sprite e = EnemyFactory.createEasyEnemy(new Position(1,1));
		Sprite p = new Player(1,1);
		
		w.addSprite(p);
		w.addSprite(e);
		
		assertTrue(w.getSprites().size() == 2 && w.getSprites().contains(p) 
			&& w.getSprites().contains(e));
	}
	
	@Test
	public void addProjectile(){
		World w = new World(null);
		Projectile p = new Projectile(1,1,1,1,new Position(1,1));
		
		w.addProjectile(p);
		
		assertTrue(w.getProjectiles().size() == 1 && w.getProjectiles().contains(p));
	}
	
	@Test
	public void removeProjectile(){
		World w = new World(null);
		Projectile p = new Projectile(1,1,1,1,new Position(1,1));
		
		w.addProjectile(p);
		w.removeProjectile(p);
		
		assertTrue(w.getProjectiles().size() == 0);
	}
	
	@Test
	public void removeProjectiles(){
		World w = new World(null);
		List<Projectile> projectiles = new ArrayList<Projectile>();
		projectiles.add(new Projectile(1,1,1,1, new Position(1,1)));
		projectiles.add(new Projectile(1,1,1,1, new Position(1,2)));
		
		for(int i = 0; i < projectiles.size(); i++){
			w.addProjectile(projectiles.get(i));
		}
		
		assertTrue(w.getProjectiles().size() == 2);
		
		w.removeProjectiles(projectiles);
		
		assertTrue(w.getProjectiles().size() == 0);
		
	}

	@Test
	public void getProjectiles(){
		World w = new World(null);
		Projectile p1 = new Projectile(1,1,1,1, new Position(1,1));
		Projectile p2 = new Projectile(1,1,1,1, new Position(1,1));
		
		w.addProjectile(p1);
		w.addProjectile(p2);
		
		assertTrue(w.getProjectiles().size() == 2 && w.getProjectiles().contains(p1)
			&& w.getProjectiles().contains(p2));
	}
	
	@Test
	public void canMove() {
		Tile[][] tiles = WorldBuilder.getEmptyWorld(10, 10);
		tiles[1][1].setNorthWall(true);
		tiles[2][1].setNorthWall(true);
		
		tiles[1][1].setWestWall(true);
		tiles[1][2].setWestWall(true);
		
		tiles[1][3].setNorthWall(true);
		tiles[2][3].setNorthWall(true);
		
		tiles[3][1].setWestWall(true);
		tiles[3][2].setWestWall(true);
		
		tiles[5][7].setProperty(Tile.UNWALKABLE);
		tiles[9][8].addProp(PropFactory.getProp(new Position(9,8), 0));
		
		World w = new World(tiles);
		
		assertFalse(w.canMove(new Position(0 +0.5f,1 +0.5f), new Position(1 +0.5f,0 +0.5f))); //walk NE
		assertFalse(w.canMove(new Position(1 +0.5f,0 +0.5f), new Position(0 +0.5f,1 +0.5f))); //walk SW
		
		assertFalse(w.canMove(new Position(2 +0.5f,3 +0.5f), new Position(3 +0.5f,2 +0.5f))); //walk NE
		assertFalse(w.canMove(new Position(3 +0.5f,2 +0.5f), new Position(2 +0.5f,3 +0.5f))); //walk SW
		
		assertFalse(w.canMove(new Position(2 +0.5f,3 +0.5f), new Position(2 +0.5f,2 +0.5f))); //walk through north wall
		assertFalse(w.canMove(new Position(2 +0.5f,2 +0.5f), new Position(2 +0.5f,3 +0.5f))); //walk through south wall
		
		assertFalse(w.canMove(new Position(3 +0.5f,2 +0.5f), new Position(2 +0.5f,2 +0.5f))); //walk through west wall
		assertFalse(w.canMove(new Position(2 +0.5f,2 +0.5f), new Position(3 +0.5f,2 +0.5f))); //walk through east wall
		
		//Tests some paths the player can actually travel
		assertTrue(w.canMove(new Position(0 +0.5f,1 +0.5f), new Position(0 +0.5f,2 +0.5f)));
		assertTrue(w.canMove(new Position(0 +0.5f,2 +0.5f), new Position(0 +0.5f,1 +0.5f)));
		
		assertTrue(w.canMove(new Position(1 +0.5f,0 +0.5f), new Position(2 +0.5f,0 +0.5f)));
		assertTrue(w.canMove(new Position(2 +0.5f,0 +0.5f), new Position(1 +0.5f,0 +0.5f)));
		
		assertTrue(w.canMove(new Position(5 +0.5f,5 +0.5f), new Position(5 +0.5f,6 +0.5f)));
		
		assertFalse(w.canMove(new Position(4 +0.5f, 7 +0.5f), new Position(5 +0.5f, 7 +0.5f))); //walk through unwalkable tile
	
		assertFalse(w.canMove(new Position(8 +0.5f,8 +0.5f), new Position(9 +0.5f,8 +0.5f)));

	}
	
	@Test
	public void validPosition(){
		Tile[][] tiles = new Tile[20][20];
		for (int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		World w = new World(tiles);
		
		
		Position p1 = new Position(-1, 1);
		assertTrue(!(w.validPosition(p1)));
		
		Position p2 = new Position(w.getWidth() + 1, 1);
		assertTrue(!(w.validPosition(p2)));
		
		Position p3 = new Position(1, -1);
		assertTrue(!(w.validPosition(p3)));
		
		Position p4 = new Position(1, w.getHeight() + 1);
		assertTrue(!(w.validPosition(p4)));
		
		
	}
	
	@Test
	public void getWidth(){
		Tile[][] tiles = new Tile[15][10];
		for (int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		
		World w = new World(tiles);
		
		assertTrue(w.getWidth() == 15);
	}
	
	@Test
	public void getHeight(){
		Tile[][] tiles = new Tile[15][10];
		for (int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		
		World w = new World(tiles);
		
		assertTrue(w.getHeight() == 10);
	}
	
	@Test
	public void getAndAddRemoveItemsTest() {
		World w = new World();
		assertTrue(w.getItems().size() == 0);
		
		Item i = SupplyFactory.createFood(100, new Position(0, 0));
		w.addItem(i);
		assertTrue(w.getItems().size() == 1);
		assertTrue(w.getItems().contains(i));
		
		Item i2 = SupplyFactory.createRandomAmmo(new Position(0, 0));
		w.addItem(i2);
		assertTrue(w.getItems().size() == 2);
		assertTrue(w.getItems().contains(i2));
		
		w.removeItem(i);
		assertTrue(w.getItems().size() == 1);
		assertTrue(!w.getItems().contains(i));
	}

	
}
