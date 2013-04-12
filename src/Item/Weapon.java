package Item;

public class Weapon {
	private double speed;
	private int damage;
	private int range;
	private int ammunitionAmount;
	private int ammunitionInWeapon;
	private double reloadTime;
	
	public Weapon(double speed, int damage, int range, int ammunitionAmount, 
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
	
	public double getSpeed(){
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
}
