package model.sprites;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.sprites.Sprite.State;

import org.junit.Test;

public class EnemyTest {

	@Test
	public void move(){
		Enemy e = new Enemy(new Position(1f,1f), 0.2f, null, 1);
		//TODO e.setState(State.FORWARD);
		
		e.setDirection(0f);
		e.move();
		System.out.println(e.getX() + " " + e.getY());
		System.out.println(e.getDirection());
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
	public void increaseHealth(){
		//TODO
		//need get health or similar
	}
	
	@Test
	public void getActiveWeapon(){
		//TODO
		//need set weapon or similar
	}
	
	@Test
	public void spriteHitbyProjectile(){
		//TODO
		//need get health or similar
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
	public void getImage(){
		//TODO
	}
	
	@Test
	public void getPosition(){
		Enemy e = new Enemy(new Position(1f,1f), 1f, null, 1);
		e.setPosition(new Position(1.5f, 2f));
		assertTrue(e.getPosition().getX() == 1.5f && e.getPosition().getY() == 2f);
	}

}
