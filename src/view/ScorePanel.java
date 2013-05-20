package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import resources.HUDFonts;

import model.GameModel;

/**
 * A GUI object which will render a score.
 * 
 * @author 
 *
 */
public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	
	/**
	 * Creates a new instance of the object.
	 * @param model The model to fetch values from.
	 */
	public ScorePanel(GameModel model) {
		super();
		this.setPreferredSize(new Dimension(100, 100));
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setFont(HUDFonts.getScoreFont());
		g.setColor(Color.BLACK);
		g.drawString("Score: " + model.getScore(), 0, 20);
	}
}
