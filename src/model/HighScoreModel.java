package model;
/**
 * 
 * @author Vidar Eriksson
 *
 */
public class HighScoreModel {
	private static HighScoreWrapper h = null;
	
	public HighScoreModel(){
		update();
	}
	public void update(){
		h = new HighScoreWrapper();
	}
	public int getLenght(){
		return h.getLenght();	
	}
	public String getName(int pos){
		return h.getName(pos);
	}
	public String getScore(int pos){
		return h.getScore(pos);
	}
	public static void addNewScore(long newScore){
		
	}

	private class HighScoreWrapper{
		private String[][] s =createHighScore();
		
		private int getLenght(){
			return s.length;
		}
		private String getName(int pos){
			return s[pos][0];
		}
		private String getScore(int pos){
			return s[pos][1];
		}
		
		private String[][] createHighScore(){
			return  new String[][] {
					{"Vidare", "1337"}, {"Linus ", "150"},
					{"Vidare", "1337"}, {"Linus ", "150"},
					{"Vidare", "1337"}, {"Linus ", "10"},
					{"Vidare", "1337"}, {"Linus ", "1150"},
					{"Vidare", "1337"}, {"Linus ", "350"},
					{"Vidare", "1337"}, {"Linus ", "0"}
					};
		}

	}
}
