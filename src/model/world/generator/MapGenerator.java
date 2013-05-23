package model.world.generator;

import java.util.Calendar;
import java.util.Random;

/**
 * 
 * 
 * @author Martin Calleberg
 *
 */
public class MapGenerator {

	private long seed;
	private Random random;
	
	/**
	 * The value for water tiles.
	 */
	public static final int WATER = 0;
	/**
	 * The value for land tiles.
	 */
	public static final int LAND = 1;
	/**
	 * The value for city road tiles.
	 */
	public static final int ROAD = 3;
	
	public static final int HOUSE = 7;
	public static final int COMMERCIAL = 6;
	public static final int FOREST = 8;
	
	public static final int SHORE = 20;
	
	public static final int USED = 5;
	
	//Change the values bellow to change the appearance of the map.
	private final int maxShore = 8;
	private final int blendValue = 400;
	private final int landHeight = 0;
	private final int turbulence = 10;
	
	/**
	 * Creates a new map generator which will use the specified seed.
	 * @param seed the seed to use.
	 */
	public MapGenerator(long seed) {
		this.seed = seed;
		this.random = new Random(seed);
	}
	
	/**
	 * Creates a new map generator with a new seed.
	 */
	public MapGenerator() {
		this(Calendar.getInstance().getTimeInMillis());
	}
	
	/**
	 * Gives a new world with the specified size.
	 * @param w the width of the map to generate.
	 * @param h the height of the map to generate.
	 * @return a new world with the specified size.
	 */
	public int[][] generateWorld(int w, int h) {
		int[][] temp = new int[w][h];
		
		//Inits the map
		NoiseGenerator pn = new NoiseGenerator(w, h, seed);
		for(int y = 0; y < temp[0].length; y++) {
			for(int x = 0; x < temp.length; x++) {
				temp[x][y] = (int)(pn.getNoise(x, y, turbulence));
			}
		} 
		
		//Blurs the edges to create an island
		for(int y = 0; y < temp[0].length; y++) {
			for(int x = 0; x < temp.length; x++) {
				if(x < this.maxShore) {
					temp[x][y] -= ((maxShore-x)/(float)maxShore) * blendValue;
				}else if(x > temp.length - this.maxShore) {
					temp[x][y] -= ((x - (temp[0].length-this.maxShore))/(float)maxShore) * blendValue;
				}
				if(y < this.maxShore) {
					temp[x][y] -= ((maxShore-y)/(float)maxShore) * blendValue;
				}else if(y > temp[0].length - this.maxShore) {
					temp[x][y] -= ((y - (temp[0].length-this.maxShore))/(float)maxShore) * blendValue;
				}
			}
		}
		
		//Sets what's water and what's land
		for(int y = 0; y < temp[0].length; y++) {
			for(int x = 0; x < temp.length; x++) {
				if(temp[x][y] > landHeight) {
					temp[x-1][y-1] = (validPosition(temp, x-1, y-1) ? LAND : null);
					temp[x][y-1] = (validPosition(temp, x, y-1) ? LAND : null);
					temp[x-1][y] = (validPosition(temp, x-1, y) ? LAND : null);
				}else{
					temp[x][y] = WATER;
				}
			}
		} 

		//Generates roads and towns
		for(int i = 0; i < 2; i++) {
			this.addStreets(temp, (int)((random.nextInt(w - maxShore * 2) + maxShore)/3)*3, 
					(int)((random.nextInt(w - maxShore * 2) + maxShore)/3)*3);
		}
		
		for(int y = 0; y < temp[0].length; y++) {
			for(int x = 0; x < temp.length; x++) {
				if(temp[x][y] != WATER && temp[x][y] != ROAD) {
					int[] around = getAround(temp, x, y);
					for(int i = 0; i < around.length; i++) {
						if(around[i] == WATER) {
							temp[x][y] = SHORE;
						}
					}
				}
			}
		} 
		
		this.replace(temp, LAND, HOUSE);

		return temp;
	}
	
	/**
	 * Replaces the specified value with the new one everywhere in the map provided.
	 * @param temp the map.
	 * @param oldValue the value to replace.
	 * @param newValue the value to replace with.
	 */
	private void replace(int[][] temp, int oldValue, int newValue) {
		for(int y = 0; y < temp[0].length; y++) {
			for(int x = 0; x < temp.length; x++) {
				if(temp[x][y] == oldValue) {
					temp[x][y] = newValue;
				}
			}
		}
	}
	
	private int[] getAround(int[][] temp, int x, int y) {
		int[] around = new int[9];
		int i = 0;
		for(int xLoop = x-1; xLoop <= x+1; xLoop++) {
			for(int yLoop = y-1; yLoop <= y+1; yLoop++) {
				around[i] = validPosition(temp, xLoop, yLoop) ? temp[xLoop][yLoop] : USED;
				i++;
			}
		}
		return around;
	}
		
	private boolean validPosition(int[][] temp,int x, int y) {
		return !(x < 0 || y < 0 || x >= temp.length || y >= temp[0].length);
	}

	/*
	 * Adds a town at the specified position.
	 */
	private void addStreets(int[][] temp, int startx, int starty) {
		for(int i = 0; i < 6; i++) {
			int roadX = startx - (random.nextInt(3) + 1) * 3 + 3;
			int roadY = starty - (random.nextInt(3) + 1) * 3 + 3;
			int roadW = (random.nextInt(2) + 1) * 3;
			int roadH = (random.nextInt(2) + 1) * 3;
			for(int x = roadX; x <= roadX + roadW; x++) {
				for(int y = roadY; y <= roadY + roadH; y++) {
					if(temp[x][y] == LAND) {
						if(x == roadX || x == roadX + roadW) {
							temp[x][y] = ROAD;
						}else if(y == roadY || y == roadY + roadH) {
							temp[x][y] = ROAD;
						}else{
							temp[x][y] = HOUSE;
						}
					}
				}
			}
		}
	}
}
