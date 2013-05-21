package resources;

import inputOutput.SettingsModel;

import java.util.ResourceBundle;

public class Translator {
	public static String getString(String s){
		ResourceBundle bundle = ResourceBundle.getBundle("bundle/Text", SettingsModel.getLocale());
		return bundle.getString(s);
	}

}
