/*
 * Evan Harrigan
 * CSA
 * CMDR Schenk
 * Master Project
 * 4/24/2020
 */

package game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

//where game operations are handled
public class GamePanel extends Canvas implements Runnable {
	
	private Thread gameThread;
	private boolean isRunning;
	private int lives;
	
	//creating sprite objects
	private final Background background = new Background(0, 0, 0);
	private final Number number = new Number(585, -12, 0);
	private final ArrayList<Virus> viruses = new ArrayList<>();
	private final ArrayList<Tower> towers = new ArrayList<>();
	private final ArrayList<TowerNumber> towerNumbers = new ArrayList<>();
	private final ArrayList<Laser> lasers = new ArrayList<>();
		
	//setting size of game panel
	public GamePanel() {
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
	}
	
	//gets called immediately 
	public void addNotify() {
		super.addNotify();
		if(gameThread == null) {
			gameThread = new Thread(GamePanel.this);
		}
		gameThread.start();
	}

	@Override
	protected void onKeyUp(KeyEvent event) {
		
	}

	@Override
	protected void onKeyPressed(KeyEvent event) { 
		if(event.getKeyCode() == KeyEvent.VK_P) { //when P is pressed
			viruses.add(new Virus(68, 0, 3));
		}
		if(event.getKeyCode() == KeyEvent.VK_1) { //when 1 is pressed
			change(5);
		}
		if(event.getKeyCode() == KeyEvent.VK_2) { //when 2 is pressed
			change(4);
		}
		if(event.getKeyCode() == KeyEvent.VK_3) { //when 3 is pressed
			change(6);
		}
		if(event.getKeyCode() == KeyEvent.VK_4) { //when 4 is pressed
			change(3);
		}
		if(event.getKeyCode() == KeyEvent.VK_5) { //when 5 is pressed
			change(2);
		}
		if(event.getKeyCode() == KeyEvent.VK_6) { //when 6 is pressed
			change(1);
		}
		if(event.getKeyCode() == KeyEvent.VK_7) { //when 7 is pressed
			change(0);
		}
	}
	
	//swaps towers isPlaced
	public void change(int i) {
		if(towers.get(i).getIsPlaced() == false) {
			towers.get(i).setIsPlaced(true);
		}
		else {
			towers.get(i).setIsPlaced(false);
		}
	}

	//drawing sprites
	@Override
	protected void onDraw(Graphics2D g2D) { 
		background.draw(g2D);
		number.draw(g2D);
		if(towers != null) {
			for(Tower tower: towers) {
				tower.draw(g2D);
				ArrayList<Laser> l = tower.getLasers();
				if(l != null && tower.getIsPlaced()) {
					for(Laser lasers: l) {
						lasers.draw(g2D);
					}
				}
			}
		}
		if(towerNumbers != null) {
			for(TowerNumber towerNumber: towerNumbers) {
				towerNumber.draw(g2D);
			}
		}
		if(viruses != null) {
			for(Virus virus: viruses) { 
				virus.draw(g2D);
			}
		}
	}

