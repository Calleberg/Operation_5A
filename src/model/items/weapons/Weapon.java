package model.items.weapons;

import java.util.Date;

import model.geometrical.Position;
import model.items.Item;

/**
 * Represents a weapon in the game.
 * 
 * @author Vidar Eriksson
 *
 */
public class Weapon extends Item {
	private final float projectileSpeed;
	private final int damage;
	private final float range;
	private final int magazineCapacity;
	private final int reloadTime;
	private final int rateOfFire;
	private final int iconNumber;
	private final String name;
	
	private int ammunitionInMagazine;
	
	private long lastTimeFired=0;
	private long lastTimeReloaded=0;
	
	/**
	 * Creates a weapon with the specified parameters.
	 * @param projectileSpeed the speed of the projectile
	 * @param damage the damage done by the projectile
	 * @param range the numbers of tiles the projectile travels
	 * @param magazineCapacity the capacity of of the magazine for the weapon
	 * @param reloadTime the time it takes to reload the weapon in milliseconds
	 * @param rateOfFire the numbers of rounds fired per minute (AKA RPM, rounds per minutue)
	 * @param iconNumber the icon number corresponding to this icon
	 * @param name the name of the weapon.
	 */
	public Weapon(float projectileSpeed, int damage, float range, int magazineCapacity, int reloadTime, int rateOfFire, int iconNumber, String name){
		super(null, iconNumber);
		this.projectileSpeed = projectileSpeed;
		this.damage = damage;
		this.range = range;
		this.magazineCapacity = magazineCapacity;
		this.reloadTime = reloadTime;
		this.rateOfFire=rateOfFire;
		this.name=name;
		this.iconNumber=iconNumber;
		
		ammunitionInMagazine=magazineCapacity;

	}
	public Weapon(float projectileSpeed, int damage, float range,
			int magazineCapacity, int reloadTime, int rateOfFire, int iconNumber, String name,
			int ammunitionInMagazine, long lastTimeFired, long lastTimeReloaded){
		this(projectileSpeed, damage, range, magazineCapacity, reloadTime, rateOfFire, iconNumber, name);

		this.ammunitionInMagazine=ammunitionInMagazine;
		this.lastTimeFired=lastTimeFired;
		this.lastTimeReloaded=lastTimeReloaded;

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
				outOfAmmo();
				return null;
			} else {
				ammunitionInMagazine-=1;
				lastTimeFired=timeNow();
				return new Projectile(damage, projectileSpeed, range, direction, pos);
			}
		} else {
			return null;
		}
	}
	
	private void outOfAmmo() {
		//TODO no ammo error and sound...
		
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
	public String toString() {
		return name;
	}
	/**
	 * Returns a string containing the values of the weapon.
	 * @return a string containing the values of the weapon.
	 */
	public String saveWeapon(){
		String s=
				projectileSpeed+"#"+
				damage+"#"+
				range+"#"+
				magazineCapacity+"#"+
				reloadTime+"#"+
				rateOfFire+"#"+
				iconNumber+"#"+
				name+"#"+
				ammunitionInMagazine+"#"+
				lastTimeFired+"#"+
				lastTimeReloaded+"#";
		return s;
	}


}
