package com.zetcode;
/*import java.awt.EventQueue;
import javax.swing.JFrame;

public class Applications extends JFrame {
	public Applications() {
		initUI();
	}
	private void initUI() {
		add(new Board());
		setSize(250,200);
		setTitle("Dick hole piercings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Applications ex = new Applications();
			ex.setVisible(true);
		});
	}

}
