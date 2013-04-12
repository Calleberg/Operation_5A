package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JPanel;

import base.GameController;

import model.GameModel;

public class GamePanel extends JPanel implements PropertyChangeListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	private GameController controller;
	private long tick = 0;
	
	public GamePanel(GameModel model, GameController controller) {
		super();
		this.model = model;
		this.controller = controller;
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		
		tick++;
		//TODO to bort
		Random r = new Random();
		g.fillRect((int)(model.getPlayer().getX()*40), (int)(model.getPlayer().getY()*40), 40, 40);
		g.drawString(tick + "", 10, 10);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.controller.handleMouseAt(e.getX()/40, e.getY()/40);
	}
}
