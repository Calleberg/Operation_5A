package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import model.geometrical.Position;
import model.items.Item;
import model.items.Supply;
import model.items.weapons.Weapon;

/**
 * A class which will render a supply
 * @author Jonatan Magnusson
 *
 */
public class ItemView implements ObjectRenderer<Item>{
	private Item i;
	private static BufferedImage[] textures = Resources.splitImages("supplies.png",5,5);
	
	/**
	 * Create a new ItemView.
	 * @param i the item.
	 */
	public ItemView(Item i){
		this.i = i;
	}

	@Override
	public Item getObject() {
		return i;
	}

	@Override
	public void setObject(Item i) {
		this.i = i;
		
	}

	@Override
	public void render(Graphics g, Position offset, int scale) {
		if(i != null) {
			int x = (int)(i.getPosition().getX() * scale + offset.getX()); 
			int y = (int)(i.getPosition().getY() * scale + offset.getY());
			if(i instanceof Supply){
				Supply s = (Supply) i;
				g.drawImage(textures[s.getType().getIconNumber()], x, y, 
						(int)(s.getCollisionBox().getWidth()*scale), 
						(int)(s.getCollisionBox().getHeight()*scale), null);
			}else if(i instanceof Weapon){
				Weapon w = (Weapon) i;
				g.drawImage(textures[w.getIconNumber()], x, y, 
						(int)(w.getCollisionBox().getWidth()*scale), 
						(int)(w.getCollisionBox().getHeight()*scale), null);
			}
		}
	}
	/**
	 * returns all textures used to draw supplies
	 * @return all textures needed to draw supplies
	 */
	public BufferedImage[] getImages(){
		return textures;
	}
}
