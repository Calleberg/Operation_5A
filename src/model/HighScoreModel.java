package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Vidar Eriksson
 *
 */
public class HighScoreModel {
	private HighScoreWrapper h = null;
	
	public HighScoreModel(){
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
		HighScoreWrapper.addNewScore(newScore);
	}

	private static class HighScoreWrapper{
		private static String[][] s = loadHighScore();
		private static final String filePath = "/users/Highscore_Operation5A.txt";
		
		private int getLenght(){
			return s.length;
		}
	
			
		
		public static void addNewScore(long newScore) {
			writeScore(newScore);
		}



		private String getName(int pos){
			return s[pos][0];
		}
		private String getScore(int pos){
			return s[pos][1];
		}
		
		private static String[][] loadHighScore(){
			String s[][] = createScoreList(readScore());
			if (s != null && s[0][0]!=null && s[0][1]!=null){
				return s;
			} else {
				return new String[][] {
						{"Fel", "ERROR"}};
			}
		}
		private static String readScore() {
			String temp = "";
		try {
				// TODO Auto-generated method stub
				File file = new File(filePath);
				//TODO bättre save path
				
				if (!file.exists()) {
					file.createNewFile();
				}
				
				FileReader fileReader = new FileReader(file.getAbsoluteFile());
				BufferedReader bufferedReader = new BufferedReader(fileReader);
//				bufferedReader.write("Random" + "#" + newScore + "#");
				temp=bufferedReader.readLine();
				bufferedReader.close();	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println("Här är read score: "+temp);
		if (temp==null){
			temp="";
		}
		return temp;
			
		}
		private static String[][] createScoreList(String string){
			int savedScores = numbersOfSymbols(string, "##");
			
			if (savedScores<1){
				System.out.println("no saved scores");
				return new String[][]{{"no saved scores","no saved name"}};
			} else {
				String s[][] = new String[savedScores][2];
				
				for (int a=0; a < savedScores; a++){
					for (int b=0; b < 2; b++){
						String indexThingie="#";
						if(b==1){
							indexThingie+="#";
						}
						s[a][b]=string.substring(0, string.indexOf(indexThingie));
						string = string.substring(string.indexOf(indexThingie)+indexThingie.length(), string.length());
					}
				}			
				return s;
			}
		}
		
		private static int numbersOfSymbols(String s, String symbol){
			int number = 0;
			
				while (s.indexOf(symbol)>0){
					number++;
					s=s.substring(s.indexOf(symbol)+1, s.length());
				}			
			return number;
		}
		
		private static void writeScore(long newScore) {
			try {
				 
				String content = "No name entered" +"#"+newScore+"##";
				System.out.println(content);
				
				
				for (int a=0; a < s.length; a++ ){//TODO här kan det blifel om s[0].length är fleplacera, byt eventuellt plats
					for (int b=0; b < s[0].length; b++ ){
						if (b==0){
							content += s[a][b]+"#";
						} else {
							content += s[a][b]+"##";
						}
					}
				}
				
				
				File file = new File(filePath);
	 
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(content);
				bufferedWriter.close();
	 
				System.out.println("Saved new Score: " + content);
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
		}
		
		private static String to1DString(String s[][]){
			String content = "";
			for (int a=0; a < s.length; a++ ){//TODO här kan det blifel om s[0].length är fleplacera, byt eventuellt plats
				for (int b=0; b < s[0].length; b++ ){
					content += s[a][b];
				}
			}
			return content;
			
			
		}
	

	}
}
