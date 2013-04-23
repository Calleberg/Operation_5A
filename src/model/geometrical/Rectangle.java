package model.geometrical;

import java.awt.Dimension;

/**
 * A rectangle that can be used as a collision box and which supports collision detection.
 * 
 * @author Calleberg
 *
 */
public class Rectangle implements CollisionBox {

	private Line[] lines;
	private float w, h;
	private Position position;
	
	/**
	 * Creates a new rectangle at the specified position and with the specified size.
	 * @param pos the position of the rectangle.
	 * @param size the size of the rectangle.
	 */
	public Rectangle(Position pos, Dimension size) {
		this(pos.getX(), pos.getY(), (float)size.getWidth(), (float)size.getHeight());
	}
	
	/**
	 * Creates a new rectangle at the specified position and with the specified size.
	 * @param x the x-coordinate of the upper left corner.
	 * @param y the y-coordinate of the upper left corner.
	 * @param w the width of the rectangle.
	 * @param h the height of the rectangle.
	 */
	public Rectangle(float x, float y, float w, float h) {
		this.w = w;
		this.h = h;
		this.position = new Position(x, y);

		this.lines = new Line[4];
		this.lines[0] = new Line(0, 0, w, 0, this.position);
		this.lines[1] = new Line(w, 0, w, h, this.position);
		this.lines[2] = new Line(w, h, 0, h, this.position);
		this.lines[3] = new Line(0, h, 0, 0, this.position);
	}
	
	@Override
	public void rotate(float direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getWidth() {
		return this.w;
	}

	@Override
	public float getHeight() {
		return this.h;
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Position pos) {
		this.position.setX(pos.getX());
		this.position.setY(pos.getY());
	}

	/**
	 * Gives <code>true</code> if the two collision boxes intersects.
	 * Note: if one smaller collision box is inside another then this
	 * method will return <code>false</code>, but if should detect intersection
	 * before that.
	 */
	@Override
	public boolean intersects(CollisionBox box) {
		for(Line line : box.getPolygonSegments()) {
			if(line.intersects(this)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean moveBack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Line[] getPolygonSegments() {
		return this.lines;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[x=" + position.getX() + ",y=" + position.getY() + "]";
	}

}
