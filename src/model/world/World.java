package model.world;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;

import model.GameModel;
import model.items.weapons.Projectile;
import model.sprites.Sprite;

/**
 * Hold all the objects that populates the world.
 * 
 * @author Calleberg
 *
 */
public class World {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
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
	 * Adds the specified listener.
	 * @param pcl the listener to add.
	 */
	public void addListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Removes the specified listener.
	 * @param pcl the listner to remove.
	 */
	public void removeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
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
		this.pcs.firePropertyChange(GameModel.ADDED_SPRITE, null, sprite);
	}
	
	/**
	 * Removes the specified sprite from the world.
	 * @param sprite the sprite to remove.
	 */
	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
		this.pcs.firePropertyChange(GameModel.REMOVED_SPRITE, sprite, null);
	}
	
	/**
	 * Removes the sprite specified in the list from the sprites in the world.
	 * @param sprites the sprites to remove.
	 */
	public void removeSprites(List<Sprite> sprites) {
		for(Sprite s : sprites) {
			this.removeSprite(s);
		}
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
//		for(int i = 0; i < projectiles.size(); i++) {
//			projectiles.get(i).move();
//		}
		
		List<Sprite> spritesToBeRemoved = new ArrayList<Sprite>();
		List<Projectile> projectilesToBeRemoved = new ArrayList<Projectile>();
		//TODO: fixa till, inte s�rskilt bra just nu...
		for(int i = 0; i < sprites.size(); i++){
			for(int j = 0; j < sprites.size(); j++) {
				if(i != j && sprites.get(i).getCollisionBox().intersects(sprites.get(j).getCollisionBox())) {
					System.out.println("Collision");
					System.out.println("1=" + sprites.get(i).getCollisionBox());
					System.out.println("2=" + sprites.get(j).getCollisionBox());
				}
			}
		}
		for(int k = 0; k < 5; k++) {
			for(int j = 0; j < projectiles.size(); j++) {
				projectiles.get(j).move();
				for(int i = 0; i < sprites.size(); i++){
					if(sprites.get(i).getCollisionBox().intersects(projectiles.get(j).getCollisionBox())) {
						System.out.println("projectile collision");
						sprites.get(i).reduceHealth(projectiles.get(j).getDamage());
						projectilesToBeRemoved.add(projectiles.get(j));
						if(sprites.get(i).getHealth() <= 0){
							//sprites.get(i) = player
							if(i == 0){
								System.out.println("Game Over");
							}
							spritesToBeRemoved.add(sprites.get(i));
						}
					}
				}
			}
		}
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isMaxRangeReached()){
				projectilesToBeRemoved.add(projectiles.get(i));
			}
		}
		
		this.removeSprites(spritesToBeRemoved);
		this.removeProjectiles(projectilesToBeRemoved);
	}
	
	/**
	 * Adds the specified projectile to the world.
	 * @param projectile the projectile to add.
	 */
	public void addProjectile(Projectile projectile) {
		if (projectile!=null){
			this.projectiles.add(projectile);
			this.pcs.firePropertyChange(GameModel.ADDED_PROJECTILE, null, projectile);
		}
	}
	
	/**
	 * Removes the specified projectile from the world.
	 * @param projectile the projectile to remove.
	 */
	public void removeProjectile(Projectile projectile) {
		this.projectiles.remove(projectile);
		this.pcs.firePropertyChange(GameModel.REMOVED_PROJECTILE, projectile, null);
	}
	
	/**
	 * Removes the specified projectiles from the world.
	 * @param projectiles the projectiles to remove.
	 */
	public void removeProjectiles(List<Projectile> projectiles) {
		for(Projectile p : projectiles) {
			this.removeProjectile(p);
		}
	}
	
	/**
	 * Gives all the projectiles in the world.
	 * NOTE: If you want to remove projectile(s), use the methods
	 * declared in this class.
	 * @return all the projectiles in the world.
	 */
	public List<Projectile> getProjectiles() {
		return this.projectiles;
	}
}
