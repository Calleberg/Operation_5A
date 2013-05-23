package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


import model.geometrical.Position;
import model.items.Item;

/**
 * A class which will render a item.
 * @author Jonatan Magnusson and Martin Calleberg
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
	public int getLayer() {
		return 2;
	}

	@Override
	public void render(Graphics g, Position offset, int defaultSize, float scale) {
		if(i != null) {
			int x = (int)(i.getPosition().getX() * defaultSize * scale + offset.getX()); 
			int y = (int)(i.getPosition().getY() * defaultSize * scale + offset.getY());

			Graphics2D g2d = (Graphics2D)g;
			AffineTransform transformer = (AffineTransform)g2d.getTransform().clone();
			transformer.concatenate(AffineTransform.getTranslateInstance(x, y));
			transformer.concatenate(AffineTransform.getScaleInstance(scale, scale));

			g2d.drawImage(textures[i.getIconNumber()], transformer, null);
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
