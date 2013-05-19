package model;

/**
 * 
 * @author Vidar Eriksson
 *
 */
public class SettingsModel {
	/**
	 * 
	 * @return the in game language.
	 */
	public static Language getLanguage(){
		return SettingsWrapper.getLanguage();
	}
	
	public static class SettingsWrapper{

		private static  void update(){
			//TODO
		}
		public static Language getLanguage() {
			update();
			// TODO Auto-generated method stub
			return Language.ENGLISH;
		}
		
	}
	
	
	public static enum Language{
		ENGLISH,
		SWEDISH;
	}


	public static void setUserName(String text) {
		// TODO Auto-generated method stub
		
	}




	public static void setFullscreen(boolean b) {
		// TODO Auto-generated method stub
		
	}


	public static void setLanguage(Language language) {
		// TODO Auto-generated method stub
		
	}


	public static boolean getFullscreen() {
		// TODO Auto-generated method stub
		return false;
	}




	public static String getUserName() {
		// TODO Auto-generated method stub
		return "No name on player";
	}


	public static void save() {
		// TODO Auto-generated method stub
		
	}
}
