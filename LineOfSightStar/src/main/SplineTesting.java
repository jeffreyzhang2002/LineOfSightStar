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
	
	Spline spline = new BezierSpline();
		
	public void setup()
	{ }
	
	int t = 0;
	
	public void draw()
	{
		background(0);
		
		fill(0,255,0);
		stroke(0,255,0);
		for(Point2D point : spline.getControlPoints())
			drawPoint(point);
		
		fill(255,0,0);
		stroke(255,0,0);
		for(Point2D point : spline.getSplinePoints())
			drawPoint(point);
		
		rectMode(CENTER);
		if(spline.canGenerateSpline() && !spline.getSplineGradient().isEmpty())
		{
			pushMatrix();
			Point2D p = spline.getSplineGradient().get(t);
			Point2D g = spline.getSplinePoints().get(t);
			translate((float)g.getX(), (float)g.getY());
			double rotation = Math.atan2(p.getY(),p.getX());
			rotate((float)rotation);
			rect(0,0,10,10);
			popMatrix();
		}
		
		stroke(255);
		for(int i=1; i<spline.getControlPoints().size(); i++)
		{
			line(spline.getControlPoints().get(i-1),spline.getControlPoints().get(i));
		}
	
	}
	
	public void line(Point2D p1, Point2D p2)
	{
		line((float)p1.getX(), (float)p1.getY(), (float)p2.getX(), (float)p2.getY());
	}
	
	public void mousePressed()
	{
		spline.addPoint(new Point2D.Double(mouseX, mouseY));
	}
	
	public void keyPressed()
	{
		spline.generateSpline();
		if(key == 'd')
			t ++;
		else if(key == 'a')
			t --;
		
		if(t < 0)
			t = spline.getSplinePoints().size()-1;
		else if(t >= spline.getSplinePoints().size())
			t = 0;
	}
	
	public void drawPoint(Point2D point)
	{
		ellipse( (float)point.getX(), (float)point.getY(), 1, 1);
	}
}
