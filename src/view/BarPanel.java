package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import controller.GameController;

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
	private StatusBar hpBar;
	private StatusBar foodBar;
	
	private final String hp, food;

	/**
	 * Creates a new instance which will display info about the specified player.
	 * @param player the player to display information about.
	 */
	public BarPanel(Player player) {
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(120, 100));
		hpBar = new StatusBar(20, 100, 100, player.getHealth());
		foodBar = new StatusBar(20, 100, 100, player.getFood());
		
		ResourceBundle bundle = ResourceBundle.getBundle("bundle/GamePanels", Locale.getDefault());
		this.hp = bundle.getString("hitPoints");
		this.food = bundle.getString("food");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 255));
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		hpBar.setValue(player.getHealth());
		foodBar.setValue(player.getFood());
		
		setHpBarColor();
		setFoodBarColor();
		g.setColor(Color.BLACK);
		
		g.drawString(hp, 10, 20);
		hpBar.render(g, 10, 25, 1);
		
		g.drawString(food, 10, 56);
		foodBar.render(g, 10, 61, 1);
		
	}
	/*
	 * Sets the color of the food bar depending on the food level of the player
	 * green = high/gaining life, yellow = medium and red = low/loosing life
	 */
	private void setFoodBarColor() {
		if(player.getFood() <= GameController.FOOD_LOW){
			foodBar.setColor(Color.RED);
		}else if(player.getFood() < GameController.FOOD_HIGH){
			foodBar.setColor(Color.YELLOW);
		}else{
			foodBar.setColor(Color.GREEN);
		}
		
	}
	/*
	 * Sets the color of the health bar depending on the health bar of the player
	 * red indicates low life, green is medium and high
	 */
	private void setHpBarColor(){
		if(player.getHealth() > 33){
			hpBar.setColor(Color.GREEN);
		}else{
			hpBar.setColor(Color.RED);
		}
	}
}
