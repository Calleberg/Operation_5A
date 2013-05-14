package view;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A class which renders a health bar. This works similar to a progress bar (max value and current value).
 * 
 * @author 
 *
 */
public class HealthBar {

	private int max;
	private int value;
	private float w, h;
	private Color barColor = Color.GREEN;
	
	/**
	 * Creates a new health bar with the specified dimension and max value.
	 * @param height the height of the bar.
	 * @param width the width of the bar.
	 * @param max the maximum value of the bar.
	 */
	public HealthBar(float height, float width, int max) {
		this(height, width, max, max);
	}
	
	/**
	 * Creates a new health bar with the specified dimension and with the specified start and 
	 * current value.
	 * @param height the height of the bar.
	 * @param width the width of the bar.
	 * @param max the max value of the bar.
	 * @param start the current value of the bar.
	 */
	public HealthBar(float height, float width, int max, int start) {
		this.max = max;
		this.w = width;
		this.h = height;
		this.setValue(start);
	}
	
	/**
	 * Sets the value to the specified one.
	 * @param value the new value.
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Sets the colour of the bar.
	 * @param color the new colour of the bar.
	 */
	public void setColor(Color color) {
		this.barColor = color;
	}
	
	/**
	 * Renders the bar.
	 * @param g the graphics instance to use.
	 * @param x the prescaled X coordinate to draw at.
	 * @param y the prescaled Y coordinate to draw at.
	 * @param scale the scale to draw at.
	 */
	public void render(Graphics g, int x, int y, int scale) {
		g.setColor(this.barColor);
		g.fillRect(x, y, (int)((w * scale) * ((float)value/max)), (int)(h * scale));
		g.setColor(Color.BLACK);
		g.drawRect(x, y, (int)(w * scale), (int)(h * scale));
	}
}
