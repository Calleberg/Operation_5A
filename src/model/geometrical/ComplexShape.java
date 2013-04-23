package model.geometrical;

import java.util.ArrayList;
import java.util.List;

/**
 * A complex shape is a composite shape made up of other simpler shapes.
 * You can add more shapes to this and make it more complex. However, as
 * for now you cannot remove a shape and make it less complex.
 * 
 * @author Calleberg
 *
 */
public class ComplexShape implements CollisionBox {

	private List<Line> lines;
	private Position position;
	
	/**
	 * Creates a new complex shape with the specified base to build from.
	 * @param base the base shape of this new complex shape.
	 */
	public ComplexShape(CollisionBox base) {
		this.position = new Position(base.getPosition().getX(), base.getPosition().getY());
		lines = new ArrayList<Line>();
		this.addShape(base);
	}
	
	/**
	 * Adds the specified shape to this.
	 * NOTE: Take care when adding new shapes as they are locked in place
	 * once they are added, e.g. they cannot be moved relative to the base
	 * after being added.
	 * @param box the shape to add.
	 */
	public void addShape(CollisionBox box) {
		for(Line l : box.getPolygonSegments()) {
			l.setRelativeTo(position);
			l.setPosition(new Position(l.getPosition().getX() + box.getPosition().getX() - this.getPosition().getX(),
					l.getPosition().getY() + box.getPosition().getX() - this.getPosition().getY()));
			lines.add(l);
		}
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
		return this.position;
	}

	@Override
	public void setPosition(Position pos) {
		this.position.setX(pos.getX());
		this.position.setY(pos.getY());
	}

	@Override
	public boolean intersects(CollisionBox box) {
		for(Line line : box.getPolygonSegments()) {
			if(line != null && line.intersects(this)) {
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
		return this.lines.toArray(new Line[0]);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[x=" + position.getX() + ",y=" + position.getY() + 
				",polygonSegements=" + getPolygonSegments().length + "]";
	}
	
}
