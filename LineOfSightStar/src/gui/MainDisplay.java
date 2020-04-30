package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MainDisplay extends JPanel
{
	private static final long serialVersionUID = -6660083446050112398L;
	
	private GUI gui;
	private GridBagConstraints mainDisplayConstraints;
	private GridBagConstraints sideDisplayConstraints;
	
	Color borderColor = Color.white;
	
	FieldDisplay fieldContent;
	KeyPointsDisplay keyPointsContent;
	BarriersDisplayBar barriersContent;
	ConsoleDisplay consoleContent;
	
	public MainDisplay(GUI gui, Dimension dimensions)
	{
		this.gui = gui;
		fieldContent = new FieldDisplay(gui);
		keyPointsContent = new KeyPointsDisplay(gui);
		barriersContent = new BarriersDisplayBar(gui);
		consoleContent = new ConsoleDisplay(gui);
		
		MainDisplayMouseActionListener mouseListener = new MainDisplayMouseActionListener();
		super.addMouseListener(mouseListener);
		super.addMouseMotionListener(mouseListener);
		super.addMouseWheelListener(mouseListener);
		
		super.setSize(dimensions);
        super.setLayout(new GridBagLayout());
		
        int width;
        if(super.getWidth() == super.getHeight())
            width = (int) (super.getWidth() * 0.75);
        else
            width = Math.min(super.getWidth(), super.getHeight());
        
        BevelBorder border = new BevelBorder(BevelBorder.LOWERED, borderColor, borderColor);
        
        fieldContent.setSize(new Dimension(width, width));
        fieldContent.setBorder(border);
        
        keyPointsContent.setSize(new Dimension(super.getWidth() - width,width));
        keyPointsContent.setBorder(border);
        
        initalizeFieldDisplayContraints();
        initalizeSideDisplayContraints(width);
        
        super.add(fieldContent,     mainDisplayConstraints);
        super.add(consoleContent, sideDisplayConstraints);
	
	}
	
	private void initalizeFieldDisplayContraints()
	{
		mainDisplayConstraints = new GridBagConstraints();
		mainDisplayConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		mainDisplayConstraints.gridx = 0;
		mainDisplayConstraints.gridy = 0;
		mainDisplayConstraints.gridwidth = 1;
		mainDisplayConstraints.gridheight = 1;
		mainDisplayConstraints.ipadx = fieldContent.getWidth();
		mainDisplayConstraints.ipady = fieldContent.getHeight();
	}
	
	private void initalizeSideDisplayContraints(int width)
	{
		sideDisplayConstraints = new GridBagConstraints();
		sideDisplayConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		sideDisplayConstraints.gridx = 1;
		sideDisplayConstraints.gridy = 0;
		sideDisplayConstraints.gridwidth = 1;
		sideDisplayConstraints.gridheight = 1;
		sideDisplayConstraints.ipadx = super.getWidth() - width;
		sideDisplayConstraints.ipady = width;
	}
	
	private class MainDisplayMouseActionListener implements MouseListener, MouseMotionListener, MouseWheelListener
	{

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			fieldContent.editingBarrierMode = false;
			fieldContent.canEditPathMode = false;
			
		}
		
	}
}
