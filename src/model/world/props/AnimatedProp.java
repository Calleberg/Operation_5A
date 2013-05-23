package model.world.props;

import model.geometrical.CollisionBox;
import model.other.Animation;

/**
 * A prop which can be animated.
 * 
 * @author Martin Calleberg
 *
 */
public class AnimatedProp extends Prop {

	private Animation animation;
	
	/**
	 * Creates a new animated prop with the specified collisionbox and animation.
	 * @param box the collisionbox to use.
	 * @param animation the animation of the prop.
	 */
	protected AnimatedProp(CollisionBox box, Animation animation) {
		super(box, 0);
		this.animation = animation;
	}

	@Override
	public int getImageNbr() {
		return animation.getFrame();
	}
}
