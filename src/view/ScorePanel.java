package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.JPanel;

import model.GameModel;
import resources.HUDFonts;
import resources.Translator;

/**
 * A GUI object which will render a score.
 * 
 * @author 
 *
 */
public class ScorePanel extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private GameModel model;
	
	private boolean timerOn = false;
	private long timerStart;
	private static final int TIME_MAX = 2000;
	private int addedScore;
	
	/**
	 * Creates a new instance of the object.
	 * @param model The model to fetch values from.
	 */
	public ScorePanel(GameModel model) {
		super();
		this.setPreferredSize(new Dimension(200, 90));
		this.model = model;
		model.addListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, 0, this.getWidth(), this.getHeight()/2);
		
		g.setFont(HUDFonts.getScoreFont());
		g.setColor(Color.WHITE);
		String text = Translator.getMenuString("score") + ": " + model.getScore();
		g.drawString(text, this.getWidth()/2 - 
				(int)g.getFontMetrics().getStringBounds(text, g).getWidth()/2, 30);
		
		// Display just added score
		if(timerOn) {
			if(Calendar.getInstance().getTimeInMillis() - timerStart > TIME_MAX) {
				timerOn = false;
			}
			text = "+" + addedScore;
			g.setColor(Color.GREEN);
			g.drawString(text, this.getWidth()/2 - 
					(int)g.getFontMetrics().getStringBounds(text, g).getWidth()/2, 60);
		}		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(GameModel.EVENT_ADDED_SCORE)) {
			if((Integer)evt.getNewValue() > 1) {
				addedScore = (Integer)evt.getNewValue();			
				timerOn = true;
				timerStart = Calendar.getInstance().getTimeInMillis();
			}
		}
	}
}
