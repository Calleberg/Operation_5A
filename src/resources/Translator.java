package resources;


import java.util.ResourceBundle;

import model.save.SettingsModel;

public class Translator {
	public static String getString(String s){
		ResourceBundle bundle = ResourceBundle.getBundle("bundle/Text", SettingsModel.getLocale());
		return bundle.getString(s);
	}

}
