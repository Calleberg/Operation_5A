package Item;

import geometrical.Position;
import projectile.Projectile;

public class Weapon {
	private float speed;
	private int damage;
	private float range;
	private int ammunitionAmount;
	private int ammunitionInWeapon;
	private float reloadTime;
	
	public Weapon(float speed, int damage, float range, int ammunitionAmount, 
			int ammunitionInWeapon, float reloadTime){
		this.speed = speed;
		this.damage = damage;
		this.range = range;
		this.ammunitionAmount = ammunitionAmount;
		this.ammunitionInWeapon = ammunitionInWeapon;
		this.reloadTime = reloadTime;
	}
	
	public Projectile createProjectile(float direction , Position pos){
		return new Projectile(damage, speed, range, direction, pos);
	}
	
	/**
	 * reloads the weapon
	 * @param ammunition the ammunition amount needed for full ammo.
	 */
	public void reload(int ammunition){
		if(ammunition < this.missingBullets()){
			ammunitionInWeapon = ammunitionInWeapon + ammunition;
		}else{
			ammunitionInWeapon = ammunitionAmount;
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
	public float getSpeed(){
		return speed;
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
	public int getAmmunitionAmount(){
		return ammunitionAmount;
	}
	/**
	 * Returns the amount of bullets needed for full capability
	 * @return the amount of bullets missing for full capability
	 */
	public int missingBullets(){
		return ammunitionAmount-ammunitionInWeapon;
	}
	/**
	 * Returns the amount of ammunition there is in the weapon
	 * @return the amount of ammunition in the weapon
	 */
	public int getAmmunitionInWeapon(){
		return ammunitionInWeapon;
	}
	/**
	 * Returns the range the weapon can travel, the range will be the same for 
	 * all ranged weapons but differ for melee weapons
	 * @return the range of the weapon
	 */
	public float getRange(){
		return range;
	}
}
