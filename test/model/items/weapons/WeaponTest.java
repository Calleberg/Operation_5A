package model.items.weapons;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

public class WeaponTest {
//	@Test
//	public void createProjectileTest(){
//		//TODO fix with a better weapon
//		
//		Weapon w = new Weapon(1, 1, 1, 1, 1, 1000, 1, "testName");
//		assertTrue(w.createProjectile(1, new Position(1,1)) != null);
//		
//		assertTrue("Should not be able to fire two shots in a row", 
//				w.createProjectile(1, new Position(1,1)) == null);
//		
//		assertTrue(w.createProjectile(1, new Position(1,1)) == null);
//	}
//	
//	@Test
//	public void getAmmunitionInMagazineTest(){
//		int testValue = 42;
//		Weapon w = new Weapon(1, 1, 1, testValue, 1, 1, 1,"");
//		assertTrue( w.getAmmunitionInMagazine()== testValue);
//		w.createProjectile(1, new Position(1,1));
//		assertTrue(w.getAmmunitionInMagazine() == testValue - 1);
//	}
//	@Test
//	public void getDataTest(){
//		Weapon w = new Weapon(1,2,3,4,5,6,7,"w");
//		String[] s = w.getData();
//		assertTrue(s[0].equals("4") && s[1].equals("2") && s[2].equals("true") &&
//				s[3].equals("7") && s[4].equals("4") && s[5].equals("w") && s[6].equals("1.0") && 
//				s[7].equals("3.0") && s[8].equals("6") && s[9].equals("5") && s[10].equals("0") && 
//				s[11].equals("0"));
//	}
//
//	@Test
//	public void getMagazineCapacityTest(){
//		int testValue = 42;
//		Weapon w = new Weapon(1, 1, 1, testValue, 1, 1, 1,"");
//		assertTrue( w.getMagazineCapacity()==testValue);
//	}
//
//	@Test
//	public void getRangeTest(){
//		int testValue = 42;
//		Weapon w = new Weapon(1,1,testValue,1,1,1,1,"w");
//		assertTrue(w.getRange() == testValue);
//	}
//	
//	@Test
//	public void isDroppableTest(){
//		Weapon w1 = new Weapon(1,1,1,1,1,1,1,"w1", true);
//		Weapon w2 = new Weapon(1,1,1,1,1,1,1,"w2", false);
//		assertTrue(w1.isDroppable() && !(w2.isDroppable()));
//	}
//	@Test
//	public void reloadTest(){
//		int testValue = 42;
//		Weapon w = new Weapon(1, 1, 1, 1, 1, 1, 1,"");
//		assertTrue(w.reload(testValue)==testValue);
//		w.createProjectile(1, new Position(1,3));
//		assertTrue(w.reload(testValue)==testValue-1);
//		assertTrue(w.getAmmunitionInMagazine() == 1);
//		
//	}
//	
//	@Test
//	public void restoreTest(){
//		//Not Used
//	}
//	
//	@Test
//	public void saveWeaponTest(){
//		Weapon w = new Weapon(1,2,3,4,5,6,7,"w");
//		String s = w.saveWeapon();
//		assertTrue(s.equals("1.0#2#3.0#4#5#6#7#w#4#0#0#"));
//	}
//	
//	@Test
//	public void toStringTest(){
//		Weapon w = new Weapon(1,1,1,1,1,1,1,"testName");
//		assertTrue(w.toString().equals("testName"));
//		
//		Weapon w2 = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, WeaponFactory.Level.EPIC);
//		assertTrue(w2.toString().equals("Epic Pistol"));
//	}
	

}