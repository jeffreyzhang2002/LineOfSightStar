package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import math.Plane;
import rendering.LineRender;
import rendering.PointRender;
import rendering.RenderSettings;

public class FieldDisplay extends JPanel
{
	private static final long serialVersionUID = 7941993290002886144L;
	
	private GUI gui;
	private LineRender lineRender = new LineRender();
	private PointRender pointRender = new PointRender();
	
	private RenderSettings barrierSettings = new RenderSettings(Color.RED, Color.RED);
	private RenderSettings propagationPointsSettings = new RenderSettings(Color.WHITE, Color.WHITE);
	private RenderSettings selectedItemSettings = new RenderSettings(Color.YELLOW, Color.YELLOW);
	
	Plane plane = new Plane();
	
	Line2D.Double selectedBarrier = null;
	int selectedBarrierindex = 0;
	
	Point2D.Double selectedPathPoint = null;
	int selectedPointindex;
	
	boolean canEditBarrierMode = false;
	boolean canEditPathMode = false;
	
	public FieldDisplay(GUI gui)
	{ 
		super.setBackground(Color.BLACK);
		this.gui = gui; 
		
		FieldDisplayMouseActionListener mouseListener = new FieldDisplayMouseActionListener();
		super.addMouseListener(mouseListener);
		super.addMouseMotionListener(mouseListener);
		super.addMouseWheelListener(mouseListener);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		paintBarriers(g);
		paintPropagationPoints(g);
		paintSelectedItems(g);
	}
	
	public void paintBarriers(Graphics g)
	{
		lineRender.setRenderSettings(barrierSettings);
		for(Line2D line : plane.getBarriers())
			lineRender.render(line, g);
	}
	
	public void paintPropagationPoints(Graphics g)
	{
		pointRender.setRenderSettings(propagationPointsSettings);
		for(Point2D point : plane.getPropagatedPoints())
			pointRender.render(point, g);
			
	}
	
	public void paintSelectedItems(Graphics g)
	{
		if(canEditBarrierMode && selectedBarrier != null)
		{
			lineRender.setRenderSettings(selectedItemSettings);
			lineRender.render(selectedBarrier, g);
		}
		
		if(canEditPathMode && selectedPathPoint != null)
		{
			pointRender.setRenderSettings(selectedItemSettings);
			pointRender.render(selectedPathPoint, g);
		}
	}
	
	public void removeBarrier(int index)
	{
		plane.removeBarrier(index);
		gui.main.barriersContent.updateBarrierButtons();
		updateUI();
	}
	
	public void addBarrier(Line2D barrier)
	{
		plane.addBarrier(barrier);
		gui.main.barriersContent.addBarrier(barrier);
		updateUI();
	}
	
	public void setEditBarrierMode(boolean canEdit)
	{
		canEditBarrierMode = canEdit;
		if(canEdit)
			canEditPathMode = false;
		resetEditing();
	}
	
	public void setEditPathMode(boolean canEdit)
	{
		canEditPathMode = canEdit;
		if(canEdit)
			canEditBarrierMode = false;
		resetEditing();
	}
	
	private void resetEditing()
	{
		selectedPointindex = 0;
		selectedBarrierindex = 0;
		selectedBarrier = null;
		selectedPathPoint = null;
	}
	
	private class FieldDisplayMouseActionListener implements MouseListener, MouseMotionListener, MouseWheelListener
	{	
		private boolean inDraggingMode = false;
		
		public void mouseWheelMoved(MouseWheelEvent e) 
		{
			if(canEditBarrierMode)
			{
				if(plane.getBarriers().size() == 0)
	    			return;
				
				gui.main.barriersContent.barrierButtons.get(selectedBarrierindex).setBackground(Color.WHITE);
				
	    		if(e.getWheelRotation() < 0)
	    			selectedBarrierindex++;
	    		else
	    			selectedBarrierindex--;
	    		
	    		if(selectedBarrierindex < 0)
	    			selectedBarrierindex = plane.getBarriers().size()-1;
	    		else if(selectedBarrierindex >= plane.getBarriers().size())
	    			selectedBarrierindex = 0;
	    		
	    		gui.main.barriersContent.barrierButtons.get(selectedBarrierindex).setBackground(Color.YELLOW);
	    		
	    		selectedBarrier = (Line2D.Double) plane.getBarriers().get(selectedBarrierindex); 
			}
			else if(canEditPathMode)
			{
				
			}
    		updateUI();
		}

		public void mouseDragged(MouseEvent e) 
		{
			if(inDraggingMode && selectedBarrier != null && SwingUtilities.isLeftMouseButton(e))
    		{
    			selectedBarrier.x2 = e.getX();
    			selectedBarrier.y2 = e.getY();
    			updateUI();
    		}
		}

		public void mouseMoved(MouseEvent e) 
		{
			
		}

		public void mouseClicked(MouseEvent e)
		{
			if(SwingUtilities.isRightMouseButton(e) && !plane.getBarriers().isEmpty())
    		{
    			removeBarrier(selectedBarrierindex);
    			selectedBarrier = null;
    			selectedBarrierindex = 0;
    			updateUI();
    		}
		}

		public void mousePressed(MouseEvent e) 
		{
			if(canEditBarrierMode && SwingUtilities.isLeftMouseButton(e))
			{
    			selectedBarrier = new Line2D.Double(e.getPoint(), e.getPoint());
    			inDraggingMode = true;
			}
		}

		public void mouseReleased(MouseEvent e) 
		{
			if(selectedBarrier != null && SwingUtilities.isLeftMouseButton(e))
			{
				addBarrier(selectedBarrier);
				selectedBarrier = null;
				inDraggingMode = false;
			}
		}

		public void mouseEntered(MouseEvent e) 
		{
			
		}

		public void mouseExited(MouseEvent e) 
		{
			selectedBarrier = null;
			inDraggingMode = false;
			updateUI();
		}
	}
}
