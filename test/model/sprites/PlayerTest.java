package model.sprites;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.items.SupplyFactory;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponFactory;
import model.sprites.Player;
import model.sprites.Sprite.State;

import org.junit.Test;

public class PlayerTest {

	
	@Test
	public void moveXAxis(){//no exact values to allow player speed to differ.
		Player p = new Player(1,1);
		p.setState(Player.State.RUNNING);
		
		p.setMoveDir((float) (Math.PI/2));
		p.moveXAxis();
		assertTrue(p.getPosition().getX() == 1);
		assertTrue(p.getPosition().getY() == 1);
		
		p.setMoveDir((float) (Math.PI));
		p.moveXAxis();
		assertTrue(p.getPosition().getX() < 1);
		assertTrue(p.getPosition().getY() == 1);
		
		p.setMoveDir((float) (Math.PI/4));
		p.moveXAxis();
		assertTrue(p.getPosition().getX() < 1);
		assertTrue(p.getPosition().getY() == 1);
		
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		e.setState(Enemy.State.RUNNING);
		
		e.setDirection((float) (Math.PI/2));
		e.moveYAxis();
		assertTrue(e.getPosition().getX() == 1);
		assertTrue(e.getPosition().getY() == 0.8f);
		
		e.setDirection((float) (Math.PI));
		e.moveBack();
		e.moveYAxis();
		assertTrue(e.getPosition().getX() == 1);
		assertTrue(e.getPosition().getY() == 1);
		
		e.setDirection((float) (Math.PI/4));
		e.moveBack();
		e.moveYAxis();
		assertTrue(e.getPosition().getX() == 1);
		assertTrue(e.getPosition().getY() == (float)(1 - Math.sqrt(0.02)));
	}
	
	@Test
	public void moveYAxis(){//no set values to allow player speed to differ
		Player p = new Player(1, 1);
		p.setState(Player.State.RUNNING);
		
		p.setMoveDir((float) (Math.PI/2));
		p.moveYAxis();
		assertTrue(p.getPosition().getX() == 1);
		assertTrue(p.getPosition().getY() < 1);
		
		p.setMoveDir((float) (Math.PI));
		p.moveBack();
		p.moveYAxis();
		assertTrue(p.getPosition().getX() == 1);
		assertTrue(p.getPosition().getY() == 1);
		
		p.setMoveDir((float) (Math.PI/4));
		p.moveBack();
		p.moveYAxis();
		assertTrue(p.getPosition().getX() == 1);
		assertTrue(p.getPosition().getY() < 1);
	}
	//TODO remove move
	//TODO getMoveBox, getHitBox, moveBack,
	//TODO remove getcollisionBox
	
	@Test
	public void getMoveBoxWeapon(){
		//TODO
	}
	
	@Test
	public void getHitBoxWeapon(){
		//TODO
	}
	
	@Test
	public void switchWeapon(){
		Player p = new Player(1,1);
		
		Weapon w1 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w2 = WeaponFactory.createPlayerDefaultWeapon();
		p.switchWeapon(0);
		p.pickUpWeapon(w1);
		p.switchWeapon(1);
		p.pickUpWeapon(w2);
		
		assertTrue(p.getActiveWeapon() == w2);
		
		p.switchWeapon(0);
		assertTrue(p.getActiveWeapon() == w1);
	}
	
	@Test
	public void getIndex(){
		Player p = new Player(1,1);
		
		Weapon w1 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w2 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w3 = WeaponFactory.createEnemyMeleeWeapon();
		p.getWeapons()[0] = WeaponFactory.createPlayerDefaultWeapon();
		p.getWeapons()[1] = WeaponFactory.createPlayerDefaultWeapon();
		p.getWeapons()[2] = WeaponFactory.createPlayerDefaultWeapon();
		p.switchWeapon(0);
		p.pickUpWeapon(w1);
		p.switchWeapon(1);
		p.pickUpWeapon(w2);
		p.switchWeapon(2);
		p.pickUpWeapon(w3);
				
		assertTrue(p.getIndex(w1) == 0);
		assertTrue(p.getIndex(w2) == 1);
		assertTrue(p.getIndex(w3) == 2);
	}
	
	@Test
	public void getProjectileSpawn(){
		Player p = new Player(1,1);
		
		Position projectileSpawn = new Position(p.getPosition().getX() + 
				p.getHitBox().getWidth()/2 + (float)(Math.cos(p.getDirection())*0.45f) + 
				(float)(Math.cos(p.getDirection() - Math.PI/2)*0.2f), p.getPosition().getY() + 
				p.getHitBox().getHeight()/2 - (float)(Math.sin(p.getDirection())*0.45f) - 
				(float)(Math.sin(p.getDirection() - Math.PI/2)*0.2f));
		
		System.out.println(projectileSpawn + " " + p.getProjectileSpawn());
		assertTrue(p.getProjectileSpawn().getX() == projectileSpawn.getX());
		assertTrue(p.getProjectileSpawn().getY() == projectileSpawn.getY());
	}
//	return new Position(this.getPosition().getX() + getHitBox().getWidth()/2 + 
//			(float)(Math.cos(faceDir)*0.45f) + (float)(Math.cos(faceDir - Math.PI/2)*0.2f), 
//			this.getPosition().getY() + getHitBox().getHeight()/2 - 
//			(float)(Math.sin(faceDir)*0.45f) - (float)(Math.sin(faceDir - Math.PI/2)*0.2f));
	
