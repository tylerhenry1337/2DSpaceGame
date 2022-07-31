package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private SpaceShip spaceship;
	private List<Alien> aliens;
	private boolean ingame;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 1000;
	private final int B_HEIGHT = 1000;
	private final int DELAY = 15;
	//private final JButton restartButton;
	
	private final int[][] pos = {   {2380, 1000}, {2500, 600}, {1380, 89},
	        {780, 250}, {580, 139}, {680, 500},
	        {790, 500}, {760, 50}, {790, 150},
	        {980, 209}, {560, 450}, {510, 720},
	        {930, 159}, {590, 800}, {530, 60},
	        {940, 700}, {990, 300}, {920, 200},
	        {900, 259}, {660, 50}, {540, 90},
	        {810, 220}, {860, 300}, {740, 180},
	        {820, 128}, {490, 170}, {700, 400}
			
	};
	
	public Board() {
		
		initBoard();
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		spaceship = new SpaceShip(ICRAFT_X, ICRAFT_Y);
		
		initAliens();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void initAliens() {
		
		aliens = new ArrayList<>();
		
		for(int[] p : pos) {
			aliens.add(new Alien(p[0], p[1]));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(ingame) {
			drawObjects(g);
		}
		else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawObjects(Graphics g) {
		
		if(spaceship.isVisible()) {
			g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);	
		}
		
		List<Missile> ms = spaceship.getMissiles();
		
		for(Missile missile : ms) {
			if(missile.isVisible()) {
				g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
			}
		}
		
		for (Alien alien : aliens) {
			if(alien.isVisible()) {
				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Aliens left: " + aliens.size(), 5, 15);
	}
	
	private void drawGameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Comic Sans", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg))/2, B_HEIGHT /2);
		
		g.setFont(small);
		g.drawString("press ENTER to restart", (1000 - fm.stringWidth(msg))/2, 1200 /2);
		
	}
	
	/*private void addRestartButton() {
		String buttonText = "new game";
		restartButton = new JButton(buttonText);
		Font font = new Font("Helvetica", Font.BOLD, 14);
		restartButton.setFont(font);
		
		setLayout(null);
		restartButton.setBounds(x,y,w,z);
		add(restartButton);
		
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		setButtonVisibility(false);
	}*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		
		updateShip();
		updateMissiles();
		updateAliens();
		
		checkCollisions();
		
		repaint();
	}
	
	private void inGame() {
		if(!ingame) {
			timer.stop();
		}
	}
	
	private void updateShip() {
		if(spaceship.isVisible()) {
			spaceship.move();
		}
	}
	
	private void updateMissiles() {
		List<Missile> ms = spaceship.getMissiles();
		for(int i = 0; i < ms.size(); i++) {
			Missile m = ms.get(i);
			
			if (m.isVisible()) {
				m.move();
			}
			else {
				ms.remove(i);
			}
		}
	}
	
	private void updateAliens() {
		if(aliens.isEmpty()) {
			
			ingame = false;
			return;
		}
		for(int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			
			if(a.isVisible()) {
				a.move();
			}
			else {
				aliens.remove(i);
			}
		}
		
	}
	
	public void checkCollisions() {
		Rectangle r3 = spaceship.getBounds();
		for(Alien alien : aliens) {
			Rectangle r2 = alien.getBounds();
			if(r3.intersects(r2)) {
				spaceship.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
		
		List<Missile> ms = spaceship.getMissiles();
		for(Missile m : ms) {
			Rectangle r1 = m.getBounds();
			for(Alien alien : aliens) {
				Rectangle r2 = alien.getBounds();
				if(r1.intersects(r2)) {
					m.setVisible(false);
					alien.setVisible(false);
				}
			}
			
		}
	}
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			spaceship.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			spaceship.keyPressed(e);
		}
	}
}
//This code is for the board for harold hero movement and shooting missiles
/*public class Board extends JPanel implements ActionListener{
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int DELAY = 10;
	private Timer timer;
	private SpaceShip spaceShip;
	
	public Board() {
		
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setFocusable(true);
		
		spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),spaceShip.getY(), this);
		
		List<Missile> missiles = spaceShip.getMissiles();
		
		for(Missile missile : missiles) {
			g2d.drawImage(missile.getImage(),missile.getX(), missile.getY(), this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updateMissiles();
		updateSpaceShip();
		
		repaint();
	}
	
	private void updateMissiles() {
		List<Missile> missiles = spaceShip.getMissiles();
		
		for(int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			
			if (missile.isVisible()) {
				missile.move();
			}
			else {
				missiles.remove(i);
			}
		}

	}
	
	private void updateSpaceShip() {
		spaceShip.move();
	}
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			spaceShip.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			spaceShip.keyPressed(e);
		}
	}
}*/
//This code allowed harold to be controlled with up down left and right key
/*public class Board extends JPanel implements ActionListener {
	private Timer timer;
	private SpaceShip spaceShip;
	private final int DELAY = 10;
	public Board() {
		initBoard();
	}
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		spaceShip = new SpaceShip();
		timer = new Timer(DELAY, this);
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(spaceShip.getImage(),spaceShip.getX(), spaceShip.getY(), this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
	private void step() {
		spaceShip.move();
		repaint(spaceShip.getX()-1,spaceShip.getY()-1,spaceShip.getWidth()+2, spaceShip.getHeight()+2);
	}
	private class TAdapter extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			spaceShip.keyPressed(e);
		}
	}
}*/
	
	// This code allows harold to slowly present himself accross the screeen
	/*Board extends JPanel implements Runnable
	private final int B_WIDTH = 750;
	private final int B_HEIGHT = 500;
	private final int INITIAL_X = -40;
	private final int INITIAL_Y = -40;
	private final int DELAY = 40;	
	private Image star;
	private Thread animator;
	private int x, y;
	
	public Board() {
		initBoard();
	}	
	private void loadImage() {
		ImageIcon ii =new ImageIcon("src/istockphoto-153696622-170667a.jpg");
		star = ii.getImage();
				
	}
	private void initBoard() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		loadImage();
		x = INITIAL_X;
		y = INITIAL_Y;	
	}
	@Override
	public void addNotify() {
		super.addNotify();
		animator = new Thread(this);
		animator.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		drawStar(g);
	}
	private void drawStar(Graphics g) {
		g.drawImage(star, x, y, this);
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void cycle() {
		x += 1;
		y += 1;
		if(y>B_HEIGHT) {
			y = INITIAL_Y;
			x = INITIAL_X;
		}
	}
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();
		while(true) {
			cycle();
			repaint();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			if(sleep < 0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			}
			catch(InterruptedException e) {
				String msg = String.format("Thread interruped: %s", e.getMessage());
				JOptionPane.showMessageDialog(this, msg,"Error", JOptionPane.ERROR_MESSAGE);
			}
			beforeTime = System.currentTimeMillis();
		}
	}
*/	

//This code uses a frame object to display a picture of harold
/*
	private Image pic;
	public Board() {
		initBoard();
	}
	private void initBoard() {
		loadImage();
		
		int w = pic.getWidth(this);
		int h = pic.getHeight(this);
		setPreferredSize(new Dimension(w, h));
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("src/istockphoto-153696622-170667a.jpg");
		pic = ii.getImage();
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(pic, 0,0,null);
	}
	*/
	
//this code displays a 2d doughnut shape
	/*private void drawDonut(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		
		Dimension size = getSize();
		double w = size.getWidth();
		double h = size.getHeight();
		
		Ellipse2D e = new Ellipse2D.Double(0,0,80,130);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);
		
		for(double deg = 0; deg < 360; deg +=5) {
			AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
			at.rotate(Math.toRadians(deg));
			g2d.draw(at.createTransformedShape(e));
		}*/

