package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.GameModel;
import model.items.weapons.WeaponFactory;
import model.sprites.Player;

import view.GamePanel;
import view.Window;
import view.menu.HighscorePanel;
import view.menu.LoadingScreen;
import view.menu.MainMenu;
import view.menu.MenuButton;
import view.menu.PauseMenu;

/**
 * The controller which is responsible for managing the program window and its menus.
 * 
 * @author Vidar Eriksson
 *
 */
public class MenuController{
	private static final Window window = new Window();
	private static JPanel activePanel = null;
	private static GameController gameController = null;
	private static GamePanel gamePanel = null;

	public MenuController() {
		mainMenu();
	}

	private static MenuButton[] createMainMenuButtons() {
		MenuButton buttons[] = new MenuButton[5];
		
		buttons[0]= new MenuButton("New Game");
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		buttons[1]= new MenuButton("Load Game");
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadSavedGame();
			}
		});
		
		buttons[2]= new MenuButton("Highscore");
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				highscore();
			}
		});
		
		buttons[3]= new MenuButton("Settings");
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		});
		
		buttons[4]= new MenuButton("Exit Game");
		buttons[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
		
		return buttons;
	}
	
	private static MenuButton[] createPauseMenuButtons() {
		MenuButton buttons[] = new MenuButton[5];
		
		buttons[0]= new MenuButton("Settings");
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		});
		
		buttons[1]= new MenuButton("Save / Load");
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLoadGame();
			}
		});
		
		buttons[2]= new MenuButton("Main Menu");
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainMenu();
			}
		});
		
		buttons[3]= new MenuButton("Exit Game");
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
		
		buttons[4]= new MenuButton("Resume");
		buttons[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resumeGame();
			}
		});
		
		return buttons;
	}

	
	private static void startGame(){
		//TODO
		LoadingScreen l = new LoadingScreen();
		changeWindowTo(l);
		if (gameController == null){
			createGameController();
		}
		changeWindowTo(gamePanel);
	}
	private static void exitGame(){
		System.exit(0);
	}
	private static void highscore(){
		//TODO
		changeWindowTo(new HighscorePanel());
	}
	private static void settings(){
		//TODO
	}
	private static void saveLoadGame(){
		//TODO
	}
	private static void loadSavedGame(){
		//TODO
	}
	/**
	 * Changes the program window to the main menu.
	 */
	public static void mainMenu(){
		//TODO
		changeWindowTo(new MainMenu("Main Menu", createMainMenuButtons()));
	}
	/**
	 * Changes the program window to the paused game menu.
	 */
	public static void pauseMenu(){
		//TODO

		gameController.pause(true);
		
		changeWindowTo(new PauseMenu("PAUSE", createPauseMenuButtons()));

	}
	private static void changeWindowTo(JPanel p){
		if (activePanel != null){
			window.remove(activePanel);
		}
		window.add(p);
		activePanel=p;

//		window.revalidate();
		window.invalidate();
		window.validate();
		p.repaint();
		p.requestFocus();
	}
	private static void resumeGame() {

		gameController.pause(false);

		changeWindowTo(gamePanel);

		// TODO Auto-generated method stub
		
	}
	
	private static void createGameController(){

		Input input = new Input();
		GameModel gameModel = createGameModel();
		gameController = new GameController(gameModel, input);
		gamePanel = new GamePanel(gameModel, gameController);

		input.setContainer(gamePanel);
		gameModel.addListener(gamePanel);

		//Starts all the loops
		gamePanel.start();
		gameController.start();

	}
	private static GameModel createGameModel() {
		GameModel model = new GameModel();
		Player player = new Player(50,50);
		player.setWeapon(WeaponFactory.startingWeapon());
		model.setPlayer(player);

		return model;
	}

}
