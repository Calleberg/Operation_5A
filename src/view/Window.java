package view;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int x, y;

	public Window() {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
