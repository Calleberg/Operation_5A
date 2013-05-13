package model.world;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.jar.Attributes;

import model.geometrical.Position;
import model.world.props.PropFactory;
import model.world.generator.MapGenerator;

/**
 * This will build new worlds by using specific seeds which always will produce the
 * same map every time.
 * 
 * @author Calleberg
 *
 */
public class WorldBuilder {

	private long seed;
	private Random random;
	
	private static final String PROP_PREFIX = "p";
	private static final String PROPERTY_PREFIX = "s";
	
	private int[][] test = new int[][]{
			{0,0,0,0,0,0,0,0,0,0},
			{0,1,7,7,3,7,7,3,7,0},
			{0,1,1,1,3,1,1,3,7,0},
			{0,1,3,3,3,3,3,3,3,0},
			{0,1,3,7,3,7,1,7,3,0},
			{0,1,3,7,3,7,1,7,3,0},
			{0,1,3,3,3,3,3,3,3,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,0,1,1,0},
			{0,0,0,0,0,0,0,0,0,0},
	};
	private int[][] testBig = new int[][]{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,3,7,7,7,7,7,3,7,7,3,7,7,7,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,3,7,7,7,7,7,3,7,7,3,7,7,56,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,3,7,7,3,7,7,7,7,7,7,7,7,3,7,0,0,0,0,0,0,0,1,1,1,1,61,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,3,7,7,3,7,7,7,7,7,7,7,7,3,7,7,3,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,3,3,3,3,7,7,7,7,7,7,7,7,3,3,3,3,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,3,7,7,7,7,7,7,7,7,3,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,3,7,7,7,7,7,7,7,7,51,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,3,7,7,7,7,7,7,7,7,3,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,3,7,7,7,7,7,7,7,7,3,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,3,7,7,7,7,7,7,7,7,3,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,51,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,4,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,70,0,0,0,0,0},
			{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,69,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,4,1,1,51,0,0,0,0,1,1,1,1,1,1,1,1,1,62,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,4,52,0,0,0,0,0,0,1,1,1,1,1,54,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,1,1,51,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			};
	
	/*Paths to where the different files are located*/
	private final String BASE_SHORE = "shoreline/10x10_shore_";
	private final String BASE_ROAD = "road/10x10_road_";
	private final String BASE_RURAL_ROAD = "rural/10x10_road_";
	private final String BASE_RESIDENTIAL = "residential/";
	private Properties attributes;
	
	private List<Tile> spawnPoints;
	
	/**
	 * Creates a new default world builder.
	 */
	public WorldBuilder() {
		this(Calendar.getInstance().getTimeInMillis());
	}
	
	/**
	 * Creates a new world builder with the specified seed.
	 * @param seed the seed to use.
	 */
	public WorldBuilder(long seed) {
		this.seed = seed;
		this.random = new Random(seed);
		this.resetSpawnPoints();
		this.initAttributes();
	}
	
	/*
	 * Initialises the map where a specified lot size is the key for the amount of 
	 * lots of that size.
	 */
	private void initAttributes() {
		attributes = new Properties();
		String path = BASE_RESIDENTIAL + "lot";
		this.addAttributesFrom(attributes, path, "10x20");
		this.addAttributesFrom(attributes, path, "20x20");
		this.addAttributesFrom(attributes, path, "20x10");
	}

	private void addAttributesFrom(Map<Object, Object> attributes, String pathBase, String key) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = pathBase + key + "/info.txt";
		
