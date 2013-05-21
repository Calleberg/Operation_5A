package model;

import inputOutput.HighScoreModel;
import inputOutput.SettingsModel;

public class MainModel {

	private HighScoreModel scoreModel;
	private SettingsModel settingsModel;

	private GameModel gameModel;

	public MainModel() {
		
	}
	public GameModel getGameModel() {
		return gameModel;
	}

	public HighScoreModel getScoreModel() {
		return scoreModel;
	}

	public SettingsModel getSettingsModel() {
		return settingsModel;
	}
	
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public void setScoreModel(HighScoreModel scoreModel) {
		this.scoreModel = scoreModel;
	}

	public void setSettingsModel(SettingsModel settingsModel) {
		this.settingsModel = settingsModel;
	}
}
