package com.zetcode;

public class Alien extends Sprite {
	private final int INITIAL_X = 900;
	
	public Alien(int x, int y) {
		super(x, y);
		
		initAlien();
	}
	
	private void initAlien() {
		loadImage("src/lesgo.png");
		getImageDimensions();
	}
	
	public void move() {
		if(x < 0) {
			x = INITIAL_X;
		}
		x -= 1;
	}

}
