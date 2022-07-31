package com.zetcode;
//class for missiles
public class Missile extends Sprite {
	private final int BOARD_WIDTH = 1000;
	private final int MISSILE_SPEED = 5;
	
	public Missile(int x, int y) {
		super(x, y);
		
		initMissle();
	}
	
	private void initMissle() {
		loadImage("src/missile.png");
		getImageDimensions();
	}
	
	public void move() {
		x += MISSILE_SPEED;
		
		if(x > BOARD_WIDTH) {
			visible = false;
		}
	}

}
