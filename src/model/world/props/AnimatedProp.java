package model.world.props;

import view.Animation;
import model.geometrical.CollisionBox;

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
