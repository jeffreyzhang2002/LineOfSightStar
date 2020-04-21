package main;

import javax.swing.JFrame;

import gui.GUI;

public class Main {
	
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setVisible(true);
	}

}
