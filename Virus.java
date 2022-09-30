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

public class Virus extends Sprite {
	
	//virus fields
	private final Image image;
	private int health;
	
	public Virus(int x, int y, int speed) {
		super(x, y, speed);
		this.image = new ImageIcon(getClass().getResource("/game/assets/images/virus.png")).getImage();
		this.health = 100;
	}

	@Override
	protected void draw(Graphics2D g2D) {
		g2D.drawImage(this.image, (int)getX(), (int)getY(), null);
		
	}
	
	//update methods for moving virus along track
	public void update1() {
		setY(getY() + getSpeed());
	}
	
	public void update2() {
		setX(getX() + getSpeed());
	}
	
	public void update3() {
		setY(getY() - getSpeed());
	}

	public void update4() {
		setX(getX() + getSpeed());
	}
	
	public void update5() {
		setY(getY() + getSpeed());
	}

	//get and set health
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
