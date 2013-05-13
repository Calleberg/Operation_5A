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
		
//		ResourceBundle labels = ResourceBundle.getBundle("languages/LabelsBundle", Locale.ENGLISH);
//		System.out.println(labels.getString("s1"));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("HP: " + player.getHealth(), 10, 10);
		g.drawString("Ammo: " + player.getActiveWeapon().getAmmunitionInMagazine() + "/" + 
				player.getAmmoAmount(), 10, 20);
		g.drawString("Weapon: " + player.getActiveWeapon().toString(), 10, 30);
		g.drawString("Food : " + player.getFood(), 10, 40);
		
		for(int i = 0; i < player.getWeapons().length; i++) {
			g.setColor(Color.BLACK);
			if(player.getWeapons()[i] != null) {
				if(player.getWeapons()[i] == player.getActiveWeapon()) {
					g.setColor(Color.RED);
				}
				g.drawString("Slot " + (i+1) + ": " + player.getWeapons()[i].toString(), 200, 10*(i+1));
			}else{
				g.drawString("Slot " + (i+1) + ": Empty", 200, 10*(i+1));
			}
		}

	}
}
