package base;

import view.GamePanel;
import view.Window;
import model.GameModel;

public class Main {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameController controller = new GameController(model);
		Window window = new Window();
		GamePanel panel = new GamePanel(model);
		model.addListener(panel);
		window.add(panel);
	}
}
