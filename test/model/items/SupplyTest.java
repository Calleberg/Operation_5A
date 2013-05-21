package model.items;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

public class SupplyTest {

	 Supply ammo = SupplyFactory.createAmmo(20, new Position(1,1));
	 Supply food = SupplyFactory.createFood(20, new Position(1,3));
	 Supply health = SupplyFactory.createHealth(20, new Position(1,2));
	 Supply s = SupplyFactory.createAmmo(30, new Position(2,1));
	 String[] data = {"20", "2", "2", "Food"};
	 
	@Test
	public void getIconNumber() {
		 assertTrue(ammo.getType().getIconNumber() == 21);
		 assertTrue(food.getType().getIconNumber() == 20);
		 assertTrue(health.getType().getIconNumber() == 22);
	}
	
	@Test
	public void toStringType(){
		assertTrue(ammo.getType().toString() == "Ammo");
		assertTrue(food.getType().toString() == "Food");
		assertTrue(health.getType().toString() == "Health");
	}
	
	@Test
	public void fromString(){
		assertTrue(Supply.Type.fromString("Ammo") == Supply.Type.AMMO);
	}
	
	@Test
	public void getAmount(){
		assertTrue(ammo.getAmount() == 20);
		assertTrue(food.getAmount() == 20);
		assertTrue(health.getAmount() == 20);
	}
	
	@Test
	public void getType(){
		assertTrue(ammo.getType() == Supply.Type.AMMO);
		assertTrue(food.getType() == Supply.Type.FOOD);
		assertTrue(health.getType() == Supply.Type.HEALTH);
	}
	
	@Test
	public void toStringSupply(){
		assertTrue(ammo.toString().equals("20 Ammo"));
		assertTrue(food.toString().equals("20 Food"));
		assertTrue(health.toString().equals("20 Health"));
	}
	
	@Test
	public void restore(){
		s.restore(data);
		assertTrue(s.getPosition().getX() == 2 && s.getPosition().getY() == 2);
		assertTrue(s.getIconNumber() == 20);
		assertTrue(s.getType() == Supply.Type.FOOD);
	}
	
	@Test
	public void getData(){
		s.restore(data);
		String[] d = s.getData();
		assertTrue(d[0].equals("20"));
		assertTrue(d[1].equals("2.0"));
		assertTrue(d[2].equals("2.0"));
		assertTrue(d[3].equals("Food"));
	}

}
