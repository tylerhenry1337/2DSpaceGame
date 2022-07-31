package com.zetcode;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
// This class makes the space ship move and fire
public class SpaceShip extends Sprite {
	private int dx;
	private int dy;
	private List<Missile>missiles;
	
	
	
	public SpaceShip(int x, int y) {	
		super(x, y);
		
		initCraft();
	}
	private void initCraft() {
		missiles = new ArrayList<>();
		
		loadImage("src/heroharold.png");
		getImageDimensions();
	}
	public void move() {
		x += dx;
		y += dy;
		
		if(x < 1) {
			x = 1;
		}
		
		if (y < 1) {
			y = 1;
		}
		
	}
	
	public List<Missile> getMissiles(){
		return missiles;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
		if(key == KeyEvent.VK_LEFT) {
			dx = -8;
		}
		if(key == KeyEvent.VK_RIGHT) {
			dx = 8;
		}
		if (key == KeyEvent.VK_UP) {
			dy = -8;
		}
		if(key == KeyEvent.VK_DOWN) {
			dy = 8;
		}
		if(key == KeyEvent.VK_ENTER) {
			reset();
		}
	}
	
	public void fire() {
		missiles.add(new Missile(x + width, y + height/2));
	}
	public void reset() {
		
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}


}
