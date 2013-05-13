package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JPanel;

import view.GamePanel;

import model.GameModel;
import model.geometrical.Position;
import model.items.Item;
import model.items.Supply;
import model.items.SupplyFactory;
import model.items.weapons.Weapon;
import model.sprites.EnemyFactory;
import model.sprites.Player;
import model.world.Tile;

/**
 * Controls a specified model.
 * The controller will not start in a running state, so <code>start()</code> must be
 * called for the controller to start updating its model. However, other
 * methods will still work.
 * 
 * @author
 *
 */
public class GameController implements Runnable {

	private final int SLEEP = 1000 / 60;
	private GameModel gameModel;
	private Input input;
	private long lastTimeControllerStarted = 0;
	private long totalRuntime=0;
	private int ticks;
	private volatile boolean paused = false;
	private volatile boolean isRunning = true;
	private int tick = 0;
	private boolean tileOcuppied;
	private int foodTicks;
	private int enemySpawnTick;
	private long objectCreationTime=timeNow();
	private GamePanel gamePanel;
	
	
	public GameController(){
		gameModel = new GameModel();
		input = new Input();	
				
		gamePanel = new GamePanel(gameModel, this);

		input.setContainer(gamePanel);
		gameModel.addListener(gamePanel);

	}
	
	public JPanel getGamePanel(){
		return gamePanel;
	}
	
