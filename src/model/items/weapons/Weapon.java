package model.items.weapons;

import java.util.Date;

import model.geometrical.Position;
import model.items.Item;
import model.other.Saveable;

/**
 * Represents a weapon in the game.
 * 
 * @author Vidar Eriksson
 *
 */
public class Weapon extends Item implements Saveable {
	
	private final float projectileSpeed;
	private final int damage;
	private final float range;
	private final int magazineCapacity;
	private final int reloadTime;
	private final int rateOfFire;
	private final int iconNumber;
	private final String[] keys;
	private final boolean droppable;
	
	private int ammunitionInMagazine;
	
	private long lastTimeFired;
	private long lastTimeReloaded;
	
	/**
	 * A type of weapon a Weapon model can be. 
	 */
	public static enum Type{MELEE, GUN, FISTS;
		
		/**
		 * Gives the Type which has the same text as the text provided.
		 * @param text the text to check with.
		 * @return the Type which has the same text as the text provided.
		 */
		public static Type fromString(String text) {
			if (text != null) {
				for (Type b : Type.values()) {
					if (text.equalsIgnoreCase(b.toString())) {
						return b;
					}
				}
			}
			throw new IllegalArgumentException("No enum with name " + text + " found");
		}
	}
	private Type type;
	
	/**
	 * Value which specifieS if a weapon has unlimited ammo.
	 */
	public static final int UNLIMITED_AMMO = -1;
		
	/**
	 * Creates a weapon with the specified parameters.
	 * @param projectileSpeed the speed of the projectile
	 * @param damage the damage done by the projectile
	 * @param range the numbers of tiles the projectile travels
	 * @param magazineCapacity the capacity of of the magazine for the weapon
	 * @param reloadTime the time it takes to reload the weapon in milliseconds
	 * @param rateOfFire the numbers of rounds fired per minute (AKA RPM, rounds per minutue)
	 * @param iconNumber the icon number corresponding to this icon
	 * @param keys the name of the weapon by using keys.
	 * @param droppable whether the weapon is droppable or not.
	 * @param ammunitionInMagazine the amount of ammunition in the magazine.
	 * @param lastTimeFired how many milliseconds since the weapon was fired.
	 * @param lastTimeReloaded how many milliseconds since the weapon was reloaded
	 * @param type the type of the weapon.
	 */
	protected Weapon(float projectileSpeed, int damage, float range,
			int magazineCapacity, int reloadTime, int rateOfFire, int iconNumber, String[] keys,
			boolean droppable, int ammunitionInMagazine, Type type){
		this(projectileSpeed, damage, range, magazineCapacity, reloadTime, rateOfFire, iconNumber, 
				keys, droppable, ammunitionInMagazine, type, new Position(0, 0));
	}
	
	/**
	 * Creates a weapon with the specified parameters.
	 * @param projectileSpeed the speed of the projectile
	 * @param damage the damage done by the projectile
	 * @param range the numbers of tiles the projectile travels
	 * @param magazineCapacity the capacity of of the magazine for the weapon
	 * @param reloadTime the time it takes to reload the weapon in milliseconds
	 * @param rateOfFire the numbers of rounds fired per minute (AKA RPM, rounds per minutue)
	 * @param iconNumber the icon number corresponding to this icon
	 * @param keys the name of the weapon by using keys.
	 * @param droppable whether the weapon is droppable or not.
	 * @param ammunitionInMagazine the amount of ammunition in the magazine.
	 * @param lastTimeFired how many milliseconds since the weapon was fired.
	 * @param lastTimeReloaded how many milliseconds since the weapon was reloaded
	 * @param type the type of the weapon.
	 * @param pos the position of the weapon, if placed on the ground.
	 */
	protected Weapon(float projectileSpeed, int damage, float range,
			int magazineCapacity, int reloadTime, int rateOfFire, int iconNumber, String[] keys,
			boolean droppable, int ammunitionInMagazine, Type type, Position pos){
		super(null, iconNumber);
		
		this.projectileSpeed = projectileSpeed;
		this.damage = damage;
		this.range = range;
		this.magazineCapacity = magazineCapacity;
		this.reloadTime = reloadTime;
		this.rateOfFire=rateOfFire;
		this.keys=keys;
		this.iconNumber=iconNumber;
		this.droppable = droppable;

		this.ammunitionInMagazine=ammunitionInMagazine;
		this.type = type;	
		this.setPosition(pos);
	}
	
