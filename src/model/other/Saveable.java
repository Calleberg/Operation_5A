package model.other;

/**
 * Objects which should be able to be saved and restored should implement this interface.
 * 
 * @author
 *
 */
public interface Saveable {
	
	/**
	 * Loads the object with all the values it gets from the data array.
	 * @param data an array with all the data which itself sent when being saved.
	 */
	public void restore(String[] data);
	
	/**
	 * Gives an array of data this object needs to be able to restore itself.
	 * @return an array of data this object needs to be able to restore itself.
	 */
	public String[] getData();
}
