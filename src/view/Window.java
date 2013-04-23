package view;

import javax.swing.JFrame;

/**
 * 
 * 
 * @author 
 *
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new default window.
	 */
	public Window() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		
//		setUndecorated(false);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}
