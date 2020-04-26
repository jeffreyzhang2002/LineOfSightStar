package main;

import java.awt.geom.Point2D;
import math.spline.*;
import processing.core.PApplet;

public class SplineTesting extends PApplet
{
	public static void main(String[] args)
	{ PApplet.main("main.SplineTesting"); }
	
	public void settings()
	{ size(500,500); }
	
	BezierSpline spline = new BezierSpline();
		
	public void setup()
	{ }
	
	public void draw()
	{
		background(0);
	
		fill(255,0,0);
		stroke(255,0,0);
		for(Point2D point : spline.getSplinePoints())
			drawPoint(point);
		
		fill(0,255,0);
		stroke(0,255,0);
		for(Point2D point : spline.getControlPoints())
			drawPoint(point);
		
	
	}
	
	public void mousePressed()
	{
		spline.addPoint(new Point2D.Double(mouseX, mouseY));
	}
	
	public void keyPressed()
	{
		spline.generateSpline();
	}
	
	public void drawPoint(Point2D point)
	{
		ellipse( (float)point.getX(), (float)point.getY(), 1, 1);
	}
}