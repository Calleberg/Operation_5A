package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.GameController;

import model.GameModel;
import model.geometrical.Position;

public class DevPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	private GameController ctrl;
	
	public DevPanel(GameModel model, GameController ctrl) {
		super();
		this.model = model;
		this.ctrl = ctrl;
		this.setPreferredSize(new Dimension(500, 130));
	}

	//TODO: snyggare att stoppa allt i en egen klass
	private int lastTime;
	private int lastFPS;
	private int tickFPS;
	private int getFPS() {
		int newTime = (int)(ctrl.getMsSinceStart()/1000);
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
		
		g.drawString("World size: " + model.getWorld().getTiles().length + "x" + model.getWorld().getTiles()[0].length, 10, 20);
		g.drawString("Number of updates since start (ctr): " + ctrl.getNumbersOfUpdates() 
				+ ", average: " + ctrl.getNumbersOfUpdates()/(int)(1 + ctrl.getMsSinceStart()/1000) + "/s", 10, 40);
		g.drawString("Number of projectiles in model: " + model.getWorld().getProjectiles().size(), 10, 60);
		g.drawString("Number of characters/sprites: " + model.getWorld().getSprites().size(), 10, 80);
		g.drawString("Time: " + (int)(ctrl.getMsSinceStart()/1000) + " s", 10, 100);
		g.drawString("FPS: " + this.getFPS(), 10, 120);
		
		
//		g.drawString("Number of updates since start (view): " + tick 
//		+ ", average: " + tick/(int)(1 + ctrl.getMsSinceStart()/1000) + "/s", 10, 60);
//		g.drawString("Number of ObjectRenderers: " + this.objects.size(), 10, 140);
//		g.drawString("Number of tiles drawn: " + tilesDrawn + "/" + (tiles.length*tiles[0].length), 10, 160);
//		Position camPos = translatePos(new Position(getWidth()/2, getHeight()/2));
//		g.drawString("Camera position: (" + (int)camPos.getX() + "," + (int)camPos.getY() + ")", 10, 180);

	}
}
