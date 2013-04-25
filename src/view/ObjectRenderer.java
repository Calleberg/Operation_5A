package view;

import java.awt.Graphics;

import model.geometrical.Position;

/**
 * An interface for creating view classes which will render specific objects.
 * 
 * @author 
 * @param <T>
 *
 * @param <T>
 */
public interface ObjectRenderer<T> {

	/**
	 * Gives the objects this renders.
	 * @return the objects this renders.
	 */
	public T getObject();
	
	/**
	 * Sets the object this renders.
	 * @param object the object to render.
	 */
	public void setObject(T object);
	
	/**
	 * Renders the object this holds.
	 * @param g the graphics instance to draw to.
	 * @param offset the offset to draw at.
	 * @param scale the scale to draw at.
	 */
	public void render(Graphics g, Position offset, int scale);
}
