package model.items.weapons;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

public class WeaponTest {
	@Test
	public void getAmmunitionInMagazineTest(){
		int testValue = 42;
		Weapon w = new Weapon(1, 1, 1, testValue, 1, 1, 1,"");
		assertTrue( w.getAmmunitionInMagazine()==testValue);
	}

	@Test
	public void getMagazineCapacityTest(){
		int testValue = 42;
		Weapon w = new Weapon(1, 1, 1, testValue, 1, 1, 1,"");
		assertTrue( w.getMagazineCapacity()==testValue);
	}
	
	@Test
	public void createProjectileTest(){
		int testValue = 42;
		Weapon w = new Weapon(1, 1, 1, 1, 1, 1, 1,"");
		w.createProjectile(1, new Position(1,3));
		assertTrue("Did not decrease ammunition ammount of weapon", w.getAmmunitionInMagazine()==0);
		assertTrue("Should not fire with empty magazine", w.createProjectile(1, new Position(1,3))==null);
	}
	
	@Test
	public void reloadTest(){
		int testValue = 42;
		Weapon w = new Weapon(1, 1, 1, 1, 1, 1, 1,"");
		assertTrue(w.reload(testValue)==testValue);
		w.createProjectile(1, new Position(1,3));
		assertTrue(w.reload(testValue)==testValue-1);
		
	}
	
	@Test
	public void toStringTest(){
		Weapon w = new Weapon(1,1,1,1,1,1,1,"testName");
		
		assertTrue(w.toString().equals("testName"));
	}
	

}