/*
 * Evan Harrigan
 * CSA
 * CMDR Schenk
 * Master Project
 * 4/24/2020
 */

package game;

import java.awt.Graphics2D;

//abstract class for all sprites
public abstract class Sprite implements IHelper {
	
	//fields for all sprites
	private double x;
	private double y;
	private double speed;

	public Sprite(double d, double e, double speed2) {
		this.x = d;
		this.y = e;
		this.speed = speed2;
	}

	//getters and setters
	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double d) {
		this.y = d;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	protected abstract void draw(Graphics2D g2D);

}
