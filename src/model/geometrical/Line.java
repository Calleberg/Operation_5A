package model.geometrical;

import java.awt.geom.Line2D;

/**
 * A simple line which can be used as collision box and which supports collision detection.
 * Note that this line have a "relativeTo" position which is the position this line is 
 * positioned relative to. If the position is changed then the whole line is moved correctly.
 * 
 * @author Calleberg
 *
 */
public class Line extends Line2D.Float implements CollisionBox {

	private static final long serialVersionUID = 1L;
	private Position relativeTo;		//the position this line is positioned relative to.
										//this way the line can be moved simply by changing the
										//position its relative to.
	
	/**
	 * Creates a new line between the two positions specified.
	 * @param position1 the start of the line.
	 * @param position2 the end of the line.
	 */
	public Line(Position position1, Position position2, Position relativeTo) {
		this(position1.getX(), position1.getY(),
				position2.getX(), position2.getY(), relativeTo);
	}
	
	/**
	 * Creates a new line between the two positions specified.
	 * @param x1 the start's x-coordinate.
	 * @param y1 the start's y-coordinate.
	 * @param x2 the end's x-coordinate.
	 * @param y2 the end's y-coordinate.
	 */
	public Line(float x1, float y1, float x2, float y2, Position relativeTo) {
		super(x1, y1, x2, y2);
		this.relativeTo = relativeTo;
	}
	
	/**
	 * Sets the position of which this line is positioned relative to.
	 * @param pos the position of which this line is positioned relative to.
	 */
	public void setRelativeTo(Position pos) {
		this.relativeTo = pos;
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
		float dx =  pos.getX() - this.getPosition().getX();
		float dy =  pos.getY() - this.getPosition().getY();
		this.x1 += dx;
		this.y1 += dy;
		this.x2 += dx;
		this.y2 += dy;
	}
	
	@Override
	public double getX1() {
		return super.getX1() + this.relativeTo.getX();
	}
	
	@Override
	public double getY1() {
		return super.getY1() + this.relativeTo.getY();
	}
	
	@Override
	public double getX2() {
		return super.getX2() + this.relativeTo.getX();
	}
	
	@Override
	public double getY2() {
		return super.getY2() + this.relativeTo.getY();
	}

	@Override
	public boolean intersects(CollisionBox box) {
		for(Line line : box.getPolygonSegments()) {
			if(line != null && this.intersectsLine(line)) {
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
		return new Line[]{this};
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[x=" + getPosition().getX() + ",y=" + getPosition().getY() + "]";
	}

}
