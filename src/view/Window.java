package view;

import java.awt.Dimension;
import java.awt.Image;

import inputOutput.SettingsModel;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The main widows of the game. All graphics in the game is presented in this frame.
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame {
	private JPanel activePanel = null;
	private boolean isFullScreen = !SettingsModel.getFullscreen();
	private static final Dimension DEFAULT_SIZE = new Dimension(1000, 700);
	
	/**
	 * Creates a new window.
	 */
	public Window() {
		super();

		this.setTitle("Operation 5A");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(getProgramIcon());
		this.setUndecorated(true);
		update();

	}
	private Image getProgramIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	private void update(){
		if(isFullScreen != SettingsModel.getFullscreen()) {
			if (SettingsModel.getFullscreen()){
				this.fullScreen();
			} else {
				this.windowed();
			}
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.isFullScreen = SettingsModel.getFullscreen();
		}
	}
	
	/**
	 * Sets the window to fullscreen mode.
	 */
	private void fullScreen() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setAlwaysOnTop(true);

		if(!this.isUndecorated()) {
			this.dispose();
			this.setUndecorated(true);
			this.setVisible(true);
		}
	}
	
	/**
	 * Sets the window to windowed mode.
	 */
	private void windowed() {
		this.setExtendedState(NORMAL);
		this.setSize(DEFAULT_SIZE);
		this.setAlwaysOnTop(false);

		if(this.isUndecorated()) {
			this.dispose();
			this.setUndecorated(false);
			this.setVisible(true);
		}
	}
	
	/**
	 * Adds a JPanel to the window and removes the old ones.
	 * @param p the panel to be added.
	 */
	public void add(JPanel p){
		super.add(p);
		if (activePanel != null){
			remove(activePanel);
		}
		activePanel=p;
		validate();
		p.paintImmediately(activePanel.getBounds());
		p.repaint();
		p.requestFocus();
		p.validate();
		update();
	}

	

}
