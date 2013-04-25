package model.items;

import model.geometrical.Position;
import java.awt.image.BufferedImage;

//TODO mer p� enum h�mtar saker fr�n bildrepo och beroende p� antal...
/**
 * 
 * @author Vidar
 *
 */
public class Supply extends Item {
	
	public enum Type {
		FOOD (null),
		AMMO (null),//TODO f� dessa att h�mta riktiga bilder
		HEALTH (null);
		
		private final BufferedImage i;
		Type (BufferedImage i){
			this.i=i;
		}
		
		public BufferedImage getImage(){
			return this.i;
		}
	}
	
	private int amount;
	private BufferedImage image;
	private Type type;

	/**
	 * 
	 * @param amount
	 * @param type
	 * @param position
	 * @param image
	 */
	public Supply(int amount, Type type, Position position){
		super(position);
		this.amount=amount;
		this.type=type;
		generateImage();
	}
	
	private void generateImage(){
		//TODO
		
	}
	/**
	 * 
	 * @return the amount of food gained from the Supply
	 */
	public int getAmount(){
		return amount;
	}
	
	public Type getType(){
		return type;
		
	}
	
}
