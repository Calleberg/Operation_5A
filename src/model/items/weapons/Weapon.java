package model.items.weapons;

import java.awt.image.BufferedImage;
import java.util.Date;

import model.geometrical.Position;
import model.items.Item;


public class Weapon implements Item {
	private float projectileSpeed;
	private int damage;
	private float range;
	private int magazineCapacity;
	private int ammunitionInMagazine;
	
	private int reloadTime;
	private int rateOfFire;
	
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
		this.projectileSpeed = projectileSpeed;
		this.damage = damage;
		this.range = range;
		this.magazineCapacity = magazineCapacity;
		this.reloadTime = reloadTime;
		this.rateOfFire=rateOfFire;
		
		ammunitionInMagazine=magazineCapacity;

	}
	
	public Projectile createProjectile(float direction , Position pos){
		if (timeNow()-lastTimeFired>3600/rateOfFire
				&& timeNow()-lastTimeReloaded>reloadTime){
			if (ammunitionInMagazine==0){
				noAmmo();
				//No shot fired
				return null;
			} else {
				//ammunitionInMagazine-=1;
				lastTimeFired=timeNow();
				return new Projectile(damage, projectileSpeed, range, direction, pos);
			}
		} else {
			noShotFired();
			//No shot fired
			return null;
		}
	}
	
	private void noShotFired() {
		// TODO Auto-generated method stub
		
	}

	private long timeNow(){
		Date d = new Date();
		return d.getTime();
	}
	private void noAmmo() {
		//TODO no ammo error and sound...
	}

	/**
	 * reloads the weapon
	 * @param ammunition the ammunition amount needed for full ammo.
	 */
	public void reload(int ammunition){
		if(ammunition <= numbersOfMissingBullets()){
			lastTimeReloaded=timeNow();
			ammunitionInMagazine = ammunitionInMagazine + ammunition;
		}
		try {
			Thread.sleep((long) getReloadTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Returns the time it takes to reload
	 * @return the time it takes to reload
	 */
	public float getReloadTime(){
		return reloadTime;
	}
	/**
	 * Returns the speed of which the projectile fired by the weapon travels
	 * @return the speed of the projectile
	 */
	public float getProjectileSpeed(){
		return projectileSpeed;
	}
	/**
	 * Returns the damage the projectile fired by the weapon causes
	 * @return the damage of the projectile
	 */
	public int getDamage(){
		return damage;
	}
	/**
	 * Returns the ammunition capability of the weapon
	 * @return the ammunition capability
	 */
	public int getMagazineCapacity(){
		return magazineCapacity;
	}
	/**
	 * Returns the amount of bullets needed for full capability
	 * @return the amount of bullets missing for full capability
	 */
	public int numbersOfMissingBullets(){
		return magazineCapacity-ammunitionInMagazine;
	}
	/**
	 * Returns the amount of ammunition there is in the weapon
	 * @return the amount of ammunition in the weapon
	 */
	public int getAmmunitionInMagazine(){
		return ammunitionInMagazine;
	}
	/**
	 * Returns the range the weapon can travel, the range will be the same for 
	 * all ranged weapons but differ for melee weapons
	 * @return the range of the weapon
	 */
	public float getRange(){
		return range;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Position p) {
		// TODO Auto-generated method stub
		
	}
}
