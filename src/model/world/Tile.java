package model.world;

public class Tile {

	private int floor;
	
	/**
	 * Creates a new tile with the specified floor.
	 * @param floor the floor to use.
	 */
	public Tile(int floor) {
		this.floor = floor;
	}
	
	/**
	 * Sets the floor on this tile.
	 * @param floor the new floor on this tile.
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/**
	 * Gives the floor on this tile.
	 * @return the floor on this tile.
	 */
	public int getFloor() {
		return this.floor;
	}
}
