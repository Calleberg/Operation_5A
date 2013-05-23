package resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * This class holds the look and feel of the menu. One change herein should result in all menu components adopting it.
 * @author Vidar Eriksson
 *
 */
public class MenuLookAndFeel {

	private static final Color BACKGROUND = new Color(67,137,187);
	private static final Color BUTTON = new Color(144,180,204);
	private static final Color BACKGROUND2 = new Color(177,194,206);
	
	/**
	 * 
	 * @return the gap between all components.
	 */
	public static int getGap() {
		return 10;
	}
	/**
	 * Gives the color used by input fields.
	 * @return
	 */
	public static Color getInputFieldColor() {
		return Color.WHITE;
	}
	/**
	 * 
	 * @return the color of the sub menu.
	 */
	public static Color getSubMenuColor() {
		return BACKGROUND;
	}
	/**
	 * 
	 * @return the color of the sub menu's content panel.
	 */
	public static Color getSubMenuPanelColor() {
		return BACKGROUND2;
	}
	/**
	 * 
	 * @return the color which all high level menu have.
	 */
	public static Color getMenuColor() {
		return BACKGROUND;
	}
	/**
	 * 
	 * @return the color for the systems buttons.
	 */
	public static Color getButtonColor() {
		return BUTTON;
	}
	/**
	 * 
	 * @return the large font the menu uses.
	 */
	public static Font getLargeFont() {
		return new Font(Font.SANS_SERIF, Font.BOLD, 48);
	}
	/**
	 * 
	 * @return the font used by the buttons in the menu.
	 */
	public static Font getButtonFont() {
		return getLargeFont();
	}
	/**
	 * 
	 * @return the border the buttons should have in the menu.
	 */
	public static Border getButtonBorder() {
		Border border = new LineBorder(getButtonColor(), 5, false);
	    return new CompoundBorder(border, getMargin());
	}
	/**
	 * 
	 * @return the border a highlighted button will have.
	 */
	public static Border getButtonHighlightedBorder() {
		Border border = new LineBorder(Color.RED, 5, false);
	    return new CompoundBorder(border, getMargin());
	}
	private static Border getMargin() {
		return new EmptyBorder(0,40,0,40);
	}
	/**
	 * 
	 * @return the color the menu popups will have.
	 */
	public static Color getPopupColor() {
		return Color.red;
	}
	/**
	 * 
	 * @return the icon the popup window will have.
	 */
	public static Image setPopupMenuIcon() {
		return null;
	}
	/**
	 * 
	 * @return the border the popup window will have.
	 */
	public static Border getPopupBorder() {
		return new LineBorder(Color.black, 2, false);
	}
	/**
	 * 
	 * @return the border used for text fields in settings.
	 */
	public static LineBorder getSettingsTextFieldFont() {
		return new LineBorder(Color.black, 2, false);
	}

}
