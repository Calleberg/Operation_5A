package view;

import java.awt.Dimension;

import model.geometrical.Position;

public class Camera {

	private int scale;
	private Position offset;
	
	/**
	 * Creates a new camera with the specified scale.
	 * @param scale the scale.
	 */
	public Camera(int scale) {
		this.scale = scale;
		this.offset = new Position(0, 0);
	}
	
	/**
	 * Gives the x-position of the camera.
	 * This is already scaled.
	 * @return the x-position of the camera.
	 */
	public float getX() {
		return offset.getX();
	}
	
	/**
	 * Gives the y-position of the camera.
	 * This is already scaled.
	 * @return the y-position of the camera.
	 */
	public float getY() {
		return offset.getY();
	}
	
	/**
	 * Gives the offset.
	 * This is already scaled.
	 * @return the offset.
	 */
	public Position getOffset() {
		return this.offset;
	}
	
	/**
	 * Sets the specified position to the center of the camera.
	 * @param pos the position to center at.
	 * @param windowSize the size of the window the camera is on.
	 */
	public void setToCenter(Position pos, Dimension windowSize) {
		this.offset = new Position(-pos.getX() * scale + (float)windowSize.getWidth()/2, 
				-pos.getY() * scale + (float)windowSize.getHeight()/2);
	}
	
	/**
	 * Gives the scale of the camera.
	 * @return the scale of the camera.
	 */
	public int getScale() {
		return this.scale;
	}
}
