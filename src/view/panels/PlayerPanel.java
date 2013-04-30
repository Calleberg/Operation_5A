package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.sprites.Player;

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;

	public PlayerPanel(Player player) {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(500, 100));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("HP: " + player.getHealth(), 10, 10);
		g.drawString("Ammo: " + player.getActiveWeapon().getAmmunitionInMagazine() + "/" + 
				player.getAmmoAmount(), 10, 20);
		g.drawString("Weapon: " + player.getActiveWeapon().toString(), 10, 30);
	}
}
