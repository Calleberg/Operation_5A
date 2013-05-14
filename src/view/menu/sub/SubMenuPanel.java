package view.menu.sub;

import javax.swing.JPanel;

import view.menu.MenuButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;

@SuppressWarnings("serial")
public class SubMenuPanel extends JPanel{
	
	public SubMenuPanel(MenuButton b, JPanel p){
		add(b);
		add(p);
		setLayout(new MigLayout("", "[][]", "[][][]"));
		
		add(p, "cell 0 0 2 2,growx");
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, "cell 0 2");
		
		add(b, "cell 1 2,growx");
		//TODO
		
	}

}
