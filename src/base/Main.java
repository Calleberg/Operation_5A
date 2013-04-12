package base;

import view.GamePanel;
import view.Window;
import model.GameModel;

public class Main {

	public static void main(String[] args) {
		Input input = new Input();
		GameModel model = new GameModel();
		GameController controller = new GameController(model, input);
		Window window = new Window();
		GamePanel panel = new GamePanel(model, controller);
		input.setContainer(panel);
		model.addListener(panel);
		window.add(panel);
		panel.requestFocus();
	}
}
