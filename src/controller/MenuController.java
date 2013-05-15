package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.HighScoreModel;

import view.Window;
import view.menu.LoadingPanel;
import view.menu.MainMenuPanel;
import view.menu.MenuButton;
import view.menu.PauseMenuPanel;
import view.menu.subMenuPanels.HighScore;
import view.menu.subMenuPanels.SaveLoadGame;
import view.menu.subMenuPanels.Settings;

/**
 * The controller which is responsible for managing the program window and it's menus and the sub menus.
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
		WINDOW.add(new MainMenuPanel("Main Menu", MenuButtons.getMainMenuButtons()));
	}
	private static void startNewGame(){
		WINDOW.add(new LoadingPanel());
		
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
		WINDOW.add(new HighScore(MenuButtons.getMainMenuButton()));
	}
	private static void showSettingsFromMainMenu(){
		WINDOW.add(new Settings(MenuButtons.getMainMenuButton()));
	}
	private static void showSettingsFromPauseMenu(){
		WINDOW.add(new Settings(MenuButtons.getPauseMenuButton()));
	}
	private static void showSaveLoadGame(){
		WINDOW.add(new SaveLoadGame(MenuButtons.getPauseMenuButton(), true));
	}
	private static void showLoadSavedGame(){
		WINDOW.add(new SaveLoadGame(MenuButtons.getMainMenuButton(),false));
	}
	/**
	 * 
	 * @param the time the game has been played. Corresponds to the highscore.
	 */
	public static void showGameOverPanel(){
		WINDOW.add(new HighScore(MenuButtons.getMainMenuButton()));
	}
	/**
	 * Changes the program window to the paused game menu.
	 */
	public static void showPauseMenu(){
		WINDOW.add(new PauseMenuPanel("PAUSE", MenuButtons.getPauseMenuButtons(), gameController.getGamePanel()));
	}
	
	private static void resumeGame() {
		WINDOW.add(gameController.getGamePanel());
		gameController.resumeThread();
	}
	
	
	private static class MenuButtons {
		private static MenuButton mainMenuButtons[] = null;
		private static MenuButton pauseMenuButtons[] = null;
		private static MenuButton toMainMenuButton = null;
		private static MenuButton toPauseMenuButton = null;
		
		private static MenuButton getMainMenuButton() {
			if (toMainMenuButton == null){
				toMainMenuButton = new MenuButton("Main Menu");
				toMainMenuButton .addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showMainMenu();
					}
				});
			}
			return toMainMenuButton;
		}
		
		private static MenuButton getPauseMenuButton() {
			if (toPauseMenuButton == null){
				toPauseMenuButton = new MenuButton("Back to Menu");
				toPauseMenuButton .addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showPauseMenu();
					}
				});
			}
			return toPauseMenuButton;
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
						showSettingsFromMainMenu();
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
						showSettingsFromPauseMenu();
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
						int optionButton = JOptionPane.YES_NO_OPTION;
						optionButton = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", 
								"Confirm", optionButton);
						if(optionButton == JOptionPane.YES_OPTION){
							showMainMenu();
						}else{
							showPauseMenu();
						}
					}
				});
				
				buttons[3]= new MenuButton("Exit Game");
				buttons[3].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int optionButton = JOptionPane.YES_NO_OPTION;
						optionButton = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", 
								"Confirm", optionButton);
						if(optionButton == JOptionPane.YES_OPTION){
							exitGame();
						}else{
							showPauseMenu();
						}
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
