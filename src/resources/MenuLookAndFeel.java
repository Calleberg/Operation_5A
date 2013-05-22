package resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This class holds the look and feel of the menu. One change herein should result in all menu components adopting it.
 * @author Vidar Eriksson
 *
 */
public class MenuLookAndFeel {

	/**
	 * 
	 * @return the gap between all components.
	 */
	public static int getGap() {
		// TODO Auto-generated method stub
		return 10;
	}
	/**
	 * 
	 * @return the color of the sub menu.
	 */
	public static Color getSubMenuColor() {
		// TODO Auto-generated method stub
		return Color.ORANGE;
	}
	/**
	 * 
	 * @return the color of the sub menu's content panel.
	 */
	public static Color getSubMenuPanelColor() {
		// TODO Auto-generated method stub
		return Color.PINK;
	}
	/**
	 * 
	 * @return the color which all high level menu have.
	 */
	public static Color getMenuColor() {
		// TODO Auto-generated method stub
		return Color.GREEN;
	}
	/**
	 * 
	 * @return the color for the systems buttons.
	 */
	public static Color getButtonColor() {
		// TODO Auto-generated method stub
		return Color.YELLOW;
	}
	/**
	 * 
	 * @return the large font the menu uses.
	 */
	public static Font getLargeFont() {
		return new Font("Garamond", Font.BOLD, 48);
	}
	/**
	 * 
	 * @return the font used by the buttons in the menu.
	 */
	public static Font getButtonFont() {
		// TODO Auto-generated method stub
		return getLargeFont();
	}
	/**
	 * 
	 * @return the border the buttons should have in the menu.
	 */
	public static Border getButtonBorder() {
		// TODO Auto-generated method stub
		return new LineBorder(getButtonColor(), 5, false);
	}
	/**
	 * 
	 * @return the border a highlighted button will have.
	 */
	public static Border getButtonHighlightedBorder() {
		// TODO Auto-generated method stub
		return new LineBorder(Color.RED, 5, false);
	}
	/**
	 * 
	 * @return the color the menu popups will have.
	 */
	public static Color getPopupColor() {
		// TODO Auto-generated method stub
		return Color.red;
	}
	/**
	 * 
	 * @return the icon the popup window will have.
	 */
	public static Image setPopupMenuIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @return the border the popup window will have.
	 */
	public static Border getPopupBorder() {
		// TODO Auto-generated method stub
		return new LineBorder(Color.black, 2, false);
	}
	/**
	 * 
	 * @return the border used for text fields in settings.
	 */
	public static LineBorder getSettingsTextFieldFont() {
		// TODO Auto-generated method stub
		return new LineBorder(Color.black, 2, false);
	}

}
