package resources;


import java.util.ResourceBundle;

import model.save.SettingsModel;

//TODO: Vidar ska skriva javadocad
public class Translator {
	
	public static String getMenuString(String key){
		return getString("bundle/Text", key);
	}
	
	public static String getWeaponString(String key) {
		return getString("bundle/WeaponNames", key);
	}
	
	public static String getPanelSring(String key) {
		return getString("bundle/GamePanels", key);
	}
	
	private static String getString(String path, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(path, SettingsModel.getLocale());
		return bundle.getString(key);
	}

}
