package view;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JPanel;

import model.GameModel;

public class GamePanel extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	private long tick = 0;
	
	public GamePanel(GameModel model) {
		super();
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		
		tick++;
		//TODO to bort
		Random r = new Random();
		g.drawString(tick + "", 10, 10);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		this.repaint();
	}
}
