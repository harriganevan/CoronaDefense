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

public class TowerNumber extends Sprite {
	
	Image image;
	
	public TowerNumber(int x, int y, int speed, Image image) {
		super(x, y, speed);
		this.image = image;
	}

	@Override
	protected void draw(Graphics2D g2D) {
		g2D.drawImage(this.image, (int)getX(), (int)getY(), null);
	}

}
