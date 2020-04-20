package testing;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import math.Plane;
import pathfinder.LOSStar;

/**
 * Example class for LOSStar pathfinder 
 * @author Jeffrey
 *
 */
public class Main 
{
	public static void main(String[] args)
	{
		Plane plane = new Plane();
		Point2D startPoint = new Point2D.Double(50,10);
	    Point2D endPoint   = new Point2D.Double(50,90);
	    int offsetmagnitude = 5;
	    
	    Line2D wall1 = new Line2D.Double(new Point2D.Double(10,10), new Point2D.Double(90,90));
	    Line2D wall2 = new Line2D.Double(new Point2D.Double(10,90), new Point2D.Double(90,10));
	    
	    plane.addBarrier(wall1);
	    plane.addBarrier(wall2);
	    
	    LOSStar pathfinder = new LOSStar(plane, startPoint, endPoint);
	    
	    System.out.println(pathfinder.generatePath(offsetmagnitude));
	    
	}
}
