package model.items.weapons;

import java.util.Date;

import model.geometrical.Position;
import model.items.Item;


public class Weapon extends Item {
	private final float projectileSpeed;
	private final int damage;
	private final float range;
	private final int magazineCapacity;
	private final int reloadTime;
	private final int rateOfFire;
	
	private int ammunitionInMagazine;
	
	private long lastTimeFired=0;
	private long lastTimeReloaded=0;
	
	/**
	 * 
	 * @param projectileSpeed the speed of the projectile
	 * @param damage the damage done by the projectile
	 * @param range the range of the projectile
	 * @param magazineCapacity the capacity of of the magazine for the weapon
	 * @param reloadTime the time it takes to reload the weapon in milliseconds
	 * @param rateOfFire the numbers of rounds fired per minute (AKA RPM, rounds per minutue)
	 */
	public Weapon(float projectileSpeed, int damage, float range, int magazineCapacity, int reloadTime, int rateOfFire){
		super(null);
		this.projectileSpeed = projectileSpeed;
		this.damage = damage;
		this.range = range;
		this.magazineCapacity = magazineCapacity;
		this.reloadTime = reloadTime;
		this.rateOfFire=rateOfFire;
		
		ammunitionInMagazine=magazineCapacity;

	}
	
	public Projectile createProjectile(float direction, Position pos){
		if (timeNow()-lastTimeFired>3600/rateOfFire
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
	 * Reloads the weapon
	 * @param ammunition the ammunition used to reload the weapon.
	 * @return the surplus ammunition not used to fill magazine.
	 */
	public int reload(int ammunition){
		if (!isReloading() && ammunition>0){
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


}
