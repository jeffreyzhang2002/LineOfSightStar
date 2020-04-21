package gui;

import javax.swing.*;
import java.awt.*;

class ConsoleDisplay extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	Color textColor = new Color(255, 255, 255); 
	
    public ConsoleDisplay()
    {
    	super.setBackground(Color.BLACK);
    }

    public void paint(Graphics g)
    {
    	super.paint(g);
    	g.setColor(textColor);
    }
}
