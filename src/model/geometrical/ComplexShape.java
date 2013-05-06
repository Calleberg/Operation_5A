package model.geometrical;

import java.awt.geom.Line2D;
import java.util.List;

/**
 * A <code>CollisionBox</code> made up out of other combined <code>CollisionBox</code>:es.
 * 
 * @author 
 *
 */
public class ComplexShape implements CollisionBox  {

	private Polygon p;
	private int shapesAdded;
	
	/**
	 * Creates a new default complex shape with the specified shape as base.
	 * @param base the base of the complex shape.
	 */
	public ComplexShape(CollisionBox base) {
		this.p = new Polygon();
		this.addShape(base);
	}
	
	/**
	 * Creates a new default complex shape.
	 */
	public ComplexShape() {
		this(null);
	}
	
	/**
	 * Adds the specified collision box to this one.
	 * @param box the one to add.
	 */
	public void addShape(CollisionBox box) {
		if(box != null) {
			shapesAdded++;
			for(Line2D l : box.getLines()) {
				this.p.addLine(l);
			}
		}
	}
	
	@Override
	public float getWidth() {
		return this.p.getWidth();
	}

	@Override
	public float getHeight() {
		return this.p.getHeight();
	}

	@Override
	public Position getPosition() {
		return this.p.getPosition();
	}

	@Override
	public void setPosition(Position pos) {
		this.p.setPosition(pos);
	}

	@Override
	public void move(float dx, float dy) {
		this.p.move(dx, dy);
	}

	@Override
	public boolean intersects(CollisionBox box) {
		return this.p.intersects(box);
	}

	@Override
	public List<Line2D> getLines() {
		return this.p.getLines();
	}

	@Override
	public boolean moveBack() {
		return this.p.moveBack();
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[x=" + this.getPosition().getX() + ",y=" + this.getPosition().getY() +
				",width=" + this.getWidth() + ",height=" + this.getHeight() + ",shapes=" + this.shapesAdded + "]";
	}

}
