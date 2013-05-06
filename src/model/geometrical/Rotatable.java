package model.geometrical;

import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 * An abstract collision box which can rotate.
 * 
 * @author
 *
 */
public abstract class Rotatable extends Polygon {

	private double angle;
	
	/**
	 * Creates a new rotatable collision box.
	 */
	public Rotatable() {
		super();
	}
	
	/**
	 * Rotates the shape the specified amount of radians around the shape's center.
	 * @param radians the radians to rotate.
	 */
	public void rotate(double radians) {
		this.rotate(radians, this.getPosition().getX() + this.getWidth()/2, 
				this.getPosition().getY() + this.getHeight()/2);
	}
	
	/**
	 * Rotates the shape the specified amount.
	 * @param radians the amount to rotate in radians.
	 * @param anchorX the X coordinate to rotate around.
	 * @param anchorY the Y coordinate to rotate around.
	 */
	public void rotate(double radians, float anchorX, float anchorY) {
		this.angle += radians;
		double[] pt;
		for(Line2D l : this.getLines()) {
			pt = new double[]{l.getX1(), l.getY1(), l.getX2(), l.getY2()};
			AffineTransform.getRotateInstance(radians, anchorX, anchorY)
			.transform(pt, 0, pt, 0, 2);
			
			l.setLine(pt[0], pt[1], pt[2], pt[3]);
		}
	}
	
	/**
	 * Sets the direction to rotate to.
	 * @param radians the angle in radians to rotate to.
	 */
	public void setDirection(double radians) {
		this.rotate(radians - this.angle);
	}
}
