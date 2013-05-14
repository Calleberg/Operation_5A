package model.sprites;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.items.weapons.WeaponFactory;
import model.sprites.Player;
import model.sprites.Sprite.State;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void move(){
		Player p = new Player(1,1);
		p.setState(State.RUNNING);
		
//		p.setMoveDir(0f);
//		p.move();
//		assertTrue(p.getX() == 1.2f && p.getY() == 1f);
//		
//		p.setMoveDir((float)Math.PI);
//		p.move();
//		assertTrue(p.getX() == 1f && p.getY() == 1f);
//		
//		p.setMoveDir((float)Math.PI/2);
//		p.move();
//		assertTrue(p.getX() == 1f && p.getY() == 0.8f);
//		
//		p.setMoveDir((float)Math.PI*3/2);
//		p.move();
//		assertTrue(p.getX() == 1f && p.getY() == 1f);
//		
//		p.setMoveDir((float)Math.PI*-1/4);
//		p.move();
//		assertTrue(p.getX() == p.getY());
	}
	
	@Test
	public void increaseHealth(){
		Player p = new Player(1,1);
		p.increaseHealth(40);
		assertTrue(p.getHealth() == 140);
		
	}
	
	@Test
	public void getActiveWeapon(){
		Player p = new Player(1,1);
		p.addWeapon(WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, WeaponFactory.Level.NORMAL));
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
		assertTrue(p.getX() != 1 || p.getY() != 1);
	}
	
	@Test
	public void setDirection(){
		Player p = new Player(1,1);
		p.setDirection(1);
		assertTrue(p.getDirection() == 1);
	}

	@Test
	public void setX(){
		Player p = new Player(1,1);
		p.setX(4);
		System.out.println("" + p.getX());
		assertTrue(p.getX() == 4);
	}
	
	@Test
	public void setY(){
		Player p = new Player(1,1);
		p.setY(6);
		assertTrue(p.getY() == 6);
	}
	
	@Test
	public void getX(){
		Player p = new Player(1,1);
		assertTrue(p.getX() == 1);
	}
	
	@Test
	public void getY(){
		Player p = new Player(1,1);
		assertTrue(p.getY() == 1);
	}
	
	@Test
	public void setWeapon(){
		Player p = new Player(1,1);
		p.addWeapon(WeaponFactory.createWeapon(WeaponFactory.Type.PISTOL, WeaponFactory.Level.NORMAL));
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
		assertTrue(p.getX() == 2 && p.getY() == 5);
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
		p.increaseAmmo(12);
		assertTrue(p.getAmmoAmount() == 12);
	}
	
	@Test
	public void reduceAmmo(){
		Player p = new Player(1,1);
		p.increaseAmmo(18);
		p.reduceAmmo(7);
		assertTrue(p.getAmmoAmount() == 11);
	}
	
	@Test
	public void increaseAmmo(){
		Player p = new Player(1,1);
		p.increaseAmmo(14);
		assertTrue(p.getAmmoAmount() == 14);
	}
}
