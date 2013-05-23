package model.geometrical;

import java.awt.geom.Line2D;

/**
 * A basic model of a circle.
 * 
 * @author Martin Calleberg
 *
 */
public class Circle extends Polygon {

	/**
	 * Creates a new circle with the specified diameter and with the specified amount of segments.
	 * @param x X coordinate of the center.
	 * @param y Y coordinate of the center.
	 * @param diameter the diameter of the circle.
	 * @param definition the amount of segments to use when generating the circle.
	 */
	public Circle(float x, float y, float diameter, int definition) {
		super();
		float x1, y1, x2 = x + diameter/2, y2 = y;
		for(int i = 0; i <= 360; i += 360/definition) {
			float a = (float) (i * (Math.PI/180));
			x1 = x2;
			y1 = y2;
			x2 = (float) (x + (diameter/2) * Math.cos(a));
			y2 = (float) (y + (diameter/2) * Math.sin(a));
			addLine(new Line2D.Float(x1, y1, x2, y2));
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[x=" + this.getPosition().getX() + ",y=" + this.getPosition().getY() +
				",diameter=" + this.getWidth() + ",definition=" + this.getLines().size() + "]";
	}

}
