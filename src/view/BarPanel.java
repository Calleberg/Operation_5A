package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.sprites.Player;

/**
 * A GUI object which renders the hp and food values of a specified player object.
 * 
 * @author 
 *
 */
public class BarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;

	/**
	 * Creates a new instance which will display info about the specified player.
	 * @param player the player to display information about.
	 */
	public BarPanel(Player player) {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(100, 100));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("HP: " + player.getHealth(), 10, 20);
		g.drawString("Food : " + player.getFood(), 10, 40);
	}
}
