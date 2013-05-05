package view;

import javax.swing.JFrame;

/**
 * 
 * 
 * @author 
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame {
	private static Window instance = null;
	
	private Window() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);
		
//		setUndecorated(false);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	/**
	 * returns the singelton instance of Window
	 * @return the singelton instance of Window
	 */
	public static Window getInstance(){
		if (instance==null){
			instance = new Window();
		}
		return instance;
	}

}
