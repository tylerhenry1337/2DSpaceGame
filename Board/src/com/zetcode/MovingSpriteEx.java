package com.zetcode;


import java.awt.EventQueue;
import javax.swing.JFrame;

public class MovingSpriteEx extends JFrame {
	public MovingSpriteEx() {
		initUI();
	}
	private void initUI() {
		add(new Board());
		setTitle("MOVING HAROLDS");
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			MovingSpriteEx ex = new MovingSpriteEx();
			ex.setVisible(true);
		});
	}

}
