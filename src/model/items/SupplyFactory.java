package model.items;

/**
 * 
 * @author Vidar Eriksson
 *
 */
public final class SupplyFactory {
	
	/**
	 * Will create a completly random supply.
	 * @return a new random supply.
	 */
	public Supply createRandomSupply(){
		Supply s;
		int random=randomNumber();
		if (random<33){
			s=createRandomAmmo();
		} else if (random < 66){
			s=createRandomFood();
		} else {
			s=createRandomHealth();
		}
		return s;
	}

	private Supply createRandomAmmo() {
		return new Supply(randomNumber(), Supply.Type.AMMO);
	}
	private Supply createRandomFood() {
		return new Supply(randomNumber(), Supply.Type.FOOD);
	}
	private Supply createRandomHealth() {
		return new Supply(randomNumber(), Supply.Type.HEALTH);
	}
	private int randomNumber(){
		return (int) Math.random()*100;
	}
}
