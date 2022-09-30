/*
 * Evan Harrigan
 * CSA
 * CMDR Schenk
 * Master Project
 * 4/24/2020
 */

package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Tower extends Sprite {
	
	//tower fields
	Image image;
	private boolean isPlaced;
	public ArrayList<Laser> lasers;
	
	public Tower(int x, int y, int speed, boolean isPlaced) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/game/assets/images/tower2.png")).getImage();
		this.isPlaced = isPlaced;
		this.lasers = new ArrayList<>();
	}

	@Override
	protected void draw(Graphics2D g2D) {
		g2D.drawImage(this.image, (int)getX(), (int)getY(), null);
	}

	//getters and setters
	public boolean getIsPlaced() {
		return isPlaced;
	}
	
	public void setIsPlaced(boolean isPlaced){
		this.isPlaced = isPlaced;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public ArrayList<Laser> getLasers() {
		return lasers;
	}

	public void setLasers(ArrayList<Laser> lasers) {
		this.lasers = lasers;
	}

	//returns the nearest virus to tower
	public Virus nearestVirus(ArrayList<Virus> viruses) {
		Virus nearest = viruses.get(0);
		for(int i = 0; i < viruses.size(); i++) {
			if(distance(viruses.get(i)) < distance(nearest)) {
				nearest = viruses.get(i);
			}
		}
		return nearest;
	}
	
	//finds distance of a virus to tower
	public double distance(Virus v) {
		double distance = Math.pow(v.getY() - this.getY(), 2) + Math.pow(v.getX() - this.getX(), 2);
		return distance;
	}
}
