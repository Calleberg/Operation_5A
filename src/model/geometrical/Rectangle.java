package model.geometrical;

import java.awt.geom.Line2D;

/**
 * A basic model of a rectangle.
 * 
 * @author
 *
 */
public class Rectangle extends Polygon {
		
	/**
	 * Creates a new rectangle at the specified position and with the specified size.
	 * @param x the X coordinate.
	 * @param y the Y coordinate.
	 * @param w the width of the rectangle.
	 * @param h the height of the rectangle.
	 */
	public Rectangle(float x, float y, float w, float h) {
		super();
		addLine(new Line2D.Float(x, y, x + w, y));
		addLine(new Line2D.Float(x + w, y, x + w, y + h));
		addLine(new Line2D.Float(x + w, y + h, x, y + h));
		addLine(new Line2D.Float(x, y + h, x, y));
		
		addLine(new Line2D.Float(x, y, x + w, y + h));
		addLine(new Line2D.Float(x + w, y, x, y + h));
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[x=" + this.getPosition().getX() + ",y=" + this.getPosition().getY() +
				",width=" + this.getWidth() + ",height=" + this.getHeight() + "]";
	}

}
