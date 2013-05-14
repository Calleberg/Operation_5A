package model;
/**
 * 
 * @author Vidar
 *
 */
public class HighScore {
	public HighScoreWrapper getHighScore(){
		return new HighScoreWrapper();
		
	}
	
	public class HighScoreWrapper{
		private String[][] s =createHighScore();
		
		public int getLenght(){
			return s.length;
		}
		public String getName(int pos){
			return s[pos][0];
		}
		public String getScore(int pos){
			return s[pos][1];
		}
		
		private String[][] createHighScore(){
			return  new String[][] {
					{"Vidare", "1337"}, {"Linus ", "150"},
					{"Vidare", "1337"}, {"Linus ", "150"},
					{"Vidare", "1337"}, {"Linus ", "10"},
					{"Vidare", "1337"}, {"Linus ", "1150"},
					{"Vidare", "1337"}, {"Linus ", "2350"},
					{"Vidare", "1337"}, {"Linus ", "0"}
					};
		}

	}

	public static void addScore(long totalRuntime) {
		// TODO Auto-generated method stub
		
	}
}
