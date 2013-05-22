package resources;


import java.util.ResourceBundle;

import model.save.SettingsModel;

/**
 * This class accesses the games language properties and returns the preferred language for the games.
 * @author Vidar Eriksson
 *
 */
public class Translator {
	/**
	 * 
	 * @param key the key to fetch as the actual language.
	 * @return the actual language for the key.
	 */
	public static String getMenuString(String key){
		return getString("bundle/Text", key);
	}
	/**
	 * 
	 * @param key the key to fetch as the actual language.
	 * @return the actual language for the key.
	 */
	public static String getWeaponString(String key) {
		return getString("bundle/WeaponNames", key);
	}
	/**
	 * 
	 * @param key the key to fetch as the actual language.
	 * @return the actual language for the key.
	 */
	public static String getPanelSring(String key) {
		return getString("bundle/GamePanels", key);
	}
	
	/*
	 * Loads the bundle at the specified path and returns the String with the specified key.
	 */
	private static String getString(String path, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(path, SettingsModel.getLocale());
		return bundle.getString(key);
	}

}