	/**
	 * The weapon creates a projectile corresponding to the weapons specifications.
	 * If the weapon does not fire ie no ammo left in magazine or overheated it will
	 * return a null projectile.
	 * @param direction the direction the projectile will have.
	 * @param pos the position the projectile will have.
	 * @return the projectile fired.
	 */
	public Projectile createProjectile(float direction, Position pos){
		if (timeNow()-lastTimeFired>rateOfFire
				&& !isReloading()){
			if (ammunitionInMagazine==0){
				return null;
			} else {
				ammunitionInMagazine-=1;
				lastTimeFired=timeNow();
				boolean visible = (getType() == Type.GUN) ? true : false;
				return new Projectile(damage, projectileSpeed, range, direction, pos, visible);
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Gives the type of weapon.
	 * @return the type of weapon.
	 */
	public Type getType() {
		return this.type;
	}

	private long timeNow(){
		Date d = new Date();
		return d.getTime();
	}

	private boolean isReloading(){
		return timeNow()-lastTimeReloaded < reloadTime;
	}
	/**
	 * Reloads the weapon. The ammunition not used to relod is returned
	 * @param ammunition the ammunition provided to reload the weapon.
	 * @return the surplus ammunition not used to fill the weapon's magazine.
	 */
	public int reload(int ammunition){
		if (!isReloading() && ammunition>0 && magazineCapacity-ammunitionInMagazine!=0 && reloadTime!=0){
			lastTimeReloaded=timeNow();
			
			int missingBullets=magazineCapacity-ammunitionInMagazine;
			if(ammunition <= missingBullets){
				ammunitionInMagazine+=ammunition;
				return 0;
			} else {
				ammunitionInMagazine+=missingBullets;
				return ammunition-missingBullets;
		
			}
		}
		return ammunition;
	}

	/**
	 * Return true if the weapon is droppable.
	 * @return true if the weapon is droppable.
	 */
	public boolean isDroppable(){
		return droppable;
	}
	
	/**
	 * Returns the magazine maximum capacity of the weapon's magazine
	 * @return the magazine maximum capacity of the weapon's magazine
	 */
	public int getMagazineCapacity(){
		return magazineCapacity;
	}
	/**
	 * Returns the amount of ammunition in the weapon's magazine
	 * @return the amount of ammunition in the weapon's magazine
	 */
	public int getAmmunitionInMagazine(){
		return ammunitionInMagazine;
	}
	/**
	 * Return the range of the weapon.
	 * @return the range of the weapon.
	 */
	public float getRange(){
		return range;
	}
	
	/**
	 * Gives an array of two keys used to construct the name of the weapon.
	 * @return an array of two keys used to construct the name of the weapon.
	 */
	public String[] getKeys() {
		return this.keys;
	}
	
	/**
	 * NOTE: Use the factory instead!!
	 */
	@Override
	public void restore(String[] data) {
		return;
	}

	@Override
	public String[] getData() {
		return new String[]{
				this.ammunitionInMagazine + "",
				this.damage + "",
				this.droppable + "",
				this.iconNumber + "",
				this.magazineCapacity + "",
				this.projectileSpeed + "",
				this.range + "",
				this.rateOfFire + "",
				this.reloadTime + "",
				this.type.toString(),
				this.keys[0],
				this.keys[1],
				this.getPosition().getX() + "",
				this.getPosition().getY() + ""
		};
	}


}
