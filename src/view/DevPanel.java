package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.GameModel;

/**
 * Used for debugging. 
 * 
 * @author
 *
 */
public class DevPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	
	public DevPanel(GameModel model) {
		super();
		this.model = model;
		this.setPreferredSize(new Dimension(100, 100));
	}

	//TODO: snyggare att stoppa allt i en egen klass
	private int lastTime;
	private int lastFPS;
	private int tickFPS;
	private int getFPS() {
		int newTime = (int)(model.getGameTime()/1000);

		tickFPS++;
		if(lastTime != newTime) {
			lastFPS = tickFPS;
			tickFPS = 0;
			lastTime = newTime;
		}
		return lastFPS;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		g.drawString("FPS: " + this.getFPS(), 10, 20);

	}
}
