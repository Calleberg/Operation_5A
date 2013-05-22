package controller;


import inputOutput.GameIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameModel;
import model.MainModel;
import model.save.SavePath;
import resources.Translator;
import view.Window;
import view.menu.LoadingPanel;
import view.menu.MenuButton;
import view.menu.MenuPanel;
import view.menu.subMenuPanels.SaveLoadGame;
import view.menu.subMenuPanels.Score;
import view.menu.subMenuPanels.Settings;

/**
 * The controller which is responsible for managing the program's window and it's menus.
 * 
 * @author Vidar Eriksson
 *
 */
public class MenuController{
	private static final Window WINDOW = new Window();
	private static GameController gameController = null;
	private static MainModel mainModel = new MainModel();
	
	/**
	 * Creates a new game window and starts the main menu for the game.
	 */
	public MenuController() {
		showMainMenu();		
	}
	/**
	 * Changes this controllers window to the main menu.
	 */
	public static void showMainMenu(){
		WINDOW.add(new MenuPanel(Translator.getMenuString("mainMenu"), MenuActionButtons.getMainMenuButtons()));
	}
	private static void startGame(GameModel m){
		if (gameController != null){
			gameController.stopThread();
		}
		if (m == null){
			startNewGame();
		}
		
		WINDOW.add(new LoadingPanel());
		
		gameController = new GameController(mainModel);
		mainModel.setGameModel(m);
		gameController.init();

		WINDOW.add(gameController.getGamePanel());
		new Thread(gameController).start();
	}
	private static void startNewGame(){
		startGame(GameIO.newGame());
	}
	private static void exitGame(){
		System.exit(0);
	}
	private static void showhighscore(){
		WINDOW.add(new Score(MenuActionButtons.getMainMenuButton()));
	}
	private static void showSettingsFromMainMenu(){
		WINDOW.add(new Settings(MenuActionButtons.getMainMenuButton()));
	}
	private static void showSettingsFromPauseMenu(){
		WINDOW.add(new Settings(MenuActionButtons.getPauseMenuButton()));
	}
	private static void showSaveLoadGame(){
		WINDOW.add(new SaveLoadGame(GameIO.getSaveTime(), new MenuButton[]{MenuActionButtons.getLoadButton(),
				MenuActionButtons.getSaveButton(), MenuActionButtons.getPauseMenuButton()}, true));
	}
	private static void showLoadGame(){
		WINDOW.add(new SaveLoadGame(GameIO.getSaveTime(), new MenuButton[]{MenuActionButtons.getLoadButton(),
				MenuActionButtons.getSaveButton(), MenuActionButtons.getMainMenuButton()}, false));
	}
	/**
	 * Changes this controllers window to the game over view.
	 */
	public static void showGameOverPanel(){
		WINDOW.add(new Score(MenuActionButtons.getMainMenuButton()));
	}
	/**
	 * Changes this controllers window to the paused game menu view.
	 */
	public static void showPauseMenu(){
		WINDOW.add(new MenuPanel(Translator.getMenuString("pause"), MenuActionButtons.getPauseMenuButtons()));
	}
	private static void resumeGame() {
		WINDOW.add(gameController.getGamePanel());
		gameController.resumeThread();
	}
	
	
	private static class MenuActionButtons {

		private static MenuButton getSaveButton() {
			MenuButton b  = new MenuButton(Translator.getMenuString("save"));
			b .addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GameIO.saveGame(gameController.getGameModel(), SavePath.savedGame());
					WINDOW.repaint();
				}
			});
				return b;
		}
		
		private static MenuButton getLoadButton() {
			MenuButton b = new MenuButton(Translator.getMenuString("load"));
			b .addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startGame(GameIO.loadGame(SavePath.savedGame()));
				}
			});
			return b;
		}
		
		
		private static MenuButton getMainMenuButton() {
			MenuButton b = new MenuButton(Translator.getMenuString("mainMenu"));
			b .addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showMainMenu();
				}
			});
			return b;
		}
		
		private static MenuButton getPauseMenuButton() {
			MenuButton b = new MenuButton(Translator.getMenuString("pauseMenu"));
			b .addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showPauseMenu();
				}
			});
			return b;
		}
		
		private static MenuButton[] getMainMenuButtons() {
				
			MenuButton[] buttons = new MenuButton[5];
			
			buttons[0]= new MenuButton(Translator.getMenuString("newGame"));
			buttons[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startNewGame();
				}
			});
			
			buttons[1]= new MenuButton(Translator.getMenuString("load"));
			buttons[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showLoadGame();
				}
			});
			
			buttons[2]= new MenuButton(Translator.getMenuString("highScore"));
			buttons[2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showhighscore();
				}
			});
			
			buttons[3]= new MenuButton(Translator.getMenuString("settings"));
			buttons[3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showSettingsFromMainMenu();
				}
			});
			
			buttons[4]= new MenuButton(Translator.getMenuString("exitGame"));
			buttons[4].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exitGame();
				}
			});
		
			return buttons;
		}

		private static MenuButton[] getPauseMenuButtons() {

			MenuButton buttons[] = new MenuButton[5];
			
			buttons[0]= new MenuButton(Translator.getMenuString("settings"));
			buttons[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showSettingsFromPauseMenu();
				}
			});
			
			buttons[1]= new MenuButton(Translator.getMenuString("saveLoad"));
			buttons[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showSaveLoadGame();
				}
			});
			
			buttons[2]= new MenuButton(Translator.getMenuString("mainMenu"));
			buttons[2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showMainMenu();
				}
			});
			
			buttons[3]= new MenuButton(Translator.getMenuString("exitGame"));
			buttons[3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exitGame();
				}
			});
			
			buttons[4]= new MenuButton(Translator.getMenuString("resume"));
			buttons[4].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resumeGame();
				}
			});

			return buttons;
		}

	}

}
