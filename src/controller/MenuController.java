package controller;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Window;
import view.menu.GameOverPanel;
import view.menu.HighscorePanel;
import view.menu.LoadGamePanel;
import view.menu.LoadingScreen;
import view.menu.MainMenu;
import view.menu.MenuButton;
import view.menu.PauseMenu;
import view.menu.SettingsPanel;

/**
 * The controller which is responsible for managing the program window and it's menus.
 * 
 * @author Vidar Eriksson
 *
 */
public class MenuController{
	private static final Window WINDOW = new Window();
	private static GameController gameController = null;
	
	/**
	 * Creates game window and starts the main menu for the game.
	 */
	public MenuController() {
		showMainMenu();		
	}
	/**
	 * Changes the program window to the main menu.
	 */
	public static void showMainMenu(){
		WINDOW.add(new MainMenu("Main Menu", MenuButtons.getMainMenuButtons()));
	}
	private static void startNewGame(){
		//TODO
		WINDOW.add(new LoadingScreen());
		
		
		if (gameController != null){
			gameController.stopThread();
		}

		gameController = new GameController();
		WINDOW.add(gameController.getGamePanel());
		new Thread(gameController).start();
	}
	private static void exitGame(){
		System.exit(0);
	}
	private static void showhighscore(){
		WINDOW.add(new HighscorePanel(MenuButtons.getMainMenuButton()));
	}
	private static void showSettings(){
		WINDOW.add(new SettingsPanel(MenuButtons.getMainMenuButton()));
	}
	private static void showSaveLoadGame(){
		//TODO 
		//TODO 
		//TODO 
		//TODO 
		//TODO  fixa i sublkkass i denna så att återgå till menu button finns
		
	}
	private static void showLoadSavedGame(){
		WINDOW.add(new LoadGamePanel(MenuButtons.getMainMenuButton()));
	}
	/**
	 * 
	 * @param the time the game has been played. Corresponds to the highscore.
	 */
	public static void gameOver(long totalRuntime){
		WINDOW.add(new GameOverPanel(totalRuntime));
	}
	/**
	 * Changes the program window to the paused game menu.
	 */
	public static void showPauseMenu(){
		WINDOW.add(new PauseMenu("PAUSE", MenuButtons.getPauseMenuButtons(), gameController.getGamePanel()));
	}
	
	private static void resumeGame() {
		WINDOW.add(gameController.getGamePanel());
		gameController.resumeThread();
	}
	
	
	private static class MenuButtons {
		private static MenuButton mainMenuButtons[] = null;
		private static MenuButton pauseMenuButtons[] = null;
		private static MenuButton toMainMenuButton = null;
		
		public static MenuButton getMainMenuButton() {
			if (toMainMenuButton == null){
				toMainMenuButton = new MenuButton("New Game");
				toMainMenuButton .addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showMainMenu();
					}
				});
			}
			return toMainMenuButton;
		}
		
		private static MenuButton[] getMainMenuButtons() {
			if (mainMenuButtons==null){
				
				MenuButton[] buttons = new MenuButton[5];
				
				buttons[0]= new MenuButton("New Game");
				buttons[0].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						startNewGame();
					}
				});
				
				buttons[1]= new MenuButton("Load Game");
				buttons[1].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showLoadSavedGame();
					}
				});
				
				buttons[2]= new MenuButton("Highscore");
				buttons[2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showhighscore();
					}
				});
				
				buttons[3]= new MenuButton("Settings");
				buttons[3].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showSettings();
					}
				});
				
				buttons[4]= new MenuButton("Exit Game");
				buttons[4].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						exitGame();
					}
				});
				mainMenuButtons=buttons;
			}
			
			return mainMenuButtons;
		}

		private static MenuButton[] getPauseMenuButtons() {
			if (pauseMenuButtons==null){
				MenuButton buttons[] = new MenuButton[5];
				
				buttons[0]= new MenuButton("Settings");
				buttons[0].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showSettings();
					}
				});
				
				buttons[1]= new MenuButton("Save / Load");
				buttons[1].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showSaveLoadGame();
					}
				});
				
				buttons[2]= new MenuButton("Main Menu");
				buttons[2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showMainMenu();
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
				pauseMenuButtons=buttons;
			}
			return pauseMenuButtons;
		}

	}

}
