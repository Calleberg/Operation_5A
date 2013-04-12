package model;

import sprite.Player;
import world.World;

public class GameModel {
	
	private final World world;
	private final Player player;
	
	public GameModel(){
		world = new World();
		player = new Player(1,1);
		world.addSprite(player);
		
	}
}
