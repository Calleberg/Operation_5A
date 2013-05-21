package model.items.weapons;

import static org.junit.Assert.*;

import model.geometrical.Position;

import org.junit.Test;

public class ProjectileTest {

	Projectile p = new Projectile(1, 0.2f, 10f, 0f, new Position(1,1),true);
	Projectile p2 = new Projectile(1,2,3,4, new Position(5,6),true);
	String[] data = p2.getData();
	
	
	@Test
	public void move(){
		p.move();
		assertTrue(p.getPosition().getX() == 1.2f && p.getPosition().getY() == 1f);
	}
	
	@Test
	public void setPosition(){
		p.setPosition(new Position(2,2));
		assertTrue(p.getPosition().getX() == 2 && p.getPosition().getY() == 2);
	}
	
	@Test
	public void getDamage(){
		assertTrue(p.getDamage() == 1);
	}
	
	@Test
	public void getSpeed(){
		assertTrue(p.getSpeed() == 0.2f);
	}

	@Test
	public void getRange(){
		assertTrue(p.getRange() == 10f);
	}
	
	@Test
	public void getDirection(){
		assertTrue(p.getDirection() == 0f);
	}
	
	@Test
	public void getPosition(){
		p.setPosition(new Position(2,2));
		assertTrue(p.getPosition().getX() == 2 && p.getPosition().getY() == 2);
	}
	
	@Test
	public void getCollisionBox(){
		p.setPosition(new Position(2,2));
		assertTrue(p.getPosition().getX() == 2 && p.getPosition().getY() == 2
			&& p.getCollisionBox().getHeight() == 0.1f &&
				p.getCollisionBox().getWidth() == 0.1f);
	}
	
	@Test
	public void isMaxRangeReahced(){
		for(int i = 0; i <= 100000; i++){
			p.move();
		}
		assertTrue(p.isMaxRangeReached());
	}
	
	@Test
	public void testRestoreSave() {
		Projectile p3 = new Projectile(1, 0.2f, 10f, 0f, new Position(1,1),true);
		String data1[] = p3.getData();
		
		Projectile p4 = new Projectile(2, 0.1f, 70f, 12f, new Position(3,6),true);
		p4.restore(data1);
		String data2[] = p4.getData();

		for(int i = 0; i < data1.length; i++) {
			assertTrue(data1[i].equals(data2[i]));
		}
	}
}
