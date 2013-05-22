package model.save;

public class SavePath {
	private static String getSavePath(){
		//TODO var sparas?
		return System.getenv("APPDATA") + "/";
	}
	
	public static  String settings(){
		return getSavePath() + "Settings.txt";
	}
	
	public static String highScore(){
		return getSavePath() + "HighScore.txt";
	}
	
	public static  String savedGame(){
		return getSavePath() + "SavedGame.txt";
	}
}
