package view.menu.subMenuPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.menu.MenuButton;
import view.menu.MenuLabel;
/**
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public abstract class SubMenuPanel extends JPanel{
	
	/**
	 * Creates a sub menu panel.
	 * @param s the title of the sub menu.
	 * @param p the content to show in the sub menu.
	 * @param b the buttons to add to the sub menu.
	 */
	public SubMenuPanel(String s, JPanel p, MenuButton[] b){
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(resources.MenuLookAndFeel.getSubMenuColor());
		add(textFieldPanel, BorderLayout.NORTH);
		textFieldPanel.add(new MenuLabel(s));

		JScrollPane pane = new JScrollPane();
		p.setBackground(resources.MenuLookAndFeel.getSubMenuPanelColor());
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(resources.MenuLookAndFeel.getPopupBorder());
		pane.setBackground(resources.MenuLookAndFeel.getSubMenuPanelColor());
		pane.getVerticalScrollBar().setUnitIncrement(16);
		JPanel paneContent = new JPanel();
		paneContent.setLayout(new FlowLayout());
		pane.setViewportView(paneContent);
		paneContent.add(p);
		paneContent.setBackground(resources.MenuLookAndFeel.getSubMenuPanelColor());
		add(pane, BorderLayout.CENTER);


		JPanel buttonFieldPanel = new JPanel();
		buttonFieldPanel.setBackground(resources.MenuLookAndFeel.getSubMenuColor());
		add(buttonFieldPanel, BorderLayout.SOUTH);
		buttonFieldPanel.setLayout(new GridLayout(1, 0, resources.MenuLookAndFeel.getGap(), resources.MenuLookAndFeel.getGap()));

		JPanel buttonFieldPanelLeft = new JPanel();
		buttonFieldPanelLeft.setBackground(resources.MenuLookAndFeel.getSubMenuColor());
		buttonFieldPanel.add(buttonFieldPanelLeft);
		buttonFieldPanelLeft.setLayout(new GridLayout(1, 0, resources.MenuLookAndFeel.getGap(), resources.MenuLookAndFeel.getGap()));
		for (int a=0; a<b.length-1; a++){
			buttonFieldPanelLeft.add(b[a]);
		}
		buttonFieldPanel.add(b[b.length-1], BorderLayout.SOUTH);
	}
	/**
	 * Creates a sub menu panel.
	 * @param s the title of the sub menu.
	 * @param p the content to show in the sub menu.
	 * @param b the button to add to the sub menu.
	 */
	public SubMenuPanel(String s, JPanel p, MenuButton b){
		this(s, p, new MenuButton[]{b});
	}
}
