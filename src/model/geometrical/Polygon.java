package model.geometrical;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A basic polygon. 
 * Note: This will not autocomplete itself even if it is not a closed circuit.
 * 
 * @author
 *
 */
public class Polygon implements CollisionBox {

	private List<Line2D> polygon;
	private float x, y;
	private Position oldPosition;
	private Position position;	//the position this polygon moves relative to.
	
	/**
	 * Creates a new polygon with the specified position as anchor.
	 */
	public Polygon() {
		this.polygon = new ArrayList<Line2D>();
		this.oldPosition = new Position(0, 0);
		this.position = new Position(0, 0);
	}
	
	/**
	 * Adds the specified line to the polygon.
	 * @param line the line to add.
	 */
	public void addLine(Line2D line) {
		this.polygon.add(new PolygonSegement(position, (float)line.getX1(), (float)line.getY1(), 
				(float)line.getX2(), (float)line.getY2()));
	}
	
	@Override
	public float getWidth() {
		float max = 0;
		for(Line2D l : this.getLines()) {
			if(l.getX1() > max) {
				max = (float)l.getX1();
			}
			if(l.getX2() > max) {
				max = (float)l.getX2();
			}
		}
		return max - this.getPosition().getX();
	}
	
	@Override
	public float getHeight() {
		float max = 0;
		for(Line2D l : this.getLines()) {
			if(l.getY1() > max) {
				max = (float)l.getY1();
			}
			if(l.getY2() > max) {
				max = (float)l.getY2();
			}
		}
		return max - this.getPosition().getY();
	}
	
	@Override
	public List<Line2D> getLines() {
		return this.polygon;
	}

	@Override
	public Position getPosition() {
		return (Position) position.clone();
	}

	@Override
	public void setPosition(Position pos) {
		this.oldPosition = this.getPosition(); 
		this.position.setX(pos.getX());
		this.position.setY(pos.getY());
	}

	/**
	 * Checks if this collision box intersects the specified one.
	 * @return <code>true</code> if and only if:
	 * <br>-Any of the segments of the specified collision box intersects any of this one's.
	 */
	@Override
	public boolean intersects(CollisionBox box) {
		if(box != null) {
			for(Line2D l1 : this.getLines()) {
				for(Line2D l2 : box.getLines()) {
					if(l1.intersectsLine(l2)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Renders the polygon.
	 * @param g the graphics instance to draw to.
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		for(Line2D l : this.getLines()) {
			g2d.draw(l);
		}
	}
	
	/**
	 * Moves the shape back one step to its previous position. 
	 * Note: only 1 previous position can be restored after each move.
	 * @return <code>false</code> if it could not be moved back.
	 */
	@Override
	public boolean moveBack() {
		if(oldPosition != null && (oldPosition.getX() != x || oldPosition.getY() != y)) {
			this.setPosition(oldPosition);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void move(float dx, float dy) {
		this.oldPosition = this.getPosition();
		this.position.setX(position.getX() + dx);
		this.position.setY(position.getY() + dy);
	}
}
