package model.geometrical;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

/**
 * A rectangle that can be used as a collision box and which supports collision detection.
 * 
 * @author Calleberg
 *
 */
public class Rectangle implements CollisionBox {

	private Rectangle2D.Float rect;
	private Position oldPos;
	
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
		this.rect = new Rectangle2D.Float(x, y, w, h);
	}

	@Override
	public float getWidth() {
		return (float)this.rect.getWidth();
	}

	@Override
	public float getHeight() {
		return (float)this.rect.getHeight();
	}

	@Override
	public Position getPosition() {
		return new Position((float)rect.getX(), (float)rect.getY());
	}

	@Override
	public void setPosition(Position pos) {
		this.oldPos = this.getPosition();
		rect.x = pos.getX();
		rect.y = pos.getY();
	}

	/**
	 * Gives <code>true</code> if the two collision boxes intersects.
	 * before that.
	 */
	@Override
	public boolean intersects(CollisionBox box) {
		if(box != null) {
			for(java.awt.geom.Rectangle2D r : box.getRectangles()) {
				if(this.rect.intersects(r)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Can only be moved back once after each move.
	 * @return <code>true</code> if there was an earlier position to move to.
	 */
	@Override
	public boolean moveBack() {
		if(oldPos == null || (oldPos.getX() == this.getPosition().getX() && oldPos.getY() == this.getPosition().getY())) {
			return false;
		}else{
			this.setPosition(oldPos);
			return true;
		}
	}
	
	@Override
	public java.awt.geom.Rectangle2D[] getRectangles() {
		return new java.awt.geom.Rectangle2D[]{this.rect};
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[x=" + this.getPosition().getX() + ",y=" + this.getPosition().getY()
				+ ",width=" + this.getWidth() + ",height=" + this.getHeight() + "]";
	}
}
