package resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * 
 * @author Vidar Eriksson
 *
 */
public class Menu {

	public static int getGap() {
		// TODO Auto-generated method stub
		return 10;
	}

	public static Color getSubMenuColor() {
		// TODO Auto-generated method stub
		return Color.ORANGE;
	}

	public static Color getSubMenuPanelColor() {
		// TODO Auto-generated method stub
		return Color.PINK;
	}

	public static Color getMenuColor() {
		// TODO Auto-generated method stub
		return Color.GREEN;
	}
	
	public static Color getButtonColor() {
		// TODO Auto-generated method stub
		return Color.YELLOW;
	}
	
	public static Font getLargeFont() {
		return new Font("Garamond", Font.BOLD, 48);
	}

	public static Font getButtonFont() {
		// TODO Auto-generated method stub
		return getLargeFont();
	}

	public static Border getButtonBorder() {
		// TODO Auto-generated method stub
		return new LineBorder(getButtonColor(), 5, false);
	}

	public static Border getButtonHighlightedBorder() {
		// TODO Auto-generated method stub
		return new LineBorder(Color.RED, 5, false);
	}

	public static Color getPopupColor() {
		// TODO Auto-generated method stub
		return Color.red;
	}

	public static Image setPopupMenuIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Border getPopupBorder() {
		// TODO Auto-generated method stub
		return new LineBorder(Color.black, 2, false);
	}

}
