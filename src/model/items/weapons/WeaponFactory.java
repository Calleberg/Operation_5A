package model.items.weapons;


public class WeaponFactory {
	public enum Type{
		//Range
		PISTOL,
		SHOTGUN,
		REVOLVER,
		HUNTING_RIFLE,
		MACHINEGUN,
		MINIGUN,
		ROCKET_LAUNCHER,
		//Melee
		FISTS,
		POCKET_KNIFE,
		MACHETTE,
		BAT,
		PIPE;

		public float getProjectileSpeed() {
			// TODO Auto-generated method stub
			return 0.1f;
		}

		public int getDamage() {
			// TODO Auto-generated method stub
			return 1;
		}

		public float getRange() {
			// TODO Auto-generated method stub
			return 100f;
		}

		public int getMagazineCapacity() {
			// TODO Auto-generated method stub
//			return 1;
//			return 2147483647;
			return 100000000;
		}

		public int getReloadTime() {
			// TODO Auto-generated method stub
			return 10;
		}

		public int getRateOfFire() {
			// TODO Auto-generated method stub
			return 1000;
		}
		
		
		
		
		
	}
	public enum Level{
		RUSTY (1),
		NORMAL (3),
		LARGE (5),
		BADASS (7),
		EPIC (10);
		
		private final int multiplier;
		Level (int i){
			this.multiplier=i;
		}
		
		public int percentage() {
			return 1;
		}
		
		public int multiplier() {
			return multiplier;
		}
	}
	public static Weapon CreateWeapon(Type type, Level level) {
		return new Weapon(
				type.getProjectileSpeed(),
				type.getDamage()*level.multiplier(),
				type.getRange()*level.multiplier(),
				type.getMagazineCapacity()*level.multiplier(),
				type.getReloadTime()*2/level.multiplier(),
				type.getRateOfFire()*level.multiplier()
				);
	}
	public static Weapon CreateTestWeapon() {
		return CreateWeapon(Type.MINIGUN, Level.RUSTY);
	}
	public static Weapon CreateTestWeapon2(){
		return CreateTestWeapon();
//		return new Weapon(0.5f, 1, 3f, 10, 10, 1000);
	}
}
