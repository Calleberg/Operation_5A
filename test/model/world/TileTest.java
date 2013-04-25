package model.world;

import static org.junit.Assert.assertTrue;
import model.geometrical.Position;

import org.junit.Test;

public class TileTest {

	@Test
	public void getFloor(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		assertTrue(t.getFloor() == 3);
	}
	
	@Test
	public void setFloor(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		t.setFloor(4);
		assertTrue(t.getFloor() == 4);
	}
	
	@Test
	public void hasNorthWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		assertTrue(t.hasNorthWall() == true);
	}
	
	@Test
	public void setNorthWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		t.setNorthWall(false);
		assertTrue(t.hasNorthWall() == false);
	}
	
	@Test
	public void hasWestWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		assertTrue(t.hasWestWall() == true);
	}
	
	@Test
	public void setWestWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		t.setWestWall(false);
		assertTrue(t.hasWestWall() == false);
	}
	
	@Test
	public void getCollisionBox(){
		//TODO not implemented in Tile
	}
	
	@Test
	public void setX(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		t.setX(4);
		assertTrue(t.getX() == 4);
	}
	
	@Test
	public void setY(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		t.setY(5);
		assertTrue(t.getY() == 5);
	}
	
	@Test
	public void getX(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		assertTrue(t.getX() == 1);
	}
	
	@Test
	public void getY(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		assertTrue(t.getY() == 1);
	}
	
	@Test
	public void getPosition(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		assertTrue(t.getX() == 1 && t.getY() == 1);
	}
	
	@Test
	public void setPosition(){
		Tile t = new Tile(new Position(1, 1), 3, true, true);
		t.setPosition(new Position(3,5));
		assertTrue(t.getX() == 3 && t.getY() == 5);
	}
}

