package model.sprites;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.sprites.Player;
import model.sprites.Sprite.State;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void move(){
		Player p = new Player(1,1);
//		p.setState(State.FORWARD);
		
		p.setDirection(0f);
		p.move();
		assertTrue(p.getX() == 1.2f && p.getY() == 1f);
		
		p.setDirection((float)Math.PI);
		p.move();
		assertTrue(p.getX() == 1f && p.getY() == 1f);
		
		p.setDirection((float)Math.PI/2);
		p.move();
		assertTrue(p.getX() == 1f && p.getY() == 0.8f);
		
		p.setDirection((float)Math.PI*3/2);
		p.move();
		assertTrue(p.getX() == 1f && p.getY() == 1f);
		
		p.setDirection((float)Math.PI*-1/4);
		p.move();
		assertTrue(p.getX() == p.getY());
	}
	
	@Test
	public void increaseHealth(){
		//TODO
		//need get health or something similar
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
		Player p = new Player(1,1);
		p.setPosition(new Position(1.5f, 2f));
		assertTrue(p.getPosition().getX() == 1.5f && p.getPosition().getY() == 2f);
	}

}
