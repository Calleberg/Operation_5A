package model.geometrical;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple model of a line.
 * 
 * @author Martin Calleberg
 *
 */
public class Line implements CollisionBox {

	private Line2D line;
	//Precompiled list
	private List<Line2D> lines;
	private Position oldPosition;
	
	/**
	 * Creates a new line between the two positions.
	 * @param x1 the first X coordinate.
	 * @param y1 the first Y coordinate.
	 * @param x2 the second X coordinate.
	 * @param y2 the second Y coordinate.
	 */
	public Line(float x1, float y1, float x2, float y2) {
		this.line = new Line2D.Float(x1, y1, x2, y2);
		this.lines = new ArrayList<Line2D>();
		lines.add(line);
	}
	
	@Override
	public float getWidth() {
		return (float)Math.max(line.getX1(), line.getX2()) - this.getPosition().getX();
	}

	@Override
	public float getHeight() {
		return (float)Math.max(line.getY1(), line.getY2()) - this.getPosition().getY();
	}

	@Override
	public Position getPosition() {
		return new Position((float)Math.min(line.getX1(), line.getX2()), (float)Math.min(line.getY1(), line.getY2()));
	}

	@Override
	public void setPosition(Position pos) {
		Position oldPos = this.getPosition();
		this.move(pos.getX() - oldPos.getX(), pos.getY() - oldPos.getY());
	}

	@Override
	public void move(float dx, float dy) {
		this.oldPosition = this.getPosition();
		this.line.setLine(line.getX1() + dx, line.getY1() + dy, 
				line.getX2() + dx, line.getY2() + dy);
	}

	@Override
	public boolean intersects(CollisionBox box) {
		if(box != null) {
			for(Line2D l : box.getLines()) {
				if(l.intersectsLine(line)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Line2D> getLines() {
		return this.lines;
	}

	@Override
	public boolean moveBack() {
		if(oldPosition != null) {
			this.setPosition(oldPosition);
			oldPosition = null;
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[x1=" + this.line.getX1() + ",y1=" + this.line.getY1() +
				",x2=" + this.line.getX2() + ",y2=" + this.line.getY2() + "]";
	}

}
