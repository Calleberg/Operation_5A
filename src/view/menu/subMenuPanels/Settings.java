package view.menu.subMenuPanels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resources.MenuLookAndFeel;

import model.SettingsModel;

import view.menu.MenuButton;
import view.menu.MenuLabel;

/**
 * 
 * @author Vidar Eriksson
 *
 */
@SuppressWarnings("serial")
public class Settings extends SubMenuPanel {
	private static JTextField nameField = new JTextField();
	private static SettingsModel.Language language = SettingsModel.getLanguage();
	private static JCheckBox fullscreen = new JCheckBox("On");
	
	public Settings(MenuButton button) {
		super(resources.Language.getSettingsText(), getPanel(), new MenuButton[]{getSaveButton(), button});
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSettings();				
			}
		});
	}

	private static MenuButton getSaveButton() {
		MenuButton b = new MenuButton("Save");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSettings();
			}
		});
		return b;
	}

	private static JPanel getPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();		
		
		p.setLayout(new GridLayout(0, 2, resources.MenuLookAndFeel.getGap(), resources.MenuLookAndFeel.getGap()));
		
		p.add(new MenuLabel("Player Name:"));
		p.add(nameField);
		nameField.setFont(resources.MenuLookAndFeel.getLargeFont());
		nameField.setText(SettingsModel.getUserName());
		nameField.setBackground(MenuLookAndFeel.getSubMenuPanelColor());
		nameField.setBorder(MenuLookAndFeel.getSettingsTextFieldFont());
		
		
		
		p.add(new MenuLabel("Language:"));
		p.add(new MenuLabel("No choises yet..."));
		
		
		p.add(new MenuLabel("Fullscreen:"));
		p.add(fullscreen);
		fullscreen.setBackground(MenuLookAndFeel.getSubMenuPanelColor());
		fullscreen.setFont(resources.MenuLookAndFeel.getLargeFont());
		fullscreen.setSelected(SettingsModel.getFullscreen());
		

		return p;
	}
	
	private static void saveSettings(){
		SettingsModel.setUserName(nameField.getText());
		SettingsModel.setFullscreen(fullscreen.isSelected());
		SettingsModel.setLanguage(language);
		SettingsModel.save();
	}

}
