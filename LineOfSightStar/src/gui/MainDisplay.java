package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;

class MainDisplay extends JPanel
{
	private static final long serialVersionUID = 1L;
	
    private FieldDisplay fieldContent = new FieldDisplay();
    private ConsoleDisplay consoleContent = new ConsoleDisplay();
    static Color borderColor = Color.white;
    
    public MainDisplay(Dimension dim)
    {
    	BevelBorder border = new BevelBorder(BevelBorder.LOWERED, borderColor, borderColor);
    	
        super.setSize(dim);
        super.setLayout(new GridBagLayout());

        int width;
        if(super.getWidth() == super.getHeight())
            width = (int) (super.getWidth() * 0.75);
        else
            width = Math.min(super.getWidth(), super.getHeight());

        fieldContent.setSize(new Dimension(width, width));
        fieldContent.setBorder(border);
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = fieldContent.getWidth();
        c.ipady = fieldContent.getHeight();

        consoleContent.setSize(new Dimension(super.getWidth() - width,width));
        consoleContent.setBorder(border);
        
        GridBagConstraints d = new GridBagConstraints();
        d.anchor = GridBagConstraints.FIRST_LINE_END;
        d.gridx = 1;
        d.gridy = 0;
        d.gridwidth = 1;
        d.gridheight = 1;
        d.ipadx = consoleContent.getWidth();
        d.ipady = consoleContent.getHeight();

        super.add(fieldContent, c);
        super.add(consoleContent, d);
    }
}
