package model.items.weapons;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

public class TestWeapon {

//	@Test
//	public void Weapon(){
//		int speed =100;
//		int damage = 56;
//		int range = 99;
//		int ammunitionAmmount =123;
//		int ammoInWeapon = 45;
//		int reloadTime =42;
//
//		Weapon w = new Weapon(speed, damage, range, ammunitionAmmount, ammoInWeapon, reloadTime);
//
//
//		//Checks all the get methods of the values set in the constructor
//		assertTrue(w.getAmmunitionInMagazine()==ammunitionAmmount);
//		assertTrue(w.getDamage()==damage);
//		assertTrue(w.getRange()==range);
//		assertTrue(w.getMagazineCapacity() ==ammunitionAmmount);
//		assertTrue(w.getAmmunitionInMagazine()==ammoInWeapon);
//		assertTrue(w.getReloadTime()==reloadTime);
//
//
//	}

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
	}
	
	@Test
	public void reloadTest(){
		int testValue = 42;
		Weapon w = new Weapon(1, 1, 1, 1, 1, 1, 1,"");
		assertTrue(w.reload(testValue)==testValue);
		w.createProjectile(1, new Position(1,3));
		assertTrue(w.reload(testValue)==testValue-1);
		
	}

}