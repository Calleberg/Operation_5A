package base;

import model.GameModel;

public class GameController extends Thread {

	private int sleep;
	private GameModel model;
	
	public GameController(GameModel model) {
		this.model = model;
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
	
	public void update() {
		model.update();
	}
}