	@Test
	public void getCenter(){
		Player p = new Player(1,1);
		
		float targetX = p.getPosition().getX() + p.getHitBox().getWidth()/2;
		float targetY = p.getPosition().getY() + p.getHitBox().getHeight()/2; 
		
		assertTrue(p.getCenter().getX() == targetX && p.getCenter().getY() == targetY);
		
	}
	
	@Test
	public void getMoveDir(){
		Player p = new Player(1,1);
		p.setMoveDir((float) Math.PI);
		assertTrue(p.getMoveDir() == (float)Math.PI);
		
		p.setMoveDir((float) Math.PI/77);
		assertTrue(p.getMoveDir() == (float)Math.PI/77);
	}
	
	@Test
	public void setMoveDir(){
		Player p = new Player(1,1);
		p.setMoveDir((float) Math.PI);
		assertTrue(p.getMoveDir() == (float)Math.PI);
		
		p.setMoveDir((float) Math.PI/77);
		assertTrue(p.getMoveDir() == (float)Math.PI/77);
	}
	
	@Test
	public void pickUpWeapon(){
		Player p = new Player(1,1);
		
		Weapon w1 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w2 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w3 = WeaponFactory.createEnemyMeleeWeapon();
		p.switchWeapon(0);
		p.pickUpWeapon(w1);
		
		assertTrue(p.getWeapons()[0] == w1);
		
		p.pickUpWeapon(w2);
		p.switchWeapon(1);
		p.pickUpWeapon(w3);
		
		assertTrue(p.getWeapons()[0] == w2);
		assertTrue(p.getWeapons()[1] == w3);
	}
	
	@Test
	public void getWeapons(){
		Player p = new Player(1,1);
		
		Weapon w1 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w2 = WeaponFactory.createPlayerDefaultWeapon();
		Weapon w3 = WeaponFactory.createEnemyMeleeWeapon();
		p.getWeapons()[0] = null;
		p.getWeapons()[1] = null;
		p.getWeapons()[2] = null;
		p.switchWeapon(0);
		p.pickUpWeapon(w1);
		p.switchWeapon(1);
		p.pickUpWeapon(w2);
		p.switchWeapon(2);
		p.pickUpWeapon(w3);
		
		assertTrue(p.getWeapons()[0] == w1);
		assertTrue(p.getWeapons()[1] == w2);
		assertTrue(p.getWeapons()[2] == w3);
	}

	
	@Test
	public void reload(){
		Player p = new Player(1,1);
		p.switchWeapon(0);
		p.pickUpWeapon(WeaponFactory.createTestWeapon(Weapon.Type.GUN));
		p.getActiveWeapon().createProjectile(0, new Position(1, 1));
		p.reloadActiveWeapon();
		assertTrue(p.getActiveWeapon().getAmmunitionInMagazine() == 
				p.getActiveWeapon().getMagazineCapacity());
	}
	
	@Test
	public void restore(){
		Player p = new Player(1,1);
		String[] s = p.getData();
		Player p2 = new Player(1,1);
		p2.restore(s);
		assertTrue(p.getHealth() == p2.getHealth());
		
		assertTrue(p.getCenter().getX() == p2.getCenter().getX());
		assertTrue(p.getCenter().getY() == p2.getCenter().getY());
		assertTrue(p.getAmmoAmount() == p2.getAmmoAmount());
		assertTrue(p.getFood() == p2.getFood());
		assertTrue(p.getIndex(p.getActiveWeapon()) == p2.getIndex(p2.getActiveWeapon()));
	}
	
	@Test
	public void getData(){
		Player p = new Player(1,1);
		
		String[] s = new String[] {
				p.getDirection() + "",
				p.getHealth() + "",
				p.getAmmoAmount() + "",
				p.getFood() + "",
				p.getCenter().getX() + "",
				p.getCenter().getY() + "",
				p.getIndex(p.getActiveWeapon()) + "",
		};
		for(int i = 0; i<s.length; i++){
			assertTrue(s[i].equals(p.getData()[i]));
		}
	}
	
	@Test
	public void pickUpItem(){
		Player p = new Player(1,1);
		
		int ammo = p.getAmmoAmount();
		p.pickUpItem(SupplyFactory.createAmmo(1, new Position(2,2)));
		assertTrue(p.getAmmoAmount() == ammo+1);
		
		p.reduceHealth(50);
		p.pickUpItem(SupplyFactory.createHealth(1, new Position(2,2)));
		assertTrue(p.getHealth() == 51);
		
		p.removeFood(50);
		p.pickUpItem(SupplyFactory.createFood(1, new Position(2,2)));
		assertTrue(p.getFood() == 51);
	}
	
