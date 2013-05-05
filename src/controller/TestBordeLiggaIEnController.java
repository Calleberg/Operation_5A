package controller;

import view.GamePanel;
import view.Window;
import model.GameModel;
import model.items.weapons.WeaponFactory;
import model.sprites.Player;




public class TestBordeLiggaIEnController {
	static TestBordeLiggaIEnController instanceOfThis= null;

	private TestBordeLiggaIEnController(){
	}
	
	public static void startGame(){
		//TODO: lägg allt detta i en annan klass
		Input input = new Input();
		GameModel model = new GameModel();
		Player player = new Player(50,50);
		player.setWeapon(WeaponFactory.createTestWeapon());
		model.setPlayer(player);
		GameController controller = new GameController(model, input);
		Window window = Window.getInstance();
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

	public static TestBordeLiggaIEnController getInstance() {
		if (instanceOfThis==null){
			instanceOfThis=new TestBordeLiggaIEnController();
		}
		return instanceOfThis;
	}
}
