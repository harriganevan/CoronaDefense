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
import javax.swing.ImageIcon;

//background object inherits fields from Sprite
public class Background extends Sprite {
	
	Image image;

	public Background(int x, int y, int speed) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/game/assets/images/coronaDefenseBG.png")).getImage();
	}

	@Override
	protected void draw(Graphics2D g2D) {
		g2D.drawImage(this.image, (int)getX(), (int)getY(), null);
		
	}

}
