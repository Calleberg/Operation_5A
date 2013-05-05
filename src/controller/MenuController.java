package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.Window;
import view.menu.MainMenu;
import view.menu.MenuButton;
import view.menu.PauseMenu;

/**
 * @author
 *
 */
public class MenuController {
	private static MenuController instance = null;
	private static final MainMenu mainMenu = new MainMenu("Main Menu", getMainMenuButtons());
	private static PauseMenu pauseMenu = null;
	private static final Window window = Window.getInstance();
	private static JPanel activeWindow = null;
	
	public enum MenuActions{
		START_GAME ("Start Game"),
		HIGHSCORE ("Highscore"),
		SETTINGS ("Settings"),
		EXIT_GAME ("Exit Game"),
		START_LOAD ("Start / Load"),
		MAIN_MENU ("Main Menu"),
		RESUME ("Resume");
		private String s;
		MenuActions(String s){
			this.s=s;
		}
		@Override
		public String toString(){
			return s;
		}
		public void doProperAction(){
			switch(this){
				case START_GAME:
					startGame();
					break;
				case HIGHSCORE:
					highscore();
					break;
				case SETTINGS:
					settings();
					break;
				case EXIT_GAME:
					exitGame();
					break;
				case START_LOAD:
					saveLoad();
					break;
				case MAIN_MENU:
					mainMenu();
					break;
				case RESUME:
					resume();
					break;
				default:
					break;
			}
					
		}

	}

	private MenuController() {
		mainMenu();
	}

	private static MenuButton[] getMainMenuButtons() {
		MenuButton buttons[] = new MenuButton[4];
		
		buttons[0]= new MenuButton("New Game");
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		buttons[1]= new MenuButton("Highscore");
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				highscore();
			}
		});
		
		buttons[2]= new MenuButton("Settings");
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		});
		
		buttons[3]= new MenuButton("Exit Game");
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
		
		return buttons;
	}

	public static MenuController getInstance() {
		if (instance==null){
			instance = new MenuController();
		}
		return instance;
	}
	
	public static void startGame(){
		//TODO
		TestBordeLiggaIEnController.startGame();
	}
	public static void exitGame(){
		System.out.println("Game Terminated sucsessfully");
		System.exit(0);
	}
	public static void highscore(){
		//TODO
	}
	public static void settings(){
		//TODO
	}
	public static void saveLoad(){
		//TODO
	}
	public static void returnToGame(){
		//TODO
	}
	public static void mainMenu(){
		//TODO
		changeWindowWiewTo(mainMenu);
	}
	public static void pauseMenu(){
		//TODO
		changeWindowWiewTo(pauseMenu);
	}
	private static void changeWindowWiewTo(JPanel p){
		if (activeWindow != null){
			window.remove(activeWindow);
		}
		window.add(p);
		activeWindow=p;
		window.revalidate();
	}
	private static void resume() {
		// TODO Auto-generated method stub
		
	}
}
