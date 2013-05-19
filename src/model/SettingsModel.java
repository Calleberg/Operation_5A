package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.corba.se.impl.protocol.FullServantCacheLocalCRDImpl;

import savePath.SavePath;

/**
 * 
 * @author Vidar Eriksson
 *
 */
public class SettingsModel {
	
	public static enum Language{
		ENGLISH,
		SWEDISH;
		
		public static Language loadFrom(String s){
			if (s.contains("SWE")){
				return SWEDISH;
			} else {
				return ENGLISH;
			}
		}
		public String saveString(){
			if (this == SWEDISH){
				return "SWE";
			} else {
				return "ENG";
			}
		}
	}
	
	
	/**
	 * 
	 * @return the in game language.
	 */
	public static Language getLanguage(){
		return SettingsWrapper.getLanguage();
	}
	public static void setLanguage(Language language) {
		SettingsWrapper.setLanguage(language);
	}
	public static String getUserName() {
		return SettingsWrapper.getUserName();
	}
	public static void setUserName(String text) {
		SettingsWrapper.setName(text);	
	}
	public static boolean getFullscreen() {
		return SettingsWrapper.getFullscreen();
	}
	public static void setFullscreen(boolean b) {
		SettingsWrapper.setFullscreen(b);
	}
	public static void save() {
		SettingsWrapper.write();
	}
	
	
	private static class SettingsWrapper{
		private static final String DATA_DIVIDER = "#";
		
		private static String name;
		private static Language language;
		private static boolean fullscreen;

		private static boolean getFullscreen() {
			read();
			return fullscreen;
		}
		public static String getUserName() {
			read();
			return name;
		}
		private static Language getLanguage() {
			read();
			return language;
		}
		private static void setLanguage(Language l) {
			language = l;
			write();			
		}
		private static void setFullscreen(boolean b) {
			fullscreen = b;
			write();		
		}
		private static void setName(String text) {
			name = text;
			write();			
		}

		private static void read() {
			String temp = null;
			try {
				File file = new File(SavePath.settings());
				if(file.exists()) {
					FileReader fileReader = new FileReader(file.getAbsoluteFile());
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					temp=bufferedReader.readLine();
					bufferedReader.close();
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				defaultSettings();
			}
			if (temp!=null){
				convertFromString(temp);
			} else { 
				defaultSettings();
			}
		}
		private static void defaultSettings() {
			name = "No name entered";
			fullscreen = true;
			language=Language.ENGLISH;
		}	
		private static void write() {
			try {				 			
				File file = new File(SavePath.settings());
	 
				if (!file.exists()) {
					file.createNewFile();
				}
	 
				FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(convertToSaveableString());
				bufferedWriter.close();
				fileWriter.close();
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		private static String convertToSaveableString(){
			String temp="";
			
			if (fullscreen){
				temp+=1;
			} else {
				temp+=0;
			}
			temp += DATA_DIVIDER;
			
			temp += name + DATA_DIVIDER;
			
			temp += language.saveString() + DATA_DIVIDER;

			return temp;
			
		}
		private static void convertFromString(String temp) {
			if (temp.charAt(0) == '0' ){
				fullscreen = false;
			} else {
				fullscreen = true;
			}
			
			temp = temp.substring(temp.indexOf(DATA_DIVIDER)+1);
			
			name = temp.substring(0, temp.indexOf(DATA_DIVIDER));
			temp = temp.substring(temp.indexOf(DATA_DIVIDER)+1);
			
			language = Language.loadFrom(temp.substring(0, temp.indexOf(DATA_DIVIDER)-1));
			
		}
	

	}

}