	@Override
	public void run() {
		lastTimeControllerStarted=timeNow();
		new Thread(gamePanel).start();
		while (isRunning){
			runThread();
		}
	}
	private synchronized void runThread(){
		if (!paused) {
			update();
			ticks++;
			try{
				Thread.sleep(SLEEP);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try{
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Gives the number of updates since start.
	 * @return the number of updates since start.
	 */
	public int getNbrOfUpdates() {
		return this.ticks;
	}
	
	/**
	 * Gives the time in ms the controller has existed.
	 * @return the time in ms the controller has existed.
	 */
	public long getMsSinceStart() {
		return timeNow()-objectCreationTime;
	}
	
	/**
	 * Sets the direction of the player towards the mouse' position.
	 * @param x The x-coordinate of the mouse' position.
	 * @param y The y-coordinate of the mouse' position.
	 */
	public void handleMouseAt(float x, float y) {
		float dx = gameModel.getPlayer().getCenter().getX() - x;
		float dy = gameModel.getPlayer().getCenter().getY() - y;
		float dir = (float)Math.atan(dy/dx);
		if(dx < 0) {
			dir -= (float)(Math.PI);
		}
		gameModel.getPlayer().setDirection(-dir + (float)Math.PI);
	}
		
	/**
	 * Updates the model.
	 */
	public void update() {
		//TODO de metoder som kallas i slutet av update() dröjer ett tag innan de exekveras AKA lagg
		//Escape pressed
		if(input.isPressed(KeyEvent.VK_ESCAPE)){
			input.reset();
			pauseThread();
			MenuController.showPauseMenu();
		}
		
		//playerMove
		this.updatePlayerPosition();
				
		//playerShoot
		if(input.mousePressed(MouseEvent.BUTTON1)){
			gameModel.playerShoot();
		}
		
		//playerReload
		if(input.isPressed(KeyEvent.VK_R)){
			gameModel.getPlayer().reloadActiveWeapon();
		}
		
		playerSwitchWeapon();
	playerPickUpWeapon();
		
		//Spawn supplies
		if(gameModel.getSpawnPoints() != null){
			tick++;
			if(tick == 600){
				int rnd = (int)Math.random()*gameModel.getSpawnPoints().size();
				Tile t = gameModel.getSpawnPoints().get(rnd);
				tileOcuppied = false;
				for(Item i : gameModel.getWorld().getItems()){
					if(i.getPosition().equals(t.getPosition())){
						tileOcuppied = true;
						tick = 0;
						break;
					}
				}
				if(!tileOcuppied){
					this.spawnSupplies(t);
					tick = 0;	
				}

			}
		}
		
		//spawnEnemies
		//TODO fix spawning pos
		enemySpawnTick++;
		if(enemySpawnTick >= 400){
			enemySpawnTick = 0;
			if((int)getMsSinceStart()/1000 < 120){
				gameModel.getWorld().addSprite(EnemyFactory.createEasyEnemy(new Position(50,50)));
			}else if((int)getMsSinceStart()/1000 < 480){
				gameModel.getWorld().addSprite(EnemyFactory.createMediumEnemy(new Position(55,55)));
			}else{
				gameModel.getWorld().addSprite(EnemyFactory.createHardEnemy(new Position(45,45)));
			}
		}
		
		
		//reducePlayerFoodLevel
		foodTicks++;
		if(foodTicks >= 120){
			gameModel.getPlayer().removeFood(1);
			foodTicks = 0;
		}
		
		//gameOver
		if(gameModel.getPlayer().getHealth() <= 0 || gameModel.getPlayer().getFood() <= 0){
			//TODO
			MenuController.gameOver(totalRuntime());
			this.stopThread();
	
		}
		
		gameModel.update();
	}
	
	private long totalRuntime() {
		totalRuntime+=timeNow()-lastTimeControllerStarted;
		return totalRuntime;
	}

	private void playerPickUpWeapon(){
		if(input.isPressed(KeyEvent.VK_G)){
			Weapon oldWeapon = gameModel.getPlayer().getActiveWeapon();
			if(gameModel.getWorld().playerPickUpWeapon()){
				Tile[][] t = gameModel.getWorld().getTiles();
				input.resetKey(KeyEvent.VK_G);
				//TODO oldWeapon.type or similar, because you cant throw fists
				if(oldWeapon.getMagazineCapacity() > 1000){
					return;
				}
				//TODO where spawn weapon?
				t[(int) gameModel.getPlayer().getPosition().getX()]
						[(int)gameModel.getPlayer().getPosition().getY()].setProperty(Tile.WEAPON_SPAWN);
				spawnWeapon(t[(int) gameModel.getPlayer().getPosition().getX()]
						[(int)gameModel.getPlayer().getPosition().getY()], oldWeapon);
			}
		}
	}
	
	private void playerSwitchWeapon(){
		if(input.isPressed(KeyEvent.VK_1)){
			gameModel.getPlayer().switchWeapon(0);
		}else if(input.isPressed(KeyEvent.VK_2)){
			gameModel.getPlayer().switchWeapon(1);
		}else if(input.isPressed(KeyEvent.VK_3)){
			gameModel.getPlayer().switchWeapon(2);
		}
	}
	
	/**
	 * Adds a Weapon to a given tile.
	 * @param t the tile given
	 */
	private void spawnWeapon(Tile t, Weapon w){
		w.setPosition(t.getPosition());
		gameModel.getWorld().getItems().add(w);
		gameModel.getWorld().fireEvent(GameModel.ADDED_SUPPLY, w);
		System.out.println("Weapon supposed to spawn");
	}
	
	/**
	 * adds a supply to a given tile
	 * @param t the tile given
	 */
	private void spawnSupplies(Tile t){
		Supply supply;
		if(t.getProperty() == Tile.FOOD_SPAWN){//Create a food
			supply = SupplyFactory.createFood(25, t.getPosition());
			gameModel.getWorld().getItems().add(supply);
			gameModel.getWorld().fireEvent(GameModel.ADDED_SUPPLY, supply);
		}else if(t.getProperty() == Tile.AMMO_SPAWN){//Create an ammo
			supply = SupplyFactory.createAmmo(12, t.getPosition());
			gameModel.getWorld().getItems().add(supply);
			gameModel.getWorld().fireEvent(GameModel.ADDED_SUPPLY, supply);
		}else if(t.getProperty() == Tile.HEALTH_SPAWN){//Create a health
			supply = SupplyFactory.createHealth(25, t.getPosition());
			gameModel.getWorld().getItems().add(supply);
			gameModel.getWorld().fireEvent(GameModel.ADDED_SUPPLY, supply);
		}else /*if(t.getProperty() == Tile.WEAPON_SPAWN)*/{//create a weapon
			Weapon w = gameModel.getPlayer().getActiveWeapon();
			w.setPosition(t.getPosition());
			gameModel.getWorld().getItems().add(w);
			gameModel.getWorld().fireEvent(GameModel.ADDED_SUPPLY, w);
			System.out.println("Weapon supposed to spawn");
		}	
	}
	
	
	
	/*
	 * Updates the player's position.
	 */
	private void updatePlayerPosition() {
		if(input.isPressed(KeyEvent.VK_W) && input.isPressed(KeyEvent.VK_D)) {
			gameModel.getPlayer().setMoveDir((float)(Math.PI/4));
		}else if(input.isPressed(KeyEvent.VK_D) && input.isPressed(KeyEvent.VK_S)) {
			gameModel.getPlayer().setMoveDir((float)(-Math.PI/4));
		}else if(input.isPressed(KeyEvent.VK_A) && input.isPressed(KeyEvent.VK_S)) {
			gameModel.getPlayer().setMoveDir((float)(-Math.PI*3/4));
		}else if(input.isPressed(KeyEvent.VK_W) && input.isPressed(KeyEvent.VK_A)) {
			gameModel.getPlayer().setMoveDir((float)(Math.PI*3/4));
		}else if(input.isPressed(KeyEvent.VK_W)) {
			gameModel.getPlayer().setMoveDir((float)(Math.PI/2));
		}else if(input.isPressed(KeyEvent.VK_A)) {
			gameModel.getPlayer().setMoveDir((float)(Math.PI));
		}else if(input.isPressed(KeyEvent.VK_S)) {
			gameModel.getPlayer().setMoveDir((float)(-Math.PI/2));
		}else if(input.isPressed(KeyEvent.VK_D)) {
			gameModel.getPlayer().setMoveDir(0f);
		}else{
			gameModel.getPlayer().setState(Player.State.STANDING);
		}	
	}
	
	//TODO understående metoder skall också gära sama sak med game panel.
	/**
	 * Pauses the thread from a running state. To resume the thread call <code>resumeThread()</code>.
	 */
	public synchronized void pauseThread(){
		paused=true;
		totalRuntime();
		gamePanel.pauseThread();
	}
	/**
	 * Resumes the thread to a running state. To resume the thread call <code>pauseThread()</code>.
	 */
	public synchronized void resumeThread(){
		paused=false;
		notify();
		lastTimeControllerStarted=timeNow();
		gamePanel.resumeThread();
	}
	/**
	 * Stops the thread from executing further actions, is irreversible. 
	 */
	public synchronized void stopThread(){
		isRunning=false;
		notify();
		totalRuntime();
		gamePanel.stopThread();
	}
	private long timeNow(){
		return Calendar.getInstance().getTimeInMillis();
	}
}
