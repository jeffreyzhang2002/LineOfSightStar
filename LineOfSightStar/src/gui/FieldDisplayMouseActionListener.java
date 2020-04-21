package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

public class FieldDisplayMouseActionListener implements MouseListener, MouseMotionListener, MouseWheelListener
{
	private FieldDisplay displayer;
	private int index = 0;
	
	public FieldDisplayMouseActionListener(FieldDisplay displayer)
	{
		this.displayer = displayer;
	}
	
	public void mouseDragged(MouseEvent e) 
	{
		displayer.dragLine.x2 = e.getX();
		displayer.dragLine.y2 = e.getY();
		displayer.updateUI();
	}

	public void mouseMoved(MouseEvent e) 
	{
		
	}

	public void mouseClicked(MouseEvent e) 
	{
		if(FieldDisplay.canSetStart)
		{
			displayer.start = e.getPoint();
			FieldDisplay.canSetStart = false;
		} else if(FieldDisplay.canSetEnd) {
			displayer.end = e.getPoint();
			FieldDisplay.canSetEnd = false;
		}
		displayer.updateUI();
	}

	public void mousePressed(MouseEvent e) 
	{
		displayer.dragLine = new Line2D.Double(e.getPoint(),e.getPoint());
	}

	public void mouseReleased(MouseEvent e) 
	{
		if(FieldDisplay.canEditLine)
		{
			displayer.plane.addBarrier(displayer.dragLine);
			displayer.dragLine = null;
			displayer.updateUI();
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		FieldDisplay.canEditLine = false;
		FieldDisplay.canSetStart = false;
		FieldDisplay.canSetEnd = false;
	}

	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		if(displayer.plane.getBarriers().size() == 0)
			return;
		
		if(e.getWheelRotation() < 0)
			index++;
		else
			index--;
		
		if(index < 0)
			index = displayer.plane.getBarriers().size()-1;
		else if(index >= displayer.plane.getBarriers().size())
			index = 0;
		
		displayer.dragLine = (Double) displayer.plane.getBarriers().get(index); 
		displayer.updateUI();
		
	}

}
