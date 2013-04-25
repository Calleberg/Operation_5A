package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import base.Input;


import model.GameModel;
import model.geometrical.Position;
import model.sprites.EnemyFactory;
import model.sprites.Player;

/**
 * Controls a specified model.
 * 
 * @author
 *
 */
public class GameController extends Thread {

	private int sleep;
	private GameModel model;
	private Input input;
	
	/**
	 * Creates a new gameController.
	 * @param model The model which the gameController controls.
	 * @param input The input which the gameController collects input from.
	 */
	public GameController(GameModel model, Input input) {
		this.model = model;
		this.input = input;
		this.sleep = 1000 / 60;
		this.start();
	
		model.getWorld().addSprite(EnemyFactory.createEasyEnemy(new Position (3, 3)));
		model.getWorld().addSprite(EnemyFactory.createMediumEnemy(new Position (6, 10)));
	}
	/**
	 * Update's the game a specific amount of times per second.
	 */
	@Override
	public void run() {
		while(true) {
			this.update();
			try{
				Thread.sleep(sleep);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Sets the direction of the player towards the mouse' position.
	 * @param x The x-coordinate of the mouse' position.
	 * @param y The y-coordinate of the mouse' position.
	 */
	public void handleMouseAt(float x, float y) {
		float dx = model.getPlayer().getX() - x;
		float dy = model.getPlayer().getY() - y;
		float dir = (float)Math.atan(dy/dx);
		if(dx < 0) {
			dir -= (float)(Math.PI);
		}
		model.getPlayer().setDirection(-dir + (float)Math.PI);
	}
	/**
	 * Updates the model.
	 */
	public void update() {
		//playerMove
		this.updatePlayerPosition();
		
		//playerShoot
		if(input.mousePressed(MouseEvent.BUTTON1)){
			model.playerShoot();
		}
		
		model.update();
	}
	
	/*
	 * Updates the player's position.
	 */
	private void updatePlayerPosition() {
		if(input.isPressed(KeyEvent.VK_W) && input.isPressed(KeyEvent.VK_D)) {
			model.getPlayer().setMoveDir((float)(Math.PI/4));
		}else if(input.isPressed(KeyEvent.VK_D) && input.isPressed(KeyEvent.VK_S)) {
			model.getPlayer().setMoveDir((float)(-Math.PI/4));
		}else if(input.isPressed(KeyEvent.VK_A) && input.isPressed(KeyEvent.VK_S)) {
			model.getPlayer().setMoveDir((float)(-Math.PI*3/4));
		}else if(input.isPressed(KeyEvent.VK_W) && input.isPressed(KeyEvent.VK_A)) {
			model.getPlayer().setMoveDir((float)(Math.PI*3/4));
		}else if(input.isPressed(KeyEvent.VK_W)) {
			model.getPlayer().setMoveDir((float)(Math.PI/2));
		}else if(input.isPressed(KeyEvent.VK_A)) {
			model.getPlayer().setMoveDir((float)(Math.PI));
		}else if(input.isPressed(KeyEvent.VK_S)) {
			model.getPlayer().setMoveDir((float)(-Math.PI/2));
		}else if(input.isPressed(KeyEvent.VK_D)) {
			model.getPlayer().setMoveDir(0f);
		}else{
			model.getPlayer().setState(Player.State.STANDING);
		}
	}	
}