	@Override
	public void run() {
		init(); //calling initializing method
		while(isRunning) {
			
			long startTime = System.currentTimeMillis();
			
			updateGame();
			renderGame();
			
			long endTime = System.currentTimeMillis() - startTime;
			long waitTime = (MILLISECOND/FPS) - endTime / MILLISECOND;
			
			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}
		}
	}

	//initialize method
	public void init() {
		isRunning = true;
		lives = 5; //setting lives
		towers.add(new Tower(625, 50, 0, false)); //adding towers and tower numbers
		towers.add(new Tower(455, 130, 0, false));
		towers.add(new Tower(405, 240, 0, false));
		towers.add(new Tower(355, 130, 0, false));
		towers.add(new Tower(170, 50, 0, false));
		towers.add(new Tower(-10, 50, 0, false));
		towers.add(new Tower(170, 280, 0, false));
		towerNumbers.add(new TowerNumber(-10, 100, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum1.png")).getImage()));
		towerNumbers.add(new TowerNumber(170, 100, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum2.png")).getImage()));
		towerNumbers.add(new TowerNumber(170, 230, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum3.png")).getImage()));
		towerNumbers.add(new TowerNumber(355, 180, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum4.png")).getImage()));
		towerNumbers.add(new TowerNumber(405, 190, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum5.png")).getImage()));
		towerNumbers.add(new TowerNumber(455, 180, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum6.png")).getImage()));
		towerNumbers.add(new TowerNumber(625, 100, 0, new ImageIcon(getClass().getResource("/game/assets/images/towerNum7.png")).getImage()));
	}
	
	//handling tower operations
	public void towerSet(Tower t) {
		if(t.getIsPlaced() == true) { //if the tower is placed
			t.setImage(new ImageIcon(getClass().getResource("/game/assets/images/tower.png")).getImage());
			if(t.getLasers().size() < 1 && viruses.size() != 0) { //giving each tower its own laser
				ArrayList<Laser> l = t.getLasers();
				l.add(new Laser(t.getX(), t.getY(), 4.5, t.nearestVirus(viruses))); //assigning tower the closest laser
				t.setLasers(l);
			}
			if(t.getLasers().size() != 0) {
				Laser laser = t.getLasers().get(0);
				if(laser.isClose()) { //if the laser hits the virus
					t.getLasers().remove(0);
					laser.getVirus().setHealth(laser.getVirus().getHealth() - 50);
				}
				else {
					laser.update(); //moving  laser
				}
			}
		}
		else { //if the tower is not placed
			t.setImage(new ImageIcon(getClass().getResource("/game/assets/images/tower2.png")).getImage());
			ArrayList<Laser> l = t.getLasers();
			l.clear();
			t.setLasers(l);
		}
	}

	public void updateGame() {
	
		//handling all towers
		towerSet(towers.get(0));
		towerSet(towers.get(1));
		towerSet(towers.get(2));
		towerSet(towers.get(3));
		towerSet(towers.get(4));
		towerSet(towers.get(5));
		towerSet(towers.get(6));
		
		
		//moving virus across track
		for(int i = 0; i < viruses.size(); i++) {
			Virus virus = viruses.get(i);
			if(virus.getHealth() < 1) {
				viruses.remove(virus);
			}
			else {
				if(virus.getY() < 375 && virus.getX() < 200) {
					virus.update1();
				}
				if(virus.getY() > 374 && virus.getX() < 275) {
					virus.update2();
				}
				if(virus.getX() > 274 && virus.getX() < 276) {
					virus.update3();
				}
				if(virus.getY() < 45 && virus.getX() > 274) {
					virus.update4();
				}
				if(virus.getX() > 530) {
					virus.update5();
				}
				if(virus.getY()>500) { //if virus gets to end
					viruses.remove(i);
					lives--;
					if(lives < 1) { //if all lives are lost
						viruses.removeAll(viruses);
						towers.removeAll(towers);
						towerNumbers.removeAll(towerNumbers);
						lasers.clear();
						number.image = null;
						background.image = new ImageIcon(getClass().getResource("/game/assets/images/gameOver.png")).getImage();
						isRunning = false;
					}
				}
			}
			//changing lives number
			if(lives == 4) {
				number.image = new ImageIcon(getClass().getResource("/game/assets/images/4.png")).getImage();
			}
			if(lives == 3) {
				number.image = new ImageIcon(getClass().getResource("/game/assets/images/3.png")).getImage();
			}
			if(lives == 2) {
				number.image = new ImageIcon(getClass().getResource("/game/assets/images/2.png")).getImage();
			}
			if(lives == 1) {
				number.image = new ImageIcon(getClass().getResource("/game/assets/images/1.png")).getImage();
			}	
		}
	}
	
	public void renderGame() {
		repaint();
	}
}
