package model.items.weapons;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

/**
 * 
 * @author Jonatan Magnusson, Linus Hagvall
 *
 */
public class WeaponTest {
	@Test
	public void createProjectileTest(){
		
		Weapon w = WeaponFactory.createTestWeapon(Weapon.Type.GUN);
		assertTrue(w.createProjectile(1, new Position(1,1)) != null);
		
		assertTrue("Should not be able to fire two shots in a row", 
				w.createProjectile(1, new Position(1,1)) == null);
		
		assertTrue(w.createProjectile(1, new Position(1,1)) == null);
	}
	
	@Test
	public void getAmmunitionInMagazineTest(){
		Weapon w = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, 
				WeaponFactory.Level.NORMAL);
		int testValue = w.getAmmunitionInMagazine();
		assertTrue( w.getAmmunitionInMagazine()== testValue);
		w.createProjectile(1, new Position(1,1));
		assertTrue(w.getAmmunitionInMagazine() == testValue - 1);
	}
	@Test
	public void getDataTest(){
		Weapon w = WeaponFactory.createRandomWeapon();
		String[] s = w.getData();
		Weapon w2 = WeaponFactory.loadWeapon(s);
		String[] s2 = w2.getData();
		for(int i = 0; i<s.length; i++){
			assertTrue(s[i].equals(s2[i]));
		}
	}

	@Test
	public void getMagazineCapacityTest(){
		Weapon w = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, 
				WeaponFactory.Level.NORMAL);
		w.reload(10000);//More than MagazineCapacity
		assertTrue( w.getMagazineCapacity()==w.getAmmunitionInMagazine());
	}

	@Test
	public void getRangeTest(){
		int pistolRange = 50;
		Weapon w = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, 
				WeaponFactory.Level.NORMAL);
		assertTrue(w.getRange() == pistolRange);
	}
	
	@Test
	public void isDroppableTest(){
		Weapon w1 = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, 
				WeaponFactory.Level.NORMAL);
		Weapon w2 = WeaponFactory.createEnemyMeleeWeapon();
		assertTrue(w1.isDroppable() && !(w2.isDroppable()));
	}
	@Test
	public void reloadTest(){
		Weapon w = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, 
				WeaponFactory.Level.NORMAL);
		int testValue = w.getAmmunitionInMagazine();
		w.createProjectile(1, new Position(1,3));
		assertTrue(w.reload(testValue)==testValue-1);
		w.reload(10000);//more than magazineCapacity
		assertTrue(w.getAmmunitionInMagazine() == w.getMagazineCapacity());
		
	}
	
	@Test
	public void restoreTest(){
		//Not Used
	}
	
	
	@Test
	public void toStringTest(){
		Weapon w = WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, WeaponFactory.Level.NORMAL);
		
		assertTrue(w.getKeys()[0].equals("Normal"));
		assertTrue(w.getKeys()[1].equals("Pistol"));
	}
	

}