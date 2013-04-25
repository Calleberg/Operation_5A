package model.items.weapons;

/**
 * Creates different types of weapons.
 * @author Vidar Eriksson
 *
 */
public class WeaponFactory {
	public static enum Type{
		//Range
		PISTOL (1,2,3,4,5,6,7, "Pistol"),
//		SHOTGUN,
//		REVOLVER,
//		HUNTING_RIFLE,
//		MACHINEGUN,
//		MINIGUN,
//		ROCKET_LAUNCHER,
		
		//Melee
		FISTS (1000,1,1,4,5,6,7, "Fists"),
//		POCKET_KNIFE,
//		MACHETTE,
//		BAT,
//		PIPE,
		
		TEST_WEAPON (0.1f,2,100f,100000000,5,1000,1337, "Test weapon");
		
		private final float projectileSpeed;
		private final int damage;
		private final float range;
		private final int magazineCapacity;
		private final int reloadTime;
		private final int rateOfFire;
		private final int iconNumber;
		private final String name;
		Type(float projectileSpeed,
				int damage,
				float range,
				int magazineCapacity,
				int reloadTime,
				int rateOfFire,
				int iconNumber,
				String name){
			this.projectileSpeed = projectileSpeed;
			this.damage = damage;
			this.range = range;
			this.magazineCapacity = magazineCapacity;
			this.reloadTime = reloadTime;
			this.rateOfFire=rateOfFire;
			this.iconNumber=iconNumber;
			this.name=name;
		}

		public float getProjectileSpeed() {
			return projectileSpeed;
		}

		public int getDamage() {
			return damage;
		}

		public float getRange() {
			return range;
		}

		public int getMagazineCapacity() {
			return magazineCapacity;
		}

		public int getReloadTime() {
			return reloadTime;
		}

		public int getRateOfFire() {
			return rateOfFire;
		}

		public int getIconNumber() {
			return iconNumber;
		}
		@Override
		public String toString() {
			return name;
		}
		
		
		
		
	}
	public static enum Level{
		RUSTY (1, 50, "Rusty"),
		NORMAL (3, 25, "Normal"),
		LARGE (5, 15, "Large"),
		BADASS (7, 8, "Badass"),
		EPIC (10, 2, "Epic");
		
		private final int multiplier;
		private final int percentage;
		private final String name;
		Level (int i, int j, String n){
			this.multiplier=i;
			this.percentage=j;
			this.name=n;
		}
		
		public int percentage(){
			return percentage;
		}
		
		public int multiplier() {
			return multiplier;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	public static Weapon CreateWeapon(Type type, Level level) {
		return new Weapon(
				type.getProjectileSpeed(),
				type.getDamage()*level.multiplier(),
				type.getRange(),
				type.getMagazineCapacity(),
				type.getReloadTime()/level.multiplier(),
				type.getRateOfFire(),
				type.getIconNumber(),
				level.toString() + " " + type.toString()
				);
	}
	/**
	 * Creates a test weapon for debugging.
	 * @return a test weapon for debugging.
	 */
	public static Weapon CreateTestWeapon() {
		return CreateWeapon(Type.TEST_WEAPON, Level.EPIC);
	}

	/**
	 * Creates the weapon enemies uses
	 * @return the weapon enemies uses.
	 */
	public static Weapon createEnemyMeleeWeapon(){
		return CreateWeapon(Type.FISTS, Level.RUSTY);
	}
}
