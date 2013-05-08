package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import model.GameModel;
import model.geometrical.Position;
import model.items.Item;
import model.items.Supply;
import model.items.SupplyFactory;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponFactory;
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
public class GameController extends Thread {

	private final int SLEEP = 1000 / 60;
	private GameModel model;
	private Input input;
	private long startTime = Calendar.getInstance().getTimeInMillis();
	private int ticks;
	private boolean isRunning = true;
	private int tick = 0;
	private boolean tileOcuppied;
	private int foodTicks;
	private int enemySpawnTick;

	
	/**
	 * Creates a new gameController.
	 * @param model The model which the gameController controls.
	 * @param input The input which the gameController collects input from.
	 */
	public GameController(GameModel model, Input input) {
		this.model = model;
		this.input = input;
		
		model.getWorld().addSprite(EnemyFactory.createEasyEnemy(new Position (55, 55)));
		model.getWorld().addSprite(EnemyFactory.createMediumEnemy(new Position (45, 45)));
		
	}
	
	/**
	 * Update's the game a specific amount of times per second.
	 */
	@Override
	public void run() {
		while (true){
			runControler();
		}
	}
	private void runControler(){

		while(isRunning) {
			this.update();
			ticks++;
			try{
				Thread.sleep(SLEEP);
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
		long time = Calendar.getInstance().getTimeInMillis();
		return time - this.startTime;
	}
	
	/**
	 * Sets the direction of the player towards the mouse' position.
	 * @param x The x-coordinate of the mouse' position.
	 * @param y The y-coordinate of the mouse' position.
	 */
	public void handleMouseAt(float x, float y) {
		float dx = model.getPlayer().getCenter().getX() - x;
		float dy = model.getPlayer().getCenter().getY() - y;
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
		
		//playerReload
		if(input.isPressed(KeyEvent.VK_R)){
			model.getPlayer().reloadActiveWeapon();
		}
		
		playerSwitchWeapon();
		playerPickUpWeapon();
		
		//Spawn supplies
		if(model.getSpawnPoints() != null){
			tick++;
			if(tick == 600){
				int rnd = (int)Math.random()*model.getSpawnPoints().size();
				Tile t = model.getSpawnPoints().get(rnd);
				tileOcuppied = false;
				for(Item i : model.getWorld().getItems()){
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
				model.getWorld().addSprite(EnemyFactory.createEasyEnemy(new Position(50,50)));
			}else if((int)getMsSinceStart()/1000 < 480){
				model.getWorld().addSprite(EnemyFactory.createMediumEnemy(new Position(55,55)));
			}else{
				model.getWorld().addSprite(EnemyFactory.createHardEnemy(new Position(45,45)));
			}
		}
		
		
		//reducePlayerFoodLevel
		foodTicks++;
		if(foodTicks >= 120){
			model.getPlayer().removeFood(1);
			foodTicks = 0;
		}
		
		//gameOver
		if(model.getPlayer().getHealth() <= 0 || model.getPlayer().getFood() <= 0){
			//TODO
			System.out.println("Game over, Tid: " + getMsSinceStart()/1000 + "s");
			this.pause(true);
		}
		
		model.update();
	}
	
	private void playerPickUpWeapon(){
		if(input.isPressed(KeyEvent.VK_G)){
<<<<<<< HEAD
			if(model.getWorld().playerPickUpWeapon()){
				
			}
			Weapon w = model.getPlayer().getActiveWeapon();
			model.getPlayer().pickUpWeapon(null);
			Tile[][] t = model.getWorld().getTiles();
//			input.resetKey(KeyEvent.VK_G);
			//TODO where spawn weapon?
			t[(int) model.getPlayer().getPosition().getX()-2]
					[(int)model.getPlayer().getPosition().getY()].setProperty(Tile.WEAPON_SPAWN);
			spawnSupplies(t[(int) model.getPlayer().getPosition().getX()-2]
					[(int)model.getPlayer().getPosition().getY()]);
=======
			Weapon oldWeapon = model.getPlayer().getActiveWeapon();
			if(model.getWorld().playerPickUpWeapon()){
				Tile[][] t = model.getWorld().getTiles();
				input.resetKey(KeyEvent.VK_G);
				//TODO oldWeapon.type or similar, because you cant throw fists
				if(oldWeapon.getMagazineCapacity() > 1000){
					return;
				}
				//TODO where spawn weapon?
				t[(int) model.getPlayer().getPosition().getX()-2]
						[(int)model.getPlayer().getPosition().getY()].setProperty(Tile.WEAPON_SPAWN);
				spawnWeapon(t[(int) model.getPlayer().getPosition().getX()-2]
						[(int)model.getPlayer().getPosition().getY()], oldWeapon);
			}
>>>>>>> origin/temp4
		}
	}
	
	private void playerSwitchWeapon(){
		if(input.isPressed(KeyEvent.VK_1)){
			model.getPlayer().switchWeapon(0);
		}else if(input.isPressed(KeyEvent.VK_2)){
			model.getPlayer().switchWeapon(1);
		}else if(input.isPressed(KeyEvent.VK_3)){
			model.getPlayer().switchWeapon(2);
		}
	}
	
	/**
	 * Adds a Weapon to a given tile.
	 * @param t the tile given
	 */
	private void spawnWeapon(Tile t, Weapon w){
		w.setPosition(t.getPosition());
		model.getWorld().getItems().add(w);
		model.getWorld().fireEvent(GameModel.ADDED_SUPPLY, w);
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
			model.getWorld().getItems().add(supply);
			model.getWorld().fireEvent(GameModel.ADDED_SUPPLY, supply);
		}else if(t.getProperty() == Tile.AMMO_SPAWN){//Create an ammo
			supply = SupplyFactory.createAmmo(12, t.getPosition());
			model.getWorld().getItems().add(supply);
			model.getWorld().fireEvent(GameModel.ADDED_SUPPLY, supply);
		}else if(t.getProperty() == Tile.HEALTH_SPAWN){//Create a health
			supply = SupplyFactory.createHealth(25, t.getPosition());
			model.getWorld().getItems().add(supply);
			model.getWorld().fireEvent(GameModel.ADDED_SUPPLY, supply);
		}else /*if(t.getProperty() == Tile.WEAPON_SPAWN)*/{//create a weapon
<<<<<<< HEAD
			Weapon w = WeaponFactory.startingWeapon();
=======
			Weapon w = model.getPlayer().getActiveWeapon();
>>>>>>> origin/temp4
			w.setPosition(t.getPosition());
			model.getWorld().getItems().add(w);
			model.getWorld().fireEvent(GameModel.ADDED_SUPPLY, w);
			System.out.println("Weapon supposed to spawn");
		}	
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
		
		if(input.isPressed(KeyEvent.VK_ESCAPE)){
			System.out.println("ESCAPE pressed");
			input.reset();
			MenuController.pauseMenu();
		}
			

	}
	/**
	 * Pauses the thread from running depending on the input parameter.
	 * @param b the parameter. if true the trhead will not execute.
	 */
	public void pause(boolean b){
		isRunning=!b;
//		run();
	}
}
