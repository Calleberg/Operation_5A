package model.world.props;

import model.geometrical.CollisionBox;
import model.other.Animation;

public class AnimatedProp extends Prop {

	private Animation animation;
	
	protected AnimatedProp(CollisionBox box, Animation animation) {
		super(box, 0);
		this.animation = animation;
	}

	@Override
	public int getImageNbr() {
		return animation.getFrame();
	}
}
