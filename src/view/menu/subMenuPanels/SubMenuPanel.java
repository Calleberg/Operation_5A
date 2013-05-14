package view.menu.subMenuPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import view.menu.MenuButton;
import view.menu.MenuLabel;

@SuppressWarnings("serial")
public class SubMenuPanel extends JPanel{
	
	public SubMenuPanel(String[] s, JPanel p, MenuButton[] b){
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(resources.Menu.getSubMenuColor());
		add(textFieldPanel, BorderLayout.NORTH);
		textFieldPanel.setLayout(new GridLayout(1, 0));
		
		
		
		textFieldPanel.setAlignmentY(CENTER_ALIGNMENT);
		//TODO
		
		
		
		for (int a=0; a<s.length; a++){
			textFieldPanel.add(new MenuLabel(s[a]));
		}
		
		add(p, BorderLayout.CENTER);
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

		//TODO
		
	}
	
	public SubMenuPanel(String s, JPanel p, MenuButton[] b){
		this(new String[]{s}, p, b);
	}
	public SubMenuPanel(String s, JPanel p, MenuButton b){
		this(new String[]{s}, p, new MenuButton[]{b});
	}
	public SubMenuPanel(String[] s, JPanel p, MenuButton b){
		this(s, p, new MenuButton[]{b});
	}
	
	@Override
	public void setBackground(Color c){
		
	}
}
