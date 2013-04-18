package model.geometrical;

import java.awt.geom.Line2D;

public class Line extends Line2D.Float implements CollisionBox {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new line between the two positions specified.
	 * @param position1 the start of the line.
	 * @param position2 the end of the line.
	 */
	public Line(Position position1, Position position2) {
		this(position1.getX(), position1.getY(),
				position2.getX(), position2.getY());
	}
	
	/**
	 * Creates a new line between the two positions specified.
	 * @param x1 the start's x-coordinate.
	 * @param y1 the start's y-coordinate.
	 * @param x2 the end's x-coordinate.
	 * @param y2 the end's y-coordinate.
	 */
	public Line(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2);
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
		return new Position((float)this.getX1(), (float)this.getY1());
	}

	@Override
	public void setPosition(Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean intersects(CollisionBox box) {
		for(Line l1 : box.getPolygon()) {
			if(this.intersectsLine(l1)) {
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
	public Line[] getPolygon() {
		return new Line[]{this};
	}

}
