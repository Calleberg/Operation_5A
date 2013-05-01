package base;

import controller.GameController;
import view.GamePanel;
import view.Window;
import model.GameModel;
import model.items.weapons.WeaponFactory;
import model.sprites.Player;

public class Main {

	public static void main(String[] args) {
		//TODO: lägg allt detta i en annan klass
		Input input = new Input();
		GameModel model = new GameModel();
		Player player = new Player(50,50);
		player.setWeapon(WeaponFactory.createTestWeapon());
		model.setPlayer(player);
		GameController controller = new GameController(model, input);
		Window window = new Window();
		GamePanel panel = new GamePanel(model, controller);
		input.setContainer(panel);
		model.addListener(panel);
		window.add(panel);
		panel.requestFocus();
		window.validate();
				
		//Starts all the loops
		panel.start();
		controller.start();
	}
}
