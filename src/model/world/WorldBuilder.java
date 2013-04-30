package model.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Random;

import model.geometrical.Position;
import model.world.props.PropFactory;

/**
 * This will build new worlds by using specific seeds which always will produce the
 * same map every time.
 * 
 * @author Calleberg
 *
 */
public class WorldBuilder {

	private Random random;
	private long seed;
	private int[][] mapTest = new int[][]{
			{0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,2,2,2,2,2,2,2,0},
			{0,1,2,1,1,1,2,1,2,0},
			{0,1,2,2,2,2,2,2,2,0},
			{0,1,2,1,2,1,1,1,2,0},
			{0,1,2,2,2,1,1,1,2,0},
			{0,1,1,1,2,1,1,1,2,0},
			{0,1,1,1,2,2,2,2,2,0},
			{0,0,0,0,0,0,0,0,0,0},
	};
	
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
		random = new Random(seed);
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
	 * Creates a new world with the specified size.
	 * @param width the width of the world to create.
	 * @param height the height of the world to create.
	 * @return a new world.
	 */
	public Tile[][] getNewWorld(int width, int height) {
		//TODO: fixa så att det skapas en riktig värld.
		
		Tile[][] tiles = new Tile[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = new Tile(new Position(x, y), 0);
			}
		}
		
		for(int x = 0; x < mapTest.length; x++) {
			for(int y = 0; y < mapTest[0].length; y++) {
				if(mapTest[x][y] == 0) {
					this.addTiles(tiles, x*10, y*10, "lots/water.lot");
				}else if(mapTest[x][y] == 2) {
					StringBuilder sb = new StringBuilder("road/10x10_road_");
					if(mapTest[x][y-1] == 2) {
						sb.append('N');
					}
					if(mapTest[x+1][y] == 2) {
						sb.append('E');
					}
					if(mapTest[x][y+1] == 2) {
						sb.append('S');
					}
					if(mapTest[x-1][y] == 2) {
						sb.append('W');
					}
					sb.append(".lot");
					this.addTiles(tiles, x*10, y*10, sb.toString());
				}
			}
		}
		
		//TODO: ta bort.
		this.addTiles(tiles, 50, 30, "lots/misc/10x10_testplace.lot");
		
		return tiles;
	}
	
	/**
	 * Adds the tiles saved in the file at the path specified by the url.
	 * @param tiles the tiles to add the new tiles to.
	 * @param startX the start position.
	 * @param startY the start position.
	 * @param url the path to the file.
	 */
	public void addTiles(Tile[][] tiles, int startX, int startY, String url) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		try {
			InputStream is = classLoader.getResourceAsStream(url);
			BufferedReader reader;
			if(is == null) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url)), "ISO-8859-1"));
			}else{
				reader = new BufferedReader(new InputStreamReader(is));
			}
			int w = Integer.parseInt(reader.readLine());
			int h = Integer.parseInt(reader.readLine());
			String data[][] = new String[h][w];//The position is mirrored in data[][]
						
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if(!line.substring(0, 2).equals("//")) {
					data[i] = line.split(";");
					i++;
				}
			}

			for(int x = 0; x < data.length; x++) {
				for(int y = 0; y < data[0].length; y++) {
					tiles[y + startX][x + startY] = generateTile(y + startX, x + startY, data[x][y]); //The position is mirrored in data[][]
				}
			}

			reader.close();
		} catch (IOException exc) {
			System.out.println("Could not find " + url);
		}
	}
	
	/**
	 * Generates a tile at the specified position with the data provided in the string.
	 * @param x the position of the tile.
	 * @param y the position of the tile.
	 * @param tileData a string with data.
	 * @return the new tile.
	 */
	public Tile generateTile(int x, int y, String tileData) {
		String[] data = tileData.split(",");
		Tile tile = new Tile(new Position(x, y), Integer.parseInt(data[0]));

		for(int i = 1; i < data.length; i++) {
			if(data[i].substring(0,1).equals("p")) {
				tile.setProp(PropFactory.getProp(new Position(x, y), Integer.parseInt(data[i].substring(1))));
			}else{
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
	 * @param comments give some clue to what the tiles are. This string can be multilined.
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
					if(t.getProp() != null) {
						temp += ",p" + t.getProp().getImageNbr();
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
