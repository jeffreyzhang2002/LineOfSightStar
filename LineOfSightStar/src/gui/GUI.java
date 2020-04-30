package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GUI extends JFrame
{
	
	private static final long serialVersionUID = -8896469688432037130L;
	
	MenuBar menu;
	StatusBar status;
	MainDisplay main;
	
	public GUI()
	{
		menu = new MenuBar(this);
		status = new StatusBar(this);
		main = new MainDisplay(this, new Dimension(700,500));
		
		super.getContentPane().add(BorderLayout.NORTH, menu);
		super.getContentPane().add(BorderLayout.CENTER, main);
		super.getContentPane().add(BorderLayout.SOUTH, status);
	}
}
