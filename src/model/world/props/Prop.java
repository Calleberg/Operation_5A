package model.world.props;

import model.geometrical.CollisionBox;

/**
 * Models a simple prop which can populate a world.
 * It can obstruct or simple serve as an overlay.
 * 
 * @author
 *
 */
public class Prop {

	private CollisionBox box;
	private int image;
	
	/**
	 * Creates a new prop with the specified collision box and image.
	 * @param box the collision box to use.
	 * @param imageNbr the number of the image to use.
	 */
	protected Prop(CollisionBox box, int imageNbr) {
		this.box = box;
		this.setImageNbr(imageNbr);
	}
	
	/**
	 * Gives the collision box of this prop.
	 * @return the collision box of this prop.
	 */
	public CollisionBox getCollisionBox() {
		return this.box;
	}
	
	/**
	 * Sets the image of this prop.
	 * @param nbr the image of this prop.
	 */
	public void setImageNbr(int nbr) {
		this.image = nbr;
	}
	
	/**
	 * Gives the number of the image.
	 * @return the number of the image.
	 */
	public int getImageNbr() {
		return this.image;
	}
}
