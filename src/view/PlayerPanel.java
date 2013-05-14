package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.sprites.Player;

/**
 * The GUI object which displays information about the player.
 * 
 * @author 
 *
 */
public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;

	/**
	 * Creates a new instance which will display info about the specified player.
	 * @param player the player to display information about.
	 */
	public PlayerPanel(Player player) {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(500, 100));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("Ammo: " + player.getActiveWeapon().getAmmunitionInMagazine() + "/" + 
				player.getAmmoAmount(), 10, 20);
		g.drawString("Weapon: " + player.getActiveWeapon().toString(), 10, 30);
		
		for(int i = 0; i < player.getWeapons().length; i++) {
			if(player.getWeapons()[i] != null) {
				g.setColor((player.getWeapons()[i] == player.getActiveWeapon()) ? Color.RED : Color.BLACK);
				g.drawString("Slot " + (i+1) + ": " + player.getWeapons()[i].toString(), 200, 10*(i+1));
			}else{
				g.drawString("Slot " + (i+1) + ": Empty", 200, 10*(i+1));
			}
		}

	}
}
