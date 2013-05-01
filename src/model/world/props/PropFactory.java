package model.world.props;

import view.Animation;
import model.geometrical.Position;
import model.geometrical.Rectangle;

/**
 * A factory for props.
 * 
 * @author Calleberg
 *
 */
public class PropFactory {

	/*
	 * Global animation of water, should be synchronised with all the water in the world.
	 */
	private static final Animation WATER = new Animation(new int[]{30,31,32,33,34,35,36,37,38,39}, 200, true);
	
	private static final Animation TRAFFIC_LIGHT_S = new Animation(new int[]{40,40,40,41,42,42,42,41}, 2500, true);
	
	/**
	 * Creates a new prop with the specified position.
	 * @param pos the position of the new prop.
	 * @param propNbr the number of the prop to create.
	 * @return <code>null</code> if the propNbr was invalid.
	 */
	public static Prop getProp(Position pos, int propNbr) {
		switch(propNbr) {
		case 0:		return new Prop(new Rectangle(pos.getX(), pos.getY(), 1f, 1f), propNbr); //test1
		case 1:		return new Prop(new Rectangle(pos.getX(), pos.getY(), 0.5f, 0.5f), propNbr); //test2
		case 2:		return new Prop(new Rectangle(pos.getX() + 0.5f, pos.getY(), 0.5f, 1f), propNbr); //test3
		case 10: case 11: case 12: case 13: 
		case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27:
			return new Prop(null, propNbr); //water edges
		case 30: case 31: case 32: case 33: case 34: 	
			return new AnimatedProp(null, WATER); //animated water
		case 40: case 41: case 42:
			return new AnimatedProp(new Rectangle(pos.getX() + 0.25f, pos.getY() + 0.2f, 0.5f, 0.375f), 
					TRAFFIC_LIGHT_S); //animated traffic light
		default:	return null;
		}
	}
}
