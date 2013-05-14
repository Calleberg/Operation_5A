package model.geometrical;

import java.awt.geom.Line2D;

/**
 * Used by polygons as the segments.
 * 
 * @author
 *
 */
public class PolygonSegement extends Line2D.Float {

	private static final long serialVersionUID = 1L;
	private Position pos;

	/**
	 * Creates a new segment between the two specified points. 
	 * @param pos the position to move relative to. This should be a positioned set by the 
	 * polygon itself.
	 * @param x1 the first X coordinate.
	 * @param y1 the first Y coordinate.
	 * @param x2 the second X coordinate.
	 * @param y2 the second Y coordinate.
	 */
	protected PolygonSegement(Position pos, float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
		this.pos = pos;
	}
	
	@Override
	public double getX1() {
		return super.getX1() + pos.getX();
	}
	
	@Override
	public double getY1() {
		return super.getY1() + pos.getY();
	}
	
	@Override
	public double getX2() {
		return super.getX2() + pos.getX();
	}
	
	@Override
	public double getY2() {
		return super.getY2() + pos.getY();
	}
}
