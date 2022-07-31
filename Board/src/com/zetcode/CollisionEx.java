package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;
//Class for collision detection 
public class CollisionEx extends JFrame {
	public CollisionEx() {
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("Harold the Hero");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String[] args) {
		
		EventQueue.invokeLater(() -> {
			CollisionEx ex = new CollisionEx();
			ex.setVisible(true);
		});
	}

}
