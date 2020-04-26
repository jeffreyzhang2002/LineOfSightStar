package gui;

import math.Plane;
import pathfinder.LOSStar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

class FieldDisplay extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	static Plane plane = new Plane();
	static Point2D start = null;
	static Point2D end = null;

	static ArrayList<Point2D> path = null;
	static LOSStar pathfinder = new LOSStar(plane, start, end);
	
	static Color pathColor = Color.BLUE;
	static Color startPointColor = Color.GREEN;
	static Color endPointColor = Color.ORANGE;
	static Color dragLineColor = Color.YELLOW;
	static Color barrierColor = Color.RED;
	static Color keyPointColor = Color.WHITE; 
	
	static double propagationMagnitude = 5;
	static int pointDiameters = 2;
	
	static boolean canEditLine = false;
	static boolean canSetStart = false;
	static boolean canSetEnd = false;
	static boolean canDrawPath = true;
	static boolean canDrawBarrier = true;
	static boolean canDrawPropagatedpoints = true;
	
	static Line2D.Double dragLine = null;
	
	public FieldDisplay()
	{ 
		super.setBackground(Color.BLACK); 
		
		FieldDisplayMouseActionListener actionListener = new FieldDisplayMouseActionListener();
		super.addMouseListener(actionListener);
		super.addMouseMotionListener(actionListener);
		super.addMouseWheelListener(actionListener);
	}
	
    public void paint(Graphics g)
    {
    	super.paint(g);
    	
    	if(canDrawPropagatedpoints)
    		paintPropagatedPoints(g);
    	if(canDrawBarrier)
    		paintBarriers((Graphics2D) g);
    	if(dragLine != null && canEditLine)
    		paintDragLine((Graphics2D) g);
    	if(canDrawPath)
    	{
	    	if(start != null)
	    		drawStartPoint(g);
	    	if(end != null)
	    		drawEndPoint(g);
	    	if(path != null)
	    		drawPath(g);
    	}
    }
    
    public void paintPropagatedPoints(Graphics g) 
    {
    	g.setColor(keyPointColor);
    	for(Point2D point: plane.getPropagatedPoints(propagationMagnitude))
    		g.drawOval((int)point.getX(), (int)point.getY(), pointDiameters, pointDiameters);	
    }

    public void paintBarriers(Graphics2D g)
    {
    	g.setColor(barrierColor);
    	for(Line2D line: plane.getBarriers())
    		g.draw(line);
    }
    
    public void paintDragLine(Graphics2D g)
    {
    	g.setColor(dragLineColor);
    	g.draw(dragLine);
    }
    
    public void drawPath(Graphics g)
    {
    	for(int i=1; i< path.size(); i++)
    		drawPathLine(g,path.get(i-1), path.get(i));
    }
    
    public void drawPathLine(Graphics g, Point2D start, Point2D end)
    {
    	g.setColor(pathColor);
    	g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
    }
    
    public void drawStartPoint(Graphics g)
    {
    	g.setColor(startPointColor);
		g.drawOval((int)start.getX(), (int)start.getY(), pointDiameters, pointDiameters);
    }
    
    public void drawEndPoint(Graphics g)
    {
    	g.setColor(endPointColor);
		g.drawOval((int)end.getX(), (int)end.getY(), pointDiameters, pointDiameters);
    }

    public static void changePropagationMagnitudePopUP()
    {
    	JFrame frame = new JFrame();
    	double value;
    	String string = (String)JOptionPane.showInputDialog(
    	                    frame,
    	                    "Enter a Postive Number",
    	                    "Change Propagation Magnitude",
    	                    JOptionPane.PLAIN_MESSAGE,
    	                    null,
    	                    null,
    	                    propagationMagnitude + "");
    	try {
    		value = Double.parseDouble(string);
    		if(value > 0)
    			propagationMagnitude = value;
    		else
    			changePropagationErrorMessage(frame);
    	}
    	catch(Exception e)
    	{
    		changePropagationErrorMessage(frame);
    	}
    }
    
    private static void changePropagationErrorMessage(JFrame frame)
    {
    	JOptionPane.showMessageDialog(frame,
    		    "Propagation magnitude must be a postive number",
    		    "ERROR",
    		    JOptionPane.ERROR_MESSAGE);
    }
    
    public static void unableToGeneratePathErrorMessage()
    {
    	JFrame frame = new JFrame();
    	JOptionPane.showMessageDialog(frame,
    		    "Unable to Generate Path! \n Make sure start and end points are defined",
    		    "Path Generation Warning",
    		    JOptionPane.WARNING_MESSAGE);
    }
    
    public class FieldDisplayMouseActionListener implements MouseListener, MouseMotionListener, MouseWheelListener
    {
    	private int index = 0;
    	    	
    	public void mouseDragged(MouseEvent e) 
    	{
    		if(SwingUtilities.isLeftMouseButton(e))
    		{
    			dragLine.x2 = e.getX();
    			dragLine.y2 = e.getY();
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
    			plane.getBarriers().remove(index);
    			dragLine = null;
    		}
    		else if(FieldDisplay.canSetStart)
    		{
    			FieldDisplay.start = e.getPoint();
    			FieldDisplay.canSetStart = false;
    		} 
    		else if(FieldDisplay.canSetEnd) 
    		{
    			end = e.getPoint();
    			FieldDisplay.canSetEnd = false;
    		}
    		updateUI();
    	}

    	public void mousePressed(MouseEvent e) 
    	{
    		if(SwingUtilities.isLeftMouseButton(e))
    			dragLine = new Line2D.Double(e.getPoint(),e.getPoint());
    	}

    	public void mouseReleased(MouseEvent e) 
    	{
    		if(FieldDisplay.canEditLine && SwingUtilities.isLeftMouseButton(e))
    		{
    			plane.addBarrier(dragLine);
    			dragLine = null;
    			updateUI();
    		}
    	}

    	public void mouseEntered(MouseEvent e)
    	{
    		updateUI();
    	}

    	public void mouseExited(MouseEvent e) 
    	{
    		FieldDisplay.canEditLine = false;
    		FieldDisplay.canSetStart = false;
    		FieldDisplay.canSetEnd = false;
    	}

    	public void mouseWheelMoved(MouseWheelEvent e) 
    	{
    		if(plane.getBarriers().size() == 0)
    			return;
    		
    		if(e.getWheelRotation() < 0)
    			index++;
    		else
    			index--;
    		
    		if(index < 0)
    			index = plane.getBarriers().size()-1;
    		else if(index >= plane.getBarriers().size())
    			index = 0;
    		
    		dragLine = (Line2D.Double) plane.getBarriers().get(index); 
    		updateUI();
    	}
    }

}
