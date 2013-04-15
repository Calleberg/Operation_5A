package base;

import java.awt.event.KeyEvent;

import sprite.Player;

import model.GameModel;

public class GameController extends Thread {

	private int sleep;
	private GameModel model;
	private Input input;
	
	public GameController(GameModel model, Input input) {
		this.model = model;
		this.input = input;
		this.sleep = 1000 / 60;
		this.start();
	}
	
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
	
	public void handleMouseAt(float x, float y) {
		float dx = model.getPlayer().getX() - x;
		float dy = model.getPlayer().getY() - y;
		float dir = (float)Math.atan(dy/dx);
		if(dx < 0) {
			dir -= (float)(Math.PI);
		}
		model.getPlayer().setDirection(-dir + (float)Math.PI);
	}
	
	public void update() {
		if(input.isPressed(KeyEvent.VK_W)) {
			model.getPlayer().setState(Player.State.FORWARD);
		}else if(input.isPressed(KeyEvent.VK_A)) {
			model.getPlayer().setState(Player.State.LEFT);
		}else if(input.isPressed(KeyEvent.VK_S)) {
			model.getPlayer().setState(Player.State.BACKWARDS);
		}else if(input.isPressed(KeyEvent.VK_D)) {
			model.getPlayer().setState(Player.State.RIGHT);
		}else{
			model.getPlayer().setState(Player.State.STANDING);
		}
		
		model.update();
	}
}
