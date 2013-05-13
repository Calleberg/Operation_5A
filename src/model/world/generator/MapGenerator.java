package model.world.generator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * 
 * 
 * @author Calleberg
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
	/**
	 * The value for rural road tiles.
	 */
	public static final int RURAL_ROAD = 4;
	
	public static final int HOUSE = 7;
	public static final int COMMERCIAL = 6;
	public static final int FOREST = 8;
	
	public static final int USED = 5;
	
	private final int maxShore = 8;
	private final int blendValue = 180;
	private final int landHeight = 50;
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
		Point2D[] towns = new Point2D[2];
		for(int i = 0; i < towns.length; i++) {
			Point2D point = new Point2D.Float(((random.nextInt(w - maxShore * 2) + maxShore)/3)*3, 
					((random.nextInt(w - maxShore * 2) + maxShore)/3)*3);
			this.addTown(temp, (int)point.getX(), (int)point.getY());
			towns[i] = point;
		}
		
		//Generates rural roads between towns
		for(int i = 0; i < towns.length; i++) {
			Point2D town1 = towns[(i+1) % towns.length];
			Point2D town2 = towns[i];
			this.addRoad(temp, (int)town1.getX(), (int)town1.getY(), (int)town2.getX(), (int)town2.getY());
		}

		//Generates default rural roads
		this.addRoad(temp, maxShore * 2, maxShore * 2, w - maxShore * 2, h - maxShore * 2);
		this.addRoad(temp, w - maxShore * 2, maxShore * 2, maxShore * 2, h - maxShore * 2);

		return temp;
	}
	
	/*
	 * Adds a rural road between the two positions provided.
	 */
	private void addRoad(int[][] temp, int x, int y, int x2, int y2) {
		//Changes the positions
		x = (x/3)*3;
		y = (y/3)*3;
		x2 = (x2/3)*3;
		y2 = (y2/3)*3;
		
		List<RoadNode> points = new ArrayList<RoadNode>();
		points.add(new RoadNode(x, y, x2, y2, RoadNode.Direction.NORTH));
	
		while(points.size() > 0) {
			
			for(RoadNode node : points) {
				if(temp[node.getX()][node.getY()] == WATER || node.isDone(6)) {
					points.remove(node);
					break;
				}else if(temp[node.getX()][node.getY()] == LAND) {
					temp[node.getX()][node.getY()] = RURAL_ROAD;
				}
				
				if(random.nextInt(3) == 0 && node.getX() % 3 == 0 && node.getY() % 3 == 0) {
					node.turn();
				}
				
				node.moveStep();
				
			}//for
			
		}//while
		
	}
	
	private boolean validPosition(int[][] temp,int x, int y) {
		return !(x < 0 || y < 0 || x >= temp.length || y >= temp[0].length);
	}

	/*
	 * Adds a town at the specified position.
	 */
	private void addTown(int[][] temp, int startx, int starty) {
		for(int i = 0; i < 6; i++) {
			int roadX = startx - (random.nextInt(3) + 1) * 3 + 3;
			int roadY = starty - (random.nextInt(3) + 1) * 3 + 3;
			int roadW = (random.nextInt(3) + 1) * 3;
			int roadH = (random.nextInt(3) + 1) * 3;
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
