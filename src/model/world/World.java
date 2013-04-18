package model.world;

import java.util.List;
import java.util.ArrayList;

import model.items.weapons.Projectile;
import model.sprites.Sprite;




public class World {

	private Tile[][] tiles;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();

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
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).move();
		}
		
		//TODO fixa...
		for(int i = 0; i < sprites.size(); i++){
			for(int j = 0; j < sprites.size(); j++) {
				if(i != j && sprites.get(i).getCollisionBox().intersects(sprites.get(j).getCollisionBox())) {
					System.out.println("Collision");
				}
			}
		}
	}
	
	/**
	 * Adds the specified projectile to the world.
	 * @param projectile the projectile to add.
	 */
	public void addProjectile(Projectile projectile) {
		this.projectiles.add(projectile);
	}
	
	/**
	 * Removes the specified projectile from the world.
	 * @param projectile the projectile to remove.
	 */
	public void removeProjectile(Projectile projectile) {
		this.projectiles.remove(projectile);
	}
	
	/**
	 * Gives all the projectiles in the world.
	 * @return all the projectiles in the world.
	 */
	public List<Projectile> getProjectiles() {
		return this.projectiles;
	}
}
