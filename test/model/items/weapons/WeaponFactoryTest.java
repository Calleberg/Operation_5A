package model.items.weapons;

import org.junit.Test;
import static org.junit.Assert.*;

public class WeaponFactoryTest {

	@Test
	public void TypeTest(){
		int total=0;
		for (WeaponFactory.Type.values())
		
		assertTrue(total==100);
	}
}
