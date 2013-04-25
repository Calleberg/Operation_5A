package model.geometrical;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A complex shape is a composite shape made up of other implementations
 * of <code>CollisionBox</code>.
 * 
 * @author Calleberg
 *
 */
public class ComplexShape implements CollisionBox {

	private List<java.awt.geom.Rectangle2D> rects;
	private Position oldPosition;
	
	/**
	 * Creates a new complex shape with the specified base to build from.
	 * @param base the base shape of this new complex shape.
	 */
	public ComplexShape(Rectangle base) {
		this();
		this.addShape(base);
	}
	
	/**
	 * Creates a new default complex shape.
	 */
	public ComplexShape() {
		rects = new ArrayList<java.awt.geom.Rectangle2D>();
	}
	
	/**
	 * Adds the specified shape to this.
	 * NOTE: Take care when adding new shapes as they are locked in place
	 * once they are added, e.g. they cannot be moved relative to the base
	 * after being added.
	 * @param box the shape to add.
	 */
	public void addShape(Rectangle box) {
		for(java.awt.geom.Rectangle2D r : box.getRectangles()) {
			this.rects.add(r);
		}
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
		double minX = rects.get(0).getX();
		double minY = rects.get(0).getY();
		for(Rectangle2D r : this.rects) {
			if(r.getX() < minX) {
				minX = r.getX();
			}
			if(r.getY() < minY) {
				minY = r.getY();
			}
		}
		return new Position((float)minX, (float)minY);
	}

	@Override
	public void setPosition(Position pos) {
		oldPosition = this.getPosition();
		float dx = pos.getX() - oldPosition.getX();
		float dy = pos.getY() - oldPosition.getY();
		for(Rectangle2D r : this.rects) {
			r.setRect(r.getX() + dx, r.getY() + dy, r.getWidth(), r.getHeight());
		}
	}

	@Override
	public boolean intersects(CollisionBox box) {
		for(Rectangle2D r : this.rects) {
			for(Rectangle2D r2 : box.getRectangles()) {
				if(r.intersects(r2)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Can only move back once after each move.
	 */
	@Override
	public boolean moveBack() {
		Position p = this.getPosition();
		if(oldPosition.getX() == p.getX() && oldPosition.getY() == p.getY()) {
			return false;
		}else{
			this.setPosition(oldPosition);
			return true;
		}
	}
	
	@Override
	public Rectangle2D[] getRectangles() {
		return this.rects.toArray(new Rectangle2D[0]);
	}

	@Override
	public String toString() {
		Position p = this.getPosition();
		return getClass().getName() + "[x=" + p.getX() + ",y=" + p.getY() + ",shapes=" + this.rects.size() + "]";
	}

	
	
}
