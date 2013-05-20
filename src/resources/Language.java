package resources;

import inputOutput.SettingsModel;
/**
 * Not done by far.
 * @author Vidar Eriksson
 *
 */
public class Language {
	//TODO Skall allt detta se ut så?

	
	//TODO byt plat oi ordning såp att vid fel laddas engelska
	public static String getSettingsText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Settings";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Inställningar";
		}
		return "Error langauge does not exist";
		
	}

	public static String getHighScoreText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Highscore";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Topplista";
		}
		return "Error langauge does not exist";
		
	}

	public static String getLoadGameText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Load Game";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Ladda Spel";
		}
		return "Error langauge does not exist";
		
	}

	public static String getNewGameText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "New Game";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Nytt Spel";
		}
		return "Error langauge does not exist";
		
	}

	public static String getExitGameText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Exit Game";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Avsluta Spel";
		}
		return "Error langauge does not exist";
	}

	public static String getMainMenuText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Main Menu";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Huvudmeny";
		}
		return "Error langauge does not exist";
	}

	public static String getSaveLoadGameText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Save / Load";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Spara / Ladda";
		}
		return "Error langauge does not exist";
	}

	public static String getResumeGameText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.ENGLISH){
			return "Resume";
		} else if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Ateruppta Spel";
		}
		return "Error langauge does not exist";
	}

	public static String getBackToMenuText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "Tillbaka till huvudmenyn";
		} else {
			return "Back to Main Menu";
		}
	}

	public static String getPauseText() {
		if (SettingsModel.getLanguage() == SettingsModel.Language.SWEDISH){
			return "PAUSE";
		} else {
			return "PAUSAT";
		}
	}
	
}
