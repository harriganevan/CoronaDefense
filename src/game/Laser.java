package game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Laser extends Sprite {
	
	//laser fields
	private final Image image;
	private int health;
	private Virus virus;
	
	public Laser(double d, double e, double speed, Virus virus) {
		super(d, e, speed);
		this.image = new ImageIcon(getClass().getResource("/game/assets/images/laserNew.png")).getImage();
		this.health = 100;
		this.virus = virus;
	}

	@Override
	protected void draw(Graphics2D g2D) {
		g2D.drawImage(this.image, (int)getX(), (int)getY(), null);
		
	}

	//getters and setters
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Virus getVirus() {
		return virus;
	}

	public void setVirus(Virus virus) {
		this.virus = virus;
	}

	//changing position of laser
	public void update() {
		double angle = Math.atan((this.getY() - this.virus.getY())/(this.getX() - this.virus.getX()));
		if(this.virus.getX() > this.getX()) {
			setY(getY() + (getSpeed() * Math.sin(angle)));
			setX(getX() + (getSpeed() * Math.cos(angle)));
		}
		else {
			setY(getY() - (getSpeed() * Math.sin(angle)));
			setX(getX() - (getSpeed() * Math.cos(angle)));
		}
	}
	
	//returns if laser hits virus
	public boolean isClose() {
		return ((Math.abs(this.getY() - this.virus.getY())<10) && (Math.abs(this.getX() - this.virus.getX())<10));
	}
}
