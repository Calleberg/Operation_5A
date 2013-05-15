package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.IO.Resources;

import model.items.weapons.Weapon;
import model.sprites.Player;
/**
 * A class that draws the picture that each weapon slot has in the HUD(PlayerPanel).
 * @author Jonatan
 *
 */
public class SlotPanel extends JPanel{
	private int imageNumber;
	private BufferedImage image;
	private String name;
	private Player player;
	private static BufferedImage[] textures = Resources.splitImages("supplies.png",5,5);//TODO change to real file
	private Weapon weapon;
	/**
	 * Creates an instance of a slot panel containing a picture and name of the weapon, 
	 * it also indicates what weapon is chosen 
	 * @param player the player holding the weapon
	 * @param weapon the weapon shown
	 */

	public SlotPanel(Player player, Weapon weapon){
		super();
		this.imageNumber = weapon.getIconNumber();
		this.name = weapon.toString();
		this.setImage();
		this.setSize(new Dimension(100, 60));
		this.player = player;
		this.weapon = weapon;
	}
	
	private void setImage(){
		//TODO see above
		this.image = textures[3];
	}
	

	public void paint(Graphics g){
		g.drawString("" + this.name, 5, 55);
		g.drawImage(image, 30, 5, null);
		
		if(player.getActiveWeapon() == this.weapon){
			g.setColor(Color.RED);
			g.drawLine(0, 0, 99, 0);
			g.drawLine(99, 0, 99, 59);
			g.drawLine(99, 59, 0, 59);
			g.drawLine(0, 59, 0, 0);
		}	
	}
}
