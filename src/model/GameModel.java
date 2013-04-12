package model;

public class GameModel {
	private final World world;
	private final Player player;
	public GameModel(){
		world = new World();
		world.addSprite(player);
		
	}
}
