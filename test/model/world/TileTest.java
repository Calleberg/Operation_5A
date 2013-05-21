package model.world;

import static org.junit.Assert.assertTrue;
import model.geometrical.CollisionBox;
import model.geometrical.Line;
import model.geometrical.Position;
import model.geometrical.Rectangle;
import model.world.props.Prop;
import model.world.props.PropFactory;

import org.junit.Test;

public class TileTest {

	@Test
	public void getFloor(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.getFloor() == 3);
	}
	
	@Test
	public void setFloor(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		t.setFloor(4);
		assertTrue(t.getFloor() == 4);
	}
	
	@Test
	public void hasNorthWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.hasNorthWall() == true);
	}
	
	@Test
	public void setNorthWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		t.setNorthWall(false);
		assertTrue(t.hasNorthWall() == false);
	}
	
	@Test
	public void hasWestWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.hasWestWall() == true);
	}
	
	@Test
	public void setWestWall(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		t.setWestWall(false);
		assertTrue(t.hasWestWall() == false);
	}
	
	@Test
	public void setX(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		t.getPosition().setX(4);
		assertTrue(t.getPosition().getX() == 4);
	}
	
	@Test
	public void setY(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		t.getPosition().setY(5);
		assertTrue(t.getPosition().getY() == 5);
	}
	
	@Test
	public void getX(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.getPosition().getX() == 1);
	}
	
	@Test
	public void getY(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.getPosition().getY() == 1);
	}
	
	@Test
	public void getPosition(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.getPosition().getX() == 1 && t.getPosition().getY() == 1);
	}
	
	@Test
	public void setPosition(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		t.setPosition(new Position(3,5));
		assertTrue(t.getPosition().getX() == 3 && t.getPosition().getY() == 5);
	}
	
	@Test
	public void getSetPropertyTest(){
		Tile t = new Tile(new Position(1, 1), 3, true, true, Tile.NONE);
		assertTrue(t.getProperty() == 0);
		
		t.setProperty(Tile.HEALTH_SPAWN);
		assertTrue(t.getProperty() == 1);
		
		t.setProperty(Tile.FOOD_SPAWN);
		assertTrue(t.getProperty() == 2);
		
		t.setProperty(Tile.AMMO_SPAWN);
		assertTrue(t.getProperty() == 3);
		
		t.setProperty(Tile.WEAPON_SPAWN);
		assertTrue(t.getProperty() == 4);
		
		t.setProperty(Tile.UNWALKABLE);
		assertTrue(t.getProperty() == 5);
	}
	
	@Test
	public void hasProp() {
		Tile t = new Tile(new Position(0, 0), 0);
		assertTrue(!t.hasProps());
		
		t.addProp(PropFactory.getProp(t.getPosition(), 1));
		assertTrue(t.hasProps());
	}
	
	@Test
	public void addRemovePropTest() {
		Tile t = new Tile(new Position(0, 0), 0);
		assertTrue(t.getProps().size() == 0);
		
		Prop p = PropFactory.getProp(t.getPosition(), 1);
		t.addProp(p);
		assertTrue(t.getProps().contains(p));
		assertTrue(t.getProps().size() == 1);
		
		t.removeProp(p);
		assertTrue(!t.getProps().contains(p));
		assertTrue(t.getProps().size() == 0);
	}
	
	@Test
	public void intersects() {
		Tile t = new Tile(new Position(0, 0), 0);
		CollisionBox box1 = new Rectangle(0, 0, 1f, 1f);
		assertTrue(t.intersects(box1));
		
		CollisionBox box2 = new Line(0, 0, 1f, 1f);
		assertTrue(t.intersects(box2));
	}
	
	@Test
	public void getSetPositionTest() {
		Tile t = new Tile(new Position(20, 30), 0);
		assertTrue(t.getPosition().getX() == 20);
		assertTrue(t.getPosition().getY() == 30);
		
		t.setPosition(new Position(10, 12));
		assertTrue(t.getPosition().getX() == 10);
		assertTrue(t.getPosition().getY() == 12);
	}
}

