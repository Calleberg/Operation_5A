package world;

import java.util.List;
import java.util.ArrayList;

import sprite.Sprite;

public class World {

	private Tile[][] tiles;
	private List<Sprite> sprites = new ArrayList<Sprite>();

	/**
	 * Creates a new empty world.
	 */
	public World() {
		this(null);
	}
	
	/**
	 * Creates a new world with the specified tiles.
	 * @param tiles the tiles to use on this world.
	 */
	public World(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	/**
	 * Gives all the tiles of this world.
	 * @return all the tiles of this world.
	 */
	public Tile[][] getTiles() {
		return this.tiles;
	}
	
	/**
	 * Sets the tiles of this world.
	 * @param tiles the new tiles to use.
	 */
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	/**
	 * Adds the specified sprite to the world.
	 * @param sprite the sprite to add.
	 */
	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
	}
	
	/**
	 * Removes the specified sprite from the world.
	 * @param sprite the sprite to remove.
	 */
	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}
	
	/**
	 * Gives all the sprites in this world.
	 * @return all the sprites in this world.
	 */
	public List<Sprite> getSprites() {
		return this.sprites;
	}
	
	/**
	 * Updates the world.
	 */
	public void update() {
		for(int i = 0; i < sprites.size(); i++) {
			sprites.get(i).move();
		}
	}
}
