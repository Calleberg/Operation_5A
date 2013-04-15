package base;

import view.GamePanel;
import view.Window;
import model.GameModel;
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
	}
}
