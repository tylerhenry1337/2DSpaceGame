package com.zetcode;
//Main class for shooting harold hero shooting missiles
import java.awt.EventQueue;
import javax.swing.JFrame;

public class ShootingMissilesEx extends JFrame {
	public ShootingMissilesEx() {
		
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		
		setSize(1000,1000);
		setResizable(false);
		
		setTitle("Shooting missiles");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ShootingMissilesEx ex = new ShootingMissilesEx();
			ex.setVisible(true);
		});
	}

}
