package model.geometrical;

import java.awt.geom.Line2D;

/**
 * A basic model of a triangle.
 * 
 * @author
 *
 */
public class Triangle extends Polygon {

	/**
	 * Creates a new triangle.
	 * @param points a array of length 3 with the 
	 */
	public Triangle(Position[] points) {
		super();
		addLine(new Line2D.Float(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY()));
		addLine(new Line2D.Float(points[1].getX(), points[1].getY(), points[2].getX(), points[2].getY()));
		addLine(new Line2D.Float(points[2].getX(), points[2].getY(), points[0].getX(), points[0].getY()));
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[x=" + this.getPosition().getX() + ",y=" + this.getPosition().getY() +
				",width=" + this.getWidth() + ",height=" + this.getHeight() + "]";
	}
}
