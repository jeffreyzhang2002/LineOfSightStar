package main;

import processing.core.PApplet;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import math.Plane;
import pathfinder.LOSStar;

/**
 * GUI for LOSStar
 * Controls: mousePressed = startLine, mouseReleased = endLine, a = add new generated line, r = regenerate path;
 * @author Jeffrey
 */
public class GUI extends PApplet
{
    private Plane plane;
    private LOSStar pathfinder;
    
    
    private ArrayList<Point2D> path;
    private Point2D startPoint;
    private Point2D endPoint;
    
    private Point2D addLineP1;
    private Point2D addLineP2;
    private boolean canAddLine = false;
    private ArrayList<Point2D> keyPoints;
    
    private final int pointSize = 2;
    private final int mag = 5;

    public static void main(String[] args)
    { PApplet.main("main.GUI"); }

    public void settings()
    {
    	plane = new Plane();
    	startPoint = new Point2D.Double(250,100);
    	endPoint = new Point2D.Double(250,400);
    	
        plane.addBarrier(new Line2D.Double(new Point2D.Double(100, 100), new Point2D.Double(400, 400)));
        plane.addBarrier(new Line2D.Double(new Point2D.Double(100, 400), new Point2D.Double(400, 100)));
        
        keyPoints = plane.getPropagatedPoints(mag);
        
        pathfinder = new LOSStar(plane, startPoint, endPoint);
        path = pathfinder.generatePath(mag);
        size(500,500);
    }

    public void setup()
    {

    }

    public void draw()
    {
        background(0);
        
        stroke(0,255,0);
        for(Line2D l : plane.getBarriers())
            line(l);

        stroke(255);
        fill(255);
        for(Point2D p : keyPoints)
            point(p,pointSize);
        
        stroke(0,0,255);
        fill(0,0,255);
        point(startPoint, pointSize);
        point(endPoint, pointSize);
        

        stroke(255,0,0);
        for(int i=1; i< path.size(); i++)
            line(path.get(i-1), path.get(i));
    }

    public void mousePressed()
    { 
    	addLineP1 = new Point2D.Double(mouseX,mouseY);
    }

    public void mouseReleased()
    {
    	canAddLine = true;
        addLineP2 = new Point2D.Double(mouseX,mouseY);
    }

    public void keyPressed() {
        
    	if(key == 'r')
    	{
            path = pathfinder.generatePath(mag);
            if(path == null)
            	path = new ArrayList<>();
    	}
        else if (key =='a' && canAddLine == true) {
            plane.addBarrier(new Line2D.Double(addLineP1, addLineP2));
            keyPoints = plane.getPropagatedPoints(mag);
            canAddLine = false;
        }
    }


    public void line(Line2D l)
    {	line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2()); }

    public void line(Point2D p1, Point2D p2)
    {	line((float)p1.getX(), (float)p1.getY(), (float)p2.getX(), (float)p2.getY()); }

    public void point(Point2D l, int size)
    {	ellipse((float)l.getX(), (float)l.getY(), size, size); }
}
