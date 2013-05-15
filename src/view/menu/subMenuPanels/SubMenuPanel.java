package view.menu.subMenuPanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import view.menu.MenuButton;
import view.menu.MenuLabel;
/**
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public abstract class SubMenuPanel extends JPanel{
	
	public SubMenuPanel(String s, JPanel p, MenuButton[] b){
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(resources.Menu.getSubMenuColor());
		add(textFieldPanel, BorderLayout.NORTH);
			textFieldPanel.add(new MenuLabel(s));
		
		JPanel centerPanel = new JPanel();
		centerPanel.add(p);
		centerPanel.setBackground(resources.Menu.getSubMenuPanelColor());
		add(centerPanel, BorderLayout.CENTER);
		p.setBackground(resources.Menu.getSubMenuPanelColor());
		
		JPanel buttonFieldPanel = new JPanel();
		buttonFieldPanel.setBackground(resources.Menu.getSubMenuColor());
		add(buttonFieldPanel, BorderLayout.SOUTH);
		buttonFieldPanel.setLayout(new GridLayout(1, 0, resources.Menu.getGap(), resources.Menu.getGap()));
		
		JPanel buttonFieldPanelLeft = new JPanel();
		buttonFieldPanelLeft.setBackground(resources.Menu.getSubMenuColor());
		buttonFieldPanel.add(buttonFieldPanelLeft);
		buttonFieldPanelLeft.setLayout(new GridLayout(1, 0, resources.Menu.getGap(), resources.Menu.getGap()));
		for (int a=0; a<b.length-1; a++){
			buttonFieldPanelLeft.add(b[a]);
		}
		buttonFieldPanel.add(b[b.length-1], BorderLayout.SOUTH);	
	}
	public SubMenuPanel(String s, JPanel p, MenuButton b){
		this(s, p, new MenuButton[]{b});
	}
}
