package resources;

import java.awt.Font;
/**
 * A class that creates and returns a font for the HUD
 * @author Jonatan
 *
 */
public class HUDFonts {
	/**
	 * returns the font for the Ammo
	 * @return the font for the Ammo
	 */
	public static Font getAmmoFont(){
		return new Font("Garamond", Font.PLAIN, 32);
	}
	
	/**
	 * Returns the font for the score
	 * @return the font for the score
	 */
	public static Font getScoreFont(){
		return new Font("Garamond", Font.PLAIN, 24);
	}
}
