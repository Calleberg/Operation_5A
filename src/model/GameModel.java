package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import sprite.Player;
import world.World;

public class GameModel {
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final World world;
	private final Player player;
	
	public GameModel(){
		world = new World();
		player = new Player(1,1);
		world.addSprite(player);
	}
	
	public void addListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}
	
	public void removeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void update() {
		world.update();
		pcs.firePropertyChange("", 1, 0);
	}
}
