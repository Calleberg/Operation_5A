package model.sprites;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponFactory;
import model.sprites.Sprite.State;

import org.junit.Test;

public class EnemyTest {

	@Test
	public void move(){//TODO Error
		Enemy e = new Enemy(new Position(1f,1f), 0.2f, null, 1);
		//TODO Doesn't work because of setDirectionTowardsList()
		//TODO: setState when implemented in Enemy
		
		e.setDirection(0f);
		e.move();
		assertTrue(e.getX() == 1.2f && e.getY() == 1f);
		
		e.setDirection((float)Math.PI);
		e.move();
		assertTrue(e.getX() == 1f && e.getY() == 1f);
		
		e.setDirection((float)Math.PI/2);
		e.move();
		assertTrue(e.getX() == 1f && e.getY() == 0.8f);
		
		e.setDirection((float)Math.PI*3/2);
		e.move();
		assertTrue(e.getX() == 1f && e.getY() == 1f);
		
		e.setDirection((float)Math.PI*-1/4);
		e.move();
		System.out.println(e.getX() + " " + e.getY());
		assertTrue(e.getX() == e.getY());
	}

	@Test
	public void getActiveWeapon(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, WeaponFactory.createWeapon
			(WeaponFactory.Type.FISTS, WeaponFactory.Level.RUSTY), 50);
		Weapon w = e.getActiveWeapon();
		
		assertTrue(w.toString().equals("Rusty Fists"));
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
		assertTrue(p.getCollisionBox().getPosition().getX() == (p.getPosition()).getX());
		assertTrue(p.getCollisionBox().getPosition().getY() == (p.getPosition()).getY());
	}
	
	@Test
	public void getPosition(){
		Enemy e = new Enemy(new Position(1f,1f), 1f, null, 1);
		e.setPosition(new Position(1.5f, 2f));
		assertTrue(e.getPosition().getX() == 1.5f && e.getPosition().getY() == 2f);
	}
	
	@Test
	public void setPosition(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		e.setPosition(new Position(2,3));
		assertTrue(e.getPosition().getX() == 2 && e.getPosition().getY() == 3);
	}
	
	@Test
	public void getHealth(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		assertTrue(e.getHealth() == 50);
	}
	
	@Test
	public void setDirection(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		e.setDirection(1);
		assertTrue(e.getDirection() == 1);
	}
	
	@Test
	public void setX(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		e.setX(4);
		assertTrue(e.getX() == 4);
	}
	
	@Test
	public void setY(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		e.setY(5);
		assertTrue(e.getY() == 5);
	}
	@Test
	public void getX(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		assertTrue(e.getX() == 1);
	}
	
	@Test
	public void getY(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		assertTrue(e.getY() == 1);
	}
	
	@Test 
	public void ReduceHealth(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		e.reduceHealth(10);
		assertTrue(e.getHealth() == 40);
	}
	
	@Test
	public void getProjectileSpawn(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		
		assertTrue(e.getProjectileSpawn().getX() == e.getCollisionBox().getPosition().getX());
		assertTrue(e.getProjectileSpawn().getY() == e.getCollisionBox().getPosition().getY());
	}
	
	@Test
	public void getCenter(){
		Enemy e = new Enemy(new Position(1,1), 0.2f, null, 50);
		
		float targetX = e.getX() + e.getCollisionBox().getWidth()/2;
		float targetY = e.getY() + e.getCollisionBox().getHeight()/2; 
		
		assertTrue(e.getCenter().getX() == targetX && e.getCenter().getY() == targetY);
		
	}
	
	@Test
	public void setDirectionTowardsList(){
		//TODO, wait until complete in Enemy
	}
}
