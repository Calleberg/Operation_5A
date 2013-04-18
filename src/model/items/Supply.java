package model.items;

import model.geometrical.Position;
import java.awt.image.BufferedImage;

//TODO mer på enum hämtar saker från bildrepo och beroende på antal...
/**
 * 
 * @author Vidar
 *
 */
public class Supply implements Item {
	
	public enum Type {
		FOOD (null),
		AMMO (null),//TODO få dessa att hämta riktiga bilder
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
	private Position pos;

	/**
	 * 
	 * @param amount
	 * @param type
	 * @param position
	 * @param image
	 */
	public Supply(int amount, Type type, Position position){
		this.amount=amount;
		this.pos=position;
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

	public void setX(float x) {
		this.pos.setX(x);
		
	}
	@Override
	public float getX() {
		return this.pos.getX();
	}
	@Override
	public void setY(float y) {
		this.pos.setY(y);
		
	}	
	@Override
	public float getY() {
		return this.pos.getY();
	}
	
	@Override
	public Position getPosition() {
		return pos;
	}
	
	@Override
	public void setPosition(Position p) {
		this.pos =p;
	}
	
	@Override
	public void setImage(BufferedImage i) {
		this.image=i;
		
	}
	@Override
	public BufferedImage getImage() {
		return image;
	}
	
}
