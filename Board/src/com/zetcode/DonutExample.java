package com.zetcode;
/*
import java.awt.EventQueue;
import javax.swing.JFrame;

public class DonutExample extends JFrame {
	public DonutExample() {
		initUI();
	}
	private void initUI() {
		add(new Board());
		setSize(500,380);
		setTitle("Donut mouthed bitchface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			DonutExample ex = new DonutExample();
			ex.setVisible(true);
		});
	}

}
