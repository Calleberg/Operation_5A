package model.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import model.geometrical.Position;
import model.world.props.PropFactory;

/**
 * This class reads strings and converts them to tiles and can save tiles as strings.
 * 
 * @author Martin Calleberg
 *
 */
public class WorldBuilderIO {
	
	private static final String PROP_PREFIX = "p";
	private static final String PROPERTY_PREFIX = "s";
	
	/**
	 * Adds the tiles saved in the file at the path specified by the path.
	 * @param tiles the tiles to add the new tiles to.
	 * @param startX the start position.
	 * @param startY the start position.
	 * @param base the base string of the files.
	 * @param spawnPoints the list to add spawnpoints to.
	 */
	public static void addTiles(Tile[][] tiles, int startX, int startY, String path, List<Tile> spawnPoints) {
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
					Tile t =  generateTile(y + startX, x + startY, data[x][y], spawnPoints); //The position is mirrored in data[][]
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
	 * @param a list of spawnpoints where (if this tile has one) the spawnpoint on this tile is added.
	 * @return the new tile. However, if the tile is only an empty grass tile <code>null</code> will
	 * be returned.
	 */
	public static Tile generateTile(int x, int y, String tileData, List<Tile> spawnPoints) {
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
						spawnPoints.add(tile);
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
