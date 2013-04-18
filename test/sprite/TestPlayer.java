package sprite;

import static org.junit.Assert.*;

import model.sprites.Player;

import org.junit.Test;

public class TestPlayer {

	@Test
	public void move(){
		Player p = new Player(1,1);
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
		System.out.println(p.getX() + " " + p.getY());
		assertTrue(p.getX() == p.getY());
	}

}
