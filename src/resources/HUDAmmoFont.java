package resources;

import java.awt.Font;
/**
 * A class that creates and returns a font for the Ammo text in the HUD
 * @author Jonatan
 *
 */
public class HUDAmmoFont {
	/**
	 * returns the font
	 * @return the font
	 */
	public static Font getFont(){
		return new Font("Garamond", Font.PLAIN, 48);
	}
}
