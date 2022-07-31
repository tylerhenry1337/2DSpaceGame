package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class UtilityTimerEx extends JFrame {
	public UtilityTimerEx(){
		initUI();
	}
	private void initUI() {
		add(new Board());
		setResizable(false);
		pack();
		setTitle("Im out like THREE STRIKES BIIIITTTCCHHHH!!!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame ex = new UtilityTimerEx();
			ex.setVisible(true);
		});
	}

}
