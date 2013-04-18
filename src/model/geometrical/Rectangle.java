package model.geometrical;

import java.awt.Dimension;

public class Rectangle implements CollisionBox {

	private Line[] lines;
	
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
		this.lines = new Line[4];
		this.lines[0] = new Line(x, y, x, y + h);
		this.lines[1] = new Line(x, y + h, x + w, y + h);
		this.lines[2] = new Line(x + w, y + h, x + w, y);
		this.lines[3] = new Line(x + w, y, x, y);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean intersects(CollisionBox box) {
		for(Line l1 : box.getPolygon()) {
			for(Line l2 : box.getPolygon()) {
				if(l1.intersectsLine(l2)) {
					return true;
				}
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
	public Line[] getPolygon() {
		// TODO Auto-generated method stub
		return null;
	}

}
