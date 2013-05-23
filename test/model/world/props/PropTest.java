package model.world.props;

import static org.junit.Assert.*;

import model.geometrical.Position;
import model.world.props.Prop;
import model.world.props.PropFactory;

import org.junit.Test;

/**
 * 
 * @author Martin Calleberg
 *
 */
public class PropTest {

	@Test
	public void setGetImageTest() {
		Prop p = PropFactory.getProp(new Position(0, 0), 1);
		assertTrue(p.getImageNbr() == 1);
		
		p.setImageNbr(2);
		assertTrue(p.getImageNbr() == 2);
	}
	
	@Test
	public void collisionBox() {
		Prop p = PropFactory.getProp(new Position(0, 0), 1);
		assertTrue(p.getCollisionBox() != null);
	}

}
