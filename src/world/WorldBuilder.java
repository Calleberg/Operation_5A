package world;

public class WorldBuilder {

	/**
	 * Creates a new default world builder.
	 */
	public WorldBuilder() {
		//TODO
	}
	
	/**
	 * Creates a new world with the specified size.
	 * @param width the width of the world to create.
	 * @param height the height of the world to create.
	 * @return a new world.
	 */
	public Tile[][] getNewWorld(int width, int height) {
		//TODO
		
		Tile[][] tiles = new Tile[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = new Tile(1);
			}
		}
		
		return tiles;
	}
}
