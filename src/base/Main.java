package base;

import controller.GameController;
import view.GamePanel;
import view.Window;
import model.GameModel;
import model.world.WorldBuilder;
//linus
public class Main {

	public static void main(String[] args) {
		Input input = new Input();
		GameModel model = new GameModel();
		//Test
		GameController controller = new GameController(model, input);
		Window window = new Window();
		//Test
		GamePanel panel = new GamePanel(model, controller);
		input.setContainer(panel);
		model.addListener(panel);
		window.add(panel);
		//Test
		panel.requestFocus();
		window.validate();
		
		WorldBuilder wb = new WorldBuilder();
		model.world.Tile[][] tiles = WorldBuilder.getEmptyWorld(10, 10);
		wb.addTiles(tiles, 0, 0, "lots/10x10_roadNorth.txt");
	}
}
