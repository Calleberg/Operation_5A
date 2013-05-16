package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import model.sprites.Player;

import resources.HUDAmmoFont;

/**
 * Renders the ammo a player currently possesses.
 * 
 * @author
 *
 */
public class AmmoDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;
	
	/**
	 * Creates a new instance which will display info about the specified player.
	 * @param player the player to display info about.
	 */
	public AmmoDisplay(Player player) {
		this.player = player;
	}
	
	@Override
	public void paintComponent(Graphics g) {		
		g.setColor(Color.BLACK);
		g.setFont(HUDAmmoFont.getFont());
		
		int total = player.getAmmoAmount();
		int inGun = player.getActiveWeapon().getAmmunitionInMagazine();
		
		if(inGun >= 0){
			String info = inGun + "/" + total;
			g.drawString(info, this.getWidth()/2 - (int)g.getFontMetrics().getStringBounds(info, g).getWidth()/2, 
					this.getHeight() - 15);
		}
	}
}
