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
 * @author Martin Calleberg
 *
 */
public class Polygon implements CollisionBox {

	private List<Line2D> polygon;
	private float x, y;
	private Position oldPosition;
	private Position relativePos;	//the position this polygon moves relative to.
	private float width, height; //values that only needs to be compiled once.
	private Position position; //pre compiled value
	
	/**
	 * Creates a new polygon with the specified position as anchor.
	 */
	public Polygon() {
		this.polygon = new ArrayList<Line2D>();
		this.oldPosition = new Position(0, 0);
		this.relativePos = new Position(0, 0);
	}
	
	private Position calcPos() {
		double x = this.getLines().get(0).getX1();
		double y = this.getLines().get(0).getY1();
		for(Line2D l : this.getLines()) {
			x = Math.min(x, l.getX1());
			x = Math.min(x, l.getX2());
			y = Math.min(y, l.getY1());
			y = Math.min(y, l.getY2());
		}
		return new Position((float)x, (float)y);
	}
	
	/**
	 * Adds the specified line to the polygon.
	 * @param line the line to add.
	 */
	public void addLine(Line2D line) {
		this.polygon.add(new PolygonSegement(relativePos, (float)line.getX1(), (float)line.getY1(), 
				(float)line.getX2(), (float)line.getY2()));
	}
	
	@Override
	public float getWidth() {
		if(width == 0f) {		
			float max = 0;
			for(Line2D l : this.getLines()) {
				max = (float) Math.max(l.getX1(), max);
				max = (float) Math.max(l.getX2(), max);
				x = (float) Math.min(l.getX1(), x);
				x = (float) Math.min(l.getX2(), x);
			}
			width = max - this.getPosition().getX();
		}
		return width;
	}
	
	@Override
	public float getHeight() {
		if(height == 0f) {
			float y = (float) this.getLines().get(0).getY1();			
			float max = 0;
			for(Line2D l : this.getLines()) {
				max = (float) Math.max(l.getY1(), max);
				max = (float) Math.max(l.getY2(), max);
				y = (float) Math.min(l.getY1(), y);
				y = (float) Math.min(l.getY2(), y);
			}
			height = max - this.getPosition().getY();
		}
		return height;
	}
	
	@Override
	public List<Line2D> getLines() {
		return this.polygon;
	}

	@Override
	public Position getPosition() {
		if(this.position == null) {
			position = this.calcPos();
		}
		return new Position(relativePos.getX() + position.getX(), relativePos.getY() + position.getY());
	}

	@Override
	public void setPosition(Position pos) {
		this.oldPosition = this.getPosition(); 
		this.relativePos.setX(pos.getX() - this.position.getX());
		this.relativePos.setY(pos.getY() - this.position.getY());
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
		this.relativePos.setX(relativePos.getX() + dx);
		this.relativePos.setY(relativePos.getY() + dy);
	}
}
