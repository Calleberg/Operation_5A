package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controller.IO.Resources;

import model.geometrical.Position;
import model.items.Supply;

/**
 * A class which will render a supply
 * @author Jonatan Magnusson
 *
 */
public class SupplyView implements ObjectRenderer<Supply>{
	private Supply s;
	private static BufferedImage[] textures = Resources.splitImages("supplies.png",5,5);
	
	public SupplyView(Supply s){
		this.s = s;
	}

	@Override
	public Supply getObject() {
		return s;
	}

	@Override
	public void setObject(Supply s) {
		this.s = s;
		
	}

	@Override
	public void render(Graphics g, Position offset, int scale) {
		if(s != null) {
			int x = (int)(s.getPosition().getX() * scale + offset.getX()); 
			int y = (int)(s.getPosition().getY() * scale + offset.getY());
			g.drawImage(textures[s.getType().getIconNumber()-1], x, y, 
					(int)(s.getCollisionBox().getWidth()*scale), 
					(int)(s.getCollisionBox().getHeight()*scale), null);
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
