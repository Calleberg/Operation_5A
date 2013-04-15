package Item;

public class Weapon {
	private float speed;
	private int damage;
	private float range;
	private int ammunitionAmount;
	private int ammunitionInWeapon;
	private double reloadTime;
	
	public Weapon(float speed, int damage, float range, int ammunitionAmount, 
			int ammunitionInWeapon, double reloadTime){
		this.speed = speed;
		this.damage = damage;
		this.range = range;
		this.ammunitionAmount = ammunitionAmount;
		this.ammunitionInWeapon = ammunitionInWeapon;
		this.reloadTime = reloadTime;
	}
	
	//public Projectile createProjectile(){
		//TODO
	//}
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
	public double getReloadTime(){
		return reloadTime;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getAmmunitionAmount(){
		return ammunitionAmount;
	}
	
	public int missingBullets(){
		return ammunitionAmount-ammunitionInWeapon;
	}
	
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
