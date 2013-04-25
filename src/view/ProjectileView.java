package view;

import java.awt.Graphics;

import model.geometrical.Position;
import model.items.weapons.Projectile;

/**
 * A class which will render a projectile.
 * 
 * @author Calleberg
 *
 */
public class ProjectileView implements ObjectRenderer<Projectile> {

	private Projectile p;
	
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
	public void render(Graphics g, Position offset, int scale) {
		if(p != null) {
			int x = (int)(p.getPosition().getX() * scale + offset.getX()); 
			int y = (int)(p.getPosition().getY() * scale + offset.getY());
			g.drawRect(x, y, (int)(p.getCollisionBox().getWidth()*scale), 
					(int)(p.getCollisionBox().getHeight()*scale));
		}
	}
}
