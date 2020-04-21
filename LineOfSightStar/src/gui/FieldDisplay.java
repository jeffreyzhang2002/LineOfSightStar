package gui;

import math.Plane;
import pathfinder.LOSStar;

import javax.swing.*;
import java.awt.*;
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
	
	Color pathColor       = Color.BLUE;
	Color barrierColor    = new Color(  0, 255,   0);
	Color dragLineColor   = Color.yellow;
	Color keyPointColor   = new Color(255, 255, 255); 
	
	static double propagationMagnitude = 5;
	static int pointDiameters = 2;
	
	static boolean canEditLine = false;
	static boolean canSetStart = false;
	static boolean canSetEnd = false;
	
	Line2D.Double dragLine = null;
	
	public FieldDisplay()
	{ 
		super.setBackground(Color.BLACK); 
		FieldDisplayMouseActionListener actionListener = new FieldDisplayMouseActionListener(this);
		
		super.addMouseListener(actionListener);
		super.addMouseMotionListener(actionListener);
		super.addMouseWheelListener(actionListener);
	
	}
	
    public void paint(Graphics g)
    {
    	super.paint(g);
    	paintPropagatedPoints(g);
    	paintBarriers((Graphics2D) g);
    	if(dragLine != null && canEditLine)
    		paintDragLine((Graphics2D) g);
    	if(start != null)
    	{
    		g.setColor(Color.orange);
    		g.drawOval((int)start.getX(), (int)start.getY(), pointDiameters, pointDiameters);
    	}
    	if(end != null)
    	{
    		g.setColor(Color.RED);
    		g.drawOval((int)end.getX(), (int)end.getY(), pointDiameters, pointDiameters);
    	}
    	if(path != null)
    	{
    		for(int i=1; i< path.size(); i++)
                drawLine(g,path.get(i-1), path.get(i));
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
    
    public void drawLine(Graphics g, Point2D start, Point2D end)
    {
    	g.setColor(pathColor);
    	g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
    }
}
