package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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
	private SlotPanel[] slotPanels = new SlotPanel[3];
	private AmmoDisplay ammoDisplay;

	/**
	 * Creates a new instance which will display info about the specified player.
	 * @param player the player to display information about.
	 */
	public PlayerPanel(Player player) {
		super();
		this.setPreferredSize(new Dimension(500, 70));
		this.setLayout(new GridLayout(1, 4));
		
		this.ammoDisplay = new AmmoDisplay(player);
		this.add(ammoDisplay);
		
		for(int i = 0; i < player.getWeapons().length; i++){
			slotPanels[i] = new SlotPanel(player, player.getWeapons()[i]);
			this.add(slotPanels[i]);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//Do nothing, we want a transparent panel.
	}
}
