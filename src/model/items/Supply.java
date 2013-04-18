package model.items;
//TODO javadoc
import javax.swing.Icon;

/**
 * 
 * @author Vidar
 *
 */
public class Supply {
	
	private int food;
	private int ammo;
	private int health;
	private Icon icon;

	/**
	 * 
	 * @param food
	 * @param ammo
	 * @param health
	 * @param icon
	 */
	public Supply(int food, int ammo, int health, Icon icon){
		this.food=food;
		this.ammo=ammo;
		this.health=health;
		this.icon=icon;
	}
	/**
	 * 
	 * @return
	 */
	public int getFood(){
		return food;
	}
	/**
	 * 
	 * 
	 * @return
	 */
	public int getAmmo(){
		return ammo;
	}
	/**
	 * 
	 * @return
	 */
	public int getHealth(){
		return health;
	}
	/**
	 * 
	 * @return
	 */
	public Icon getIcon(){
		return icon;
	}
	
}
