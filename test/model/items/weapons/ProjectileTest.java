package model.items.weapons;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

public class ProjectileTest {

	@Test
	public void move(){
		Projectile p = new Projectile(1, 0.2f, 10f, 0f, new Position(1,1));
		p.move();
		assertTrue(p.getPosition().getX() == 1.2f && p.getPosition().getY() == 1f);
	}
	
	@Test
	public void setPosition(){
		Projectile p = new Projectile(1, 0.2f, 10f, 0f, new Position(1,1));
		p.setPosition(new Position(2,2));
		assertTrue(p.getPosition().getX() == 2 && p.getPosition().getY() == 2);
	}

}