		try {
			InputStream is = classLoader.getResourceAsStream(path);
			BufferedReader reader;
			if(is == null) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "ISO-8859-1"));
			}else{
				reader = new BufferedReader(new InputStreamReader(is));
			}
			reader.readLine();
			attributes.put(pathBase + key + "/" + key + "_N", reader.readLine());		
			reader.readLine();
			attributes.put(pathBase + key + "/" + key + "_E", reader.readLine());	
			reader.readLine();
			attributes.put(pathBase + key + "/" + key + "_S", reader.readLine());	
			reader.readLine();
			attributes.put(pathBase + key + "/" + key + "_W", reader.readLine());	
										
			reader.close();
		} catch (IOException exc) {
			System.out.println("Could not find " + path);
		}
	}
	
	/**
	 * Gives the seed used when generating a world.
	 * @return the seed used when generating a world.
	 */
	public long getSeed() {
		return this.seed;
	}
	
	/**
	 * Gives an empty world.
	 * @param width the width of the world.
	 * @param height the height of the world.
	 * @return an empty world.
	 */
	public static Tile[][] getEmptyWorld(int width, int height) {
		Tile[][] tiles = new Tile[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		return tiles;
	}
	
	/**
	 * Resets the spawnpoints.
	 * This means the list of spawnpoints will be cleared.
	 */
	public void resetSpawnPoints() {
		this.spawnPoints = new ArrayList<Tile>();
	}
	
	/**
	 * Creates a new world with the specified size.
	 * @param width the width of the world to create.
	 * @param height the height of the world to create.
	 * @return a new world.
	 */
	public Tile[][] getNewWorld(int width, int height) {
		MapGenerator g = new MapGenerator(seed);
		int[][] mapData = g.generateWorld(width/10, height/10);
		mapData = this.test;
//		mapData = this.testBig;
		width = mapData.length * 10;
		height = mapData[0].length * 10;
		
		//Resets the spawn points.
		this.resetSpawnPoints();
		
		//Create grass everywhere.
		Tile[][] tiles = new Tile[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
				
		//Loops through every tileset and adds the right tiles.
		for(int x = 0; x < mapData.length; x++) {
			for(int y = 0; y < mapData[0].length; y++) {
				//Adds water.
				if(mapData[x][y] == MapGenerator.WATER) {
					this.addTiles(tiles, x*10, y*10, "lots/water.lot", null, false);
				}
				//Adds shorelines.
				else if(mapData[x][y] == MapGenerator.LAND) {
					this.buildDynamicTile(tiles, mapData, x, y, BASE_SHORE, MapGenerator.WATER, false);
				}
				//Adds the right road part to the world.
				if(mapData[x][y] == MapGenerator.ROAD) {
					this.buildDynamicTile(tiles, mapData, x, y, BASE_ROAD, MapGenerator.ROAD, false);
				}
				//Adds the right rural road part to the world.
				else if(mapData[x][y] == MapGenerator.RURAL_ROAD) {
					this.buildDynamicTile(tiles, mapData, x, y, BASE_RURAL_ROAD, MapGenerator.RURAL_ROAD, false);
				}
				else if(mapData[x][y] == MapGenerator.HOUSE) {
					this.tryPlaceBuilding(tiles, mapData, x, y, BASE_RESIDENTIAL, MapGenerator.HOUSE);
				}
			}
		}
				
		return tiles;
	}
	
	/***
	 * Tries to place a building at the specified position.
	 * @param tiles the tiles to add to.
	 * @param data the data to read from.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @param base the base path to the files.
	 * @param type the type to add.
	 */
	private void tryPlaceBuilding(Tile[][] tiles, int[][] data, int x, int y, String base, int type) {
		int w = 0, h = 0;
		//Checks if the building can be 20 in width
		if(data[x+1][y] == type) {
			//Checks if a 20x20 building is possible
			if(data[x][y+1] == type && data[x+1][y+1] == type) {
				w = 2;
				h = 2;
			//Then the building is 20x10
			}else{
				w = 2;
				h = 1;
			}
		//Checks if a 10x20 building can be used
		}else if(data[x][y+1] == type) {
			w = 1;
			h = 2;
		}
		
		//Checks if a building was found.
		if(w != 0 && h != 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("lot" + w + "0x" + h + "0" + "/" + w + "0x" + h + "0_");
			//Adds the appropriate direction value
			if(data[x-1][y] == MapGenerator.ROAD) {
				sb.append("W");
			}else if(data[x+w][y] == MapGenerator.ROAD) {
				sb.append("E");
			}else if(data[x][y-1] == MapGenerator.ROAD) {
				sb.append("N");
			}else if(data[x][y+h] == MapGenerator.ROAD) {
				sb.append("S");
			}
			if(sb.toString().length() > 6) {
				int n = Integer.parseInt(attributes.get(base + sb.toString()).toString());
				if(n != 0) {
					//Loops through all the tiles used to build a building so they
					//cannot be used by another building.
					for(int xLoop = 0; xLoop < w; xLoop++) {
						for(int yLoop = 0; yLoop < h; yLoop++) {
							data[x + xLoop][y + yLoop] = MapGenerator.USED;
						}
					}
					sb.append((1 + random.nextInt(n)));
					this.addTiles(tiles, x*10, y*10, base, sb.toString(), true);
				}
			}
		}
	}

	/**
	 * Builds a tile which can connect to other tiles of the same type around it.
	 * @param tiles the tiles to add to.
	 * @param data the data to read from.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @param base the base of the path to the files.
	 * @param type the type to build and connect to.
	 * @param once set to true if the tile can only be used once and then be used.
	 */
	private void buildDynamicTile(Tile[][] tiles, int[][] data, int x, int y, String base, int type, boolean once) {
		StringBuilder sb = new StringBuilder();

		//Note: 1 is N, 2 is NE, 3 is E, 4 is SE ...
		//This way complex connections can be made which cannot be expressed in simple directions (N, NE, W etc.)
		if(data[x][y-1] == type) {
			sb.append('1');
		}
		if(data[x+1][y] == type) {
			sb.append('3');
		}
		if(data[x][y+1] == type) {
			sb.append('5');
		}
		if(data[x-1][y] == type) {
			sb.append('7');
		}

		//If none of the simple connections could be made, look for more complex ones
		if(sb.toString().length() == 0) {
			if(data[x+1][y-1] == type) {
				sb.append('2');
			}
			if(data[x+1][y+1] == type) {
				sb.append('4');
			}
			if(data[x-1][y+1] == type) {
				sb.append('6');
			}
			if(data[x-1][y-1] == type) {
				sb.append('8');
			}
		}

		this.addTiles(tiles, x*10, y*10, base, sb.toString(), true);
	}
	
	/**
	 * Gives a list of all the spawn points in the last built world.
	 * @return a list of all the spawn points in the last built world.
	 */
	public List<Tile> getSpawnPoints() {
		return this.spawnPoints;
	}
	
	/**
	 * Adds the tiles saved in the file at the path specified by the path.
	 * @param tiles the tiles to add the new tiles to.
	 * @param startX the start position.
	 * @param startY the start position.
	 * @param base the base string of the files.
	 * @param details the path to the file.
	 */
	public void addTiles(Tile[][] tiles, int startX, int startY, String base, String details, boolean checkDetails) {
		if(checkDetails && details.length() == 0) {
			return;
		}
		String path = (details != null ? base + details + ".lot" : base);
		
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		try {
			InputStream is = classLoader.getResourceAsStream(path);
			BufferedReader reader;
			if(is == null) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "ISO-8859-1"));
			}else{
				reader = new BufferedReader(new InputStreamReader(is));
			}
			//Read the width and height of the tileset
			int w = Integer.parseInt(reader.readLine());
			int h = Integer.parseInt(reader.readLine());
			String data[][] = new String[h][w];//The position is mirrored in data[][] !!
						
			String line;
			int i = 0;
			//Add all the data to an array
			while ((line = reader.readLine()) != null) {
				if(!line.substring(0, 2).equals("//")) {
					data[i] = line.split(";");
					i++;
				}
			}

			//Iterate the array and add a tile for each position
			for(int x = 0; x < data.length; x++) {
				for(int y = 0; y < data[0].length; y++) {
					Tile t =  generateTile(y + startX, x + startY, data[x][y]); //The position is mirrored in data[][]
					if(t != null) {
						tiles[y + startX][x + startY] = t;
					}
				}
			}

			reader.close();
		} catch (IOException exc) {
			System.out.println("Could not find " + path);
		}
	}
	
	/**
	 * Generates a tile at the specified position with the data provided in the string.
	 * @param x the position of the tile.
	 * @param y the position of the tile.
	 * @param tileData a string with data.
	 * @return the new tile. However, if the tile is only an empty grass tile <code>null</code> will
	 * be returned.
	 */
	public Tile generateTile(int x, int y, String tileData) {
		//Create a array of all the data
		String[] data = tileData.split(",");
		int floor = Integer.parseInt(data[0]);
		if(floor == 0 && data.length == 1) {
			return null;
		}
		Tile tile = new Tile(new Position(x, y), floor);

		//Loop through the data
		for(int i = 1; i < data.length; i++) {
			//Is prop?
			if(data[i].substring(0,1).equals(PROP_PREFIX)) {
				tile.addProp(PropFactory.getProp(new Position(x, y), Integer.parseInt(data[i].substring(1))));
			//Is property?
			}else if(data[i].substring(0,1).equals(PROPERTY_PREFIX)) {
				int property = Integer.parseInt(data[i].substring(1));
				if(property != Tile.NONE) {
					tile.setProperty(property);
					if(property != Tile.UNWALKABLE) {
						this.spawnPoints.add(tile);
					}
				}
			}else{
				//Walls are saved as: 1 = west, 2 = north, 3 = both
				int walls = Integer.parseInt(data[i]);
				if(walls > 0 && walls % 2 == 1) {
					tile.setWestWall(true);
					walls--;
				}
				if(walls > 0 && walls % 2 == 0) {
					tile.setNorthWall(true);
				}
			}
		}

		return tile;
	}

	/**
	 * Saves the data provided at the specified location.
	 * @param tiles the tiles to save.
	 * @param file the path to the file to generate.
	 * @param comments give some clue to what the tiles are. 
	 */
	public static void saveTiles(Tile[][] tiles, String file, String comments) {
		File f = new File(file);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Could not create a new file!");
				return;
			}
		}
		try {
			//Open the stream
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "ISO-8859-1");

			//Write width
			String line = tiles.length + "";
			osw.write(line + "\r\n");
			
			//Write height
			line = tiles[0].length + "";
			osw.write(line + "\r\n");
			
			//Add the comments
			osw.write("//" + comments + "\r\n");
			
			//Start adding the tile data
			for(int y = 0; y < tiles[0].length; y++) {
				line = "";
				for(int x = 0; x < tiles.length; x++) {
					Tile t = tiles[x][y];
					String temp = t.getFloor() + "";  //Add the floor
					if(t.hasWestWall() || t.hasNorthWall()) {
						temp += ",";
						//Add which walls are present
						if(t.hasWestWall() && t.hasNorthWall()) {
							temp += "3";
						}else if(t.hasNorthWall()) {
							temp += "2";
						}else if(t.hasWestWall()) {
							temp += "1";
						}
					}
					//Add prop number to file.
					for(int i = 0; i < t.getProps().size(); i++) {
						temp += "," + PROP_PREFIX + t.getProps().get(i).getImageNbr();
					}
					
					//Add the property number.
					if(t.getProperty() != Tile.NONE) {
						temp += "," + PROPERTY_PREFIX + t.getProperty();
					}
					
					//End of tile
					if(x != tiles.length - 1) {
						temp += ";";
					}
					//Add the tile to the row of tiles
					line += temp;
				}
				//Write the whole row of tiles to the file
				osw.write(line + "\r\n");
			}
			
			osw.close();
			fos.close();
			
		} catch (IOException e) {
			System.out.println("Could not save file!");
		}
	}
	
}