	@Test
	public void removeFood(){
		Player p = new Player(1,1);
		p.removeFood(50);
		assertTrue(p.getFood() == 50);
		
		p.removeFood(100);
		assertTrue(p.getFood() == 0);
		
	}
	
	@Test
	public void getFood(){
		Player p = new Player(1,1);
		p.removeFood(50);
		assertTrue(p.getFood() == 50);
		
		p.addFood(60);
		assertTrue(p.getFood() == 100);
		
	}
	
	@Test
	public void addFood(){
		Player p = new Player(1,1);
		p.removeFood(50);
		p.addFood(10);
		assertTrue(p.getFood() == 60);
		
		p.addFood(50);
		assertTrue(p.getFood() == 100);//max food is 100
		
	}
	
	@Test
	public void increaseHealth(){
		Player p = new Player(1,1);
		p.increaseHealth(40);
		assertTrue(p.getHealth() == 100);
		
		p.reduceHealth(50);
		p.increaseHealth(40);
		assertTrue(p.getHealth() == 90);
		
	}
	
	@Test
	public void getActiveWeapon(){
		Player p = new Player(1,1);
		
		p.switchWeapon(0);
		p.pickUpItem(WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, WeaponFactory.Level.NORMAL));
		assertTrue(p.getActiveWeapon() != null);
	}
	
	@Test
	public void getDirection(){
		Player p = new Player(1,1);
		p.setDirection(5f);
		assertTrue(p.getDirection() == 5f);
		p.setDirection((float)Math.PI/2);
		assertTrue(p.getDirection() == (float)Math.PI/2);
	}
	
	@Test
	public void getCollisionBox(){
		//TODO more tests?
		Player p = new Player(1,1);
		assertTrue(p.getHitBox().getPosition().getX() == (p.getPosition()).getX());
		assertTrue(p.getHitBox().getPosition().getY() == (p.getPosition()).getY());
	}
	
	@Test
	public void getPosition(){
		Player p = new Player(1,1);
		p.setPosition(new Position(1.5f, 2f));
		assertTrue(p.getPosition().getX() == 1.5f && p.getPosition().getY() == 2f);
	}
	
	@Test
	public void setState(){
		Player p = new Player(1,1);
		p.setState(State.RUNNING);
		p.moveXAxis();
		p.moveYAxis();
		assertTrue(p.getPosition().getX() != 1 || p.getPosition().getY() != 1);
	}
	
	@Test
	public void setDirection(){
		Player p = new Player(1,1);
		p.setDirection(1);
		assertTrue(p.getDirection() == 1);
	}

//	@Test
//	public void setX(){
//		Player p = new Player(1,1);
//		p.setX(4);
//		System.out.println("" + p.getX());
//		assertTrue(p.getX() == 4);
//	}
	
//	@Test
//	public void setY(){
//		Player p = new Player(1,1);
//		p.setY(6);
//		assertTrue(p.getY() == 6);
//	}
	
//	@Test
//	public void getX(){
//		Player p = new Player(1,1);
//		assertTrue(p.getX() == 1);
//	}
	
//	@Test
//	public void getY(){
//		Player p = new Player(1,1);
//		assertTrue(p.getY() == 1);
//	}
//	
	@Test
	public void setWeapon(){
		Player p = new Player(1,1);
		p.switchWeapon(0);
		p.pickUpWeapon(WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, WeaponFactory.Level.NORMAL));
		assertTrue(p.getActiveWeapon() != null);
	}
	
	@Test
	public void getHealth(){
		Player p = new Player(1,1);
		assertTrue(p.getHealth() == 100);
	}
	
	@Test
	public void setPosition(){
		Player p = new Player(1,1);
		p.setPosition(new Position(2,5));
		assertTrue(p.getPosition().getX() == 2 && p.getPosition().getY() == 5);
	}
	
	@Test
	public void reduceHealth(){
		Player p = new Player(1,1);
		p.reduceHealth(40);
		assertTrue(p.getHealth() == 60);
	}
	
	@Test
	public void getAmmoAmount(){
		Player p = new Player(1,1);
		int ammo = p.getAmmoAmount();
		p.increaseAmmo(12);
		assertTrue(p.getAmmoAmount() == ammo + 12);
	}
	
	@Test
	public void reduceAmmo(){
		Player p = new Player(1,1);
		int ammo = p.getAmmoAmount();
		p.increaseAmmo(18);
		p.reduceAmmo(7);
		assertTrue(p.getAmmoAmount() == ammo + 11);
		
		p.reduceAmmo(100000);
		assertTrue(p.getAmmoAmount() == 0);
	}
	
	@Test
	public void increaseAmmo(){
		Player p = new Player(1,1);
		int ammo = p.getAmmoAmount();
		p.increaseAmmo(14);
		assertTrue(p.getAmmoAmount() == ammo + 14);
	}
}
