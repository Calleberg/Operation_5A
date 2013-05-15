package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import resources.HUDAmmoFont;

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
	private SlotPanel[] slotPanels = new SlotPanel[3];

	/**
	 * Creates a new instance which will display info about the specified player.
	 * @param player the player to display information about.
	 */
	public PlayerPanel(Player player) {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(500, 100));
		
		for(int i = 0; i < player.getWeapons().length; i++){
			slotPanels[i] = new SlotPanel(player, player.getWeapons()[i]);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(HUDAmmoFont.getFont());
		
		if(player.getActiveWeapon().getAmmunitionInMagazine()>= 0){
			g.drawString("" + player.getActiveWeapon().getAmmunitionInMagazine() + "/" + 
					player.getAmmoAmount(), 10, 55);
		}else{
			g.drawString(Character.toString('\u221e'), 10, 55);
		}
		
		for(int i = 0; i < player.getWeapons().length; i++){
			if(slotPanels[i].getWidth() > 0 && slotPanels[i].getHeight() > 0){
				g.drawImage(createImage(slotPanels[i]), 50 + 100*(i + 1), 10, null);
			}
		}
	}
	/*
	 * Creates a buffered image from a JPanel
	 */
	private BufferedImage createImage(JPanel slotPanel){
		int width = slotPanel.getWidth();
		int height = slotPanel.getHeight();
		BufferedImage slotImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = slotImage.createGraphics();
		slotPanel.paint(g);
		return slotImage;
	}
}
