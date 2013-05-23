package view;

import java.awt.Color;
import java.awt.Graphics;

import model.geometrical.Position;
import model.items.weapons.Projectile;

/**
 * A class which will render a projectile.
 * 
 * @author Martin Calleberg
 *
 */
public class ProjectileView implements ObjectRenderer<Projectile> {

	private Projectile p;
	private Position lastPos;
	
	/**
	 * Creates a new projectile view which will render the specified projectile.
	 * @param p the projectile to render.
	 */
	public ProjectileView(Projectile p){
		this.p = p;
	}

	@Override
	public Projectile getObject() {
		return p;
	}

	@Override
	public void setObject(Projectile object) {
		this.p = object;
	}
	
	@Override
	public int getLayer() {
		return 5;
	}

	@Override
	public void render(Graphics g, Position offset, int defaultSize, float scale) {
		if(p != null && p.isVisible()) {			
			if(lastPos != null) {
				float size = defaultSize * scale;
				g.setColor(Color.ORANGE);
				g.drawLine((int)(p.getPosition().getX() * size + offset.getX()),
						(int)(p.getPosition().getY() * size + offset.getY()),
						(int)(lastPos.getX() * size + offset.getX()),
						(int)(lastPos.getY() * size + offset.getY()));
			}
			
			lastPos = p.getPosition();
		}
	}
}
