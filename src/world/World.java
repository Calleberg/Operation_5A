package world;

import java.util.List;
import java.util.ArrayList;

import sprite.Player;

public class World {

	private List<Player> sprites = new ArrayList<Player>();

	public void addSprite(Player sprite) {
		this.sprites.add(sprite);
	}
	
	public List<Player> getSprites() {
		return this.sprites;
	}
}
