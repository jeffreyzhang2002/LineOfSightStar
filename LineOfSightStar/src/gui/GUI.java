package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private MenuBar menuBar = new MenuBar();
	private MainDisplay mainDisplay = new MainDisplay(new Dimension(700,500));
	
	public GUI()
	{
		super.getContentPane().add(BorderLayout.NORTH, menuBar);
		super.getContentPane().add(BorderLayout.CENTER, mainDisplay);
	}
}
