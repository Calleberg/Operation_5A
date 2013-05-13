package model.world;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import model.GameModel;
import model.geometrical.CollisionBox;
import model.geometrical.Line;
import model.geometrical.Position;
import model.items.Item;
import model.items.weapons.Projectile;
import model.items.weapons.Weapon;
import model.sprites.Enemy;
import model.sprites.Player;
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
	private List<Tile> spawnPoints;
	private List<Item> items = new ArrayList<Item>();
	

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
		this.pcs.firePropertyChange(GameModel.REMOVED_OBJECT, sprite, null);
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
		List<Item> itemsToBeRemoved = new ArrayList<Item>(); 
		//Updates all the sprites
		for(Sprite sprite : sprites) {
			sprite.moveXAxis();
			Tile[] tilesToCheck = getTileAround(sprite.getMoveBox().getPosition());
			for(int j = 0; j < tilesToCheck.length; j++) {
				if(tilesToCheck[j] != null && (sprite.getMoveBox().intersects(tilesToCheck[j].getCollisionBox())
						|| (tilesToCheck[j].getProperty() == Tile.UNWALKABLE 
						&& tilesToCheck[j].intersects(sprite.getHitBox())))) {
					sprite.moveBack();
				}
			}
			
			//Check if enemies are in range of a player
			enemyShoot();
			
			//Check if the sprite hit another sprite
			for(Sprite sprite2 : sprites) {
				if(sprite != sprite2 && sprite.getMoveBox().intersects(sprite2.getMoveBox())) {
					sprite.moveBack();
				}
			}
			
			sprite.moveYAxis();
			for(int j = 0; j < tilesToCheck.length; j++) {
				if(tilesToCheck[j] != null && (sprite.getMoveBox().intersects(tilesToCheck[j].getCollisionBox())
						|| (tilesToCheck[j].getProperty() == Tile.UNWALKABLE 
						&& tilesToCheck[j].intersects(sprite.getHitBox())))) {
					sprite.moveBack();
				}
			}
			//Check if the sprite hit another sprite
			for(Sprite sprite2 : sprites) {
				if(sprite != sprite2 && sprite.getMoveBox().intersects(sprite2.getMoveBox())) {
					sprite.moveBack();
				}
			}
			
			//Check if player hit Item
			for(int j = 0; j < items.size(); j++){
				if(sprite.getHitBox().intersects(items.get(j).getCollisionBox())){
					if(sprite.pickUpItem(items.get(j))){
						itemsToBeRemoved.add(items.get(j));
					}
				}
			}
		}
		
		List<Sprite> spritesToBeRemoved = new ArrayList<Sprite>();
		List<Projectile> projectilesToBeRemoved = new ArrayList<Projectile>();
		//Updates the projectiles 5 times each update
		for(int k = 0; k < 5; k++) {
			//Moves the projectiles
			for(int j = 0; j < projectiles.size(); j++) {
				if(!projectilesToBeRemoved.contains(projectiles.get(j))) {
					projectiles.get(j).move();
					//Checks if the projectile hit a sprite
					for(int i = 0; i < sprites.size(); i++){
						if(sprites.get(i).getHitBox().intersects(projectiles.get(j).getCollisionBox())) {
							System.out.println("projectile collision");
							sprites.get(i).reduceHealth(projectiles.get(j).getDamage());
							projectilesToBeRemoved.add(projectiles.get(j));
							if(sprites.get(i).getHealth() <= 0){
								spritesToBeRemoved.add(sprites.get(i));
							}
						}
					}
					//Check if the projectile hit an object
					CollisionBox box = projectiles.get(j).getCollisionBox();
					Tile[] tilesToCheck = getTileAround(box.getPosition());
					for(int l = 0;l < tilesToCheck.length; l++) {
						if(tilesToCheck[l] != null && box.intersects(tilesToCheck[l].getCollisionBox())) {
							projectilesToBeRemoved.add(projectiles.get(j));
						}
					}
				}
			}
		}
		//Check if the projectile has travelled further then it can
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isMaxRangeReached()){
				projectilesToBeRemoved.add(projectiles.get(i));
			}
		}
		//Remove all the objects which needs to be removed
		this.removeSprites(spritesToBeRemoved);
		this.removeProjectiles(projectilesToBeRemoved);
		this.removeitems(itemsToBeRemoved);
		
	}
	
	/*
	 * Gives an array of the tiles around the specified position.
	 */
	private Tile[] getTileAround(Position pos) {
		Tile[] tilesToCheck = new Tile[9];
		int i = 0;
		for(int x = (int)pos.getX() - 1; x <= (int)pos.getX() + 1; x++) {
			for(int y = (int)pos.getY() - 1; y <= (int)pos.getY() + 1; y++) {
				tilesToCheck[i] = ((validPosition(new Position(x, y))) ? tiles[x][y] : null);
				i++;
			}
		}
		return tilesToCheck;
	}
	
	/**
	 * returns all items currently in the world
	 * @return all the items in the world
	 */
	public List<Item> getItems(){
		return this.items;
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
		this.pcs.firePropertyChange(GameModel.REMOVED_OBJECT, projectile, null);
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
	
	/**
<<<<<<< HEAD
	 * If the player
	 * @param e
	 * @param p
	 */
	public void enemyShoot() {
		List<Player> players = new ArrayList<Player>();
		for(Sprite s : sprites){
			if(s instanceof Player){
				players.add((Player)s);
			}else{//All players will be in the first places in sprites -> all players are in 
				//list players before we get into else
				for(Player p : players){
					float dx = s.getX() - p.getX();
					float dy = s.getY() - p.getY();
					float distance = (float) Math.sqrt(dx*dx+dy*dy);
					if(distance <= s.getActiveWeapon().getRange() + p.getHitBox().getWidth() && 
							canMove(s.getCenter(), p.getCenter())){
						addProjectile(s.getActiveWeapon().createProjectile(s.getDirection(), 
								s.getProjectileSpawn()));
					}
				}
			}
		}
//		float dx = e.getX() - player.getX();
//		float dy = e.getY() - player.getY();
//		float distance = (float) Math.sqrt(dx*dx+dy*dy);
//		if(distance <= e.getActiveWeapon().getRange() + player.getHitBox().getWidth() && 
//				canMove(e.getCenter(), player.getCenter())){
//			addProjectile(e.getActiveWeapon().createProjectile(e.getDirection(), e.getProjectileSpawn()));
//		}
	}
	
	/**
	 * The Player picks up any weapon the player stands on.
	 * @return true if the player picks up a weapon.
	 */
	public boolean playerPickUpWeapon(){
		for(Sprite sprite : this.sprites){
			if(sprite instanceof Player){
				Player p = (Player)sprite;
				for(int j = 0; j < items.size(); j++){
					if(p.getHitBox().intersects(items.get(j).getCollisionBox()) && 
							items.get(j) instanceof Weapon){
						p.pickUpWeapon((Weapon)items.get(j));

						this.tiles[(int)p.getX()][(int)p.getY()].setProperty(Tile.NONE);
						this.pcs.firePropertyChange(GameModel.REMOVED_OBJECT, items.get(j), null);
						items.remove(j);
						return true;
					}
				}
				p.pickUpWeapon(null);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is possible to move between two adjacent tiles.
	 * NOTE:
	 * <br>-This method do support diagonal tile checks.
	 * <br>-This will not check if the two positions are actually adjacent.
	 * Therefore, <code>canMove(new Position(0, 0), new Position(0, 1))</code>
	 * will, for example, return the same as <code>canMove(new Position(0, 0), new Position(0, 10))</code>.
	 * @param pos1 the position of the first tile.
	 * @param pos2 the position of the second tile.
	 * @return <code>true</code> if it's possible to move between the two tiles.
=======
	 * Checks if it is possible to move between the two positions without crossing an obstacle.<br>
	 * Note: <code>canMove(pos1, pos2)</code> will return the same as <code>canMove(pos2, pos1)</code>.
	 * @param pos1 one of the two positions.
	 * @param pos2 the second of the two positions.
	 * @return <code>true</code> if its possible to move between the two positions.
>>>>>>> origin/CallebergBranch
	 */
	public boolean canMove(Position pos1, Position pos2) {
		Line l = new Line(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY());
		for(int x = (int)Math.min(pos1.getX(), pos2.getX()); x < Math.max(pos1.getX(), pos2.getX()); x++) {
			for(int y = (int)Math.min(pos1.getY(), pos2.getY()); y < Math.max(pos1.getY(), pos2.getY()); y++) {
				if(l.intersects(tiles[x][y].getCollisionBox()) || 
						(tiles[x][y].getProperty() == Tile.UNWALKABLE && tiles[x][y].intersects(l))) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if the specified position is a valid position on the map.
	 * @param pos the position to check.
	 * @return <code>true</code> if the specified position is one the map.
	 */
	public boolean validPosition(Position pos) {
		return !(pos.getX() < 0 || pos.getX() >= this.getWidth() || pos.getY() < 0 || pos.getY() >= this.getHeight());
	}
	
	/**
	 * Gives the width of the map.
	 * @return the width of the map.
	 */
	public int getWidth() {
		return this.tiles.length;
	}
	
	/**
	 * Gives the height of the map.
	 * @return the height of the map.
	 */
	public int getHeight() {
		return this.tiles[0].length;
	}
	
	/**
	 * returns all items currently in the world
	 * @return all the items in the world
	 */
	public List<Item> getitems(){
		return this.items;
	}
	
	/**
	 * set the spawn points
	 * @param spawnPoints the spawn points
	 */
	public void setSpawnPoints(List<Tile> spawnPoints){
		this.spawnPoints = spawnPoints;
	}
	
	/**
	 * Removes the specified items from the world.
	 * @param items the items to remove.
	 */
	public void removeitems(List<Item> items) {
		for(Item s : items) {
			this.removeItem(s);
		}
	}
	/**
	 * Removes the specified Item from the world.
	 * @param Item the Item to remove.
	 */
	public void removeItem(Item Item) {
		this.items.remove(Item);
		this.pcs.firePropertyChange(GameModel.REMOVED_OBJECT, Item, null);
	}

	/**
	 * Fire an event.
	 * @param event string describing the event.
	 * @param obj the object which has changed.
	 */
	public void fireEvent(String event, Object obj){
		pcs.firePropertyChange(event, null, obj);
	}
}
