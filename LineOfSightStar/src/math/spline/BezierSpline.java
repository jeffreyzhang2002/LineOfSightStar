package math.spline;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BezierSpline extends Spline
{	
	public static final int minimunNumberOfPoints = 4;
	
	/**
	 * Creates a Bezier Spline using 4 control points
	 * @param p1 first control point
	 * @param p2 second control point
	 * @param p3 third control point
	 * @param p4 fourth control point
	 */
	public BezierSpline(Point2D p1, Point2D p2, Point2D p3, Point2D p4)
	{ super(p1,p2,p3,p4); }
	
	/**
	 * Creates a Bezier spline using a List of control points
	 * @param addedpoints the list of control points
	 */
	public BezierSpline (List<Point2D> addedpoints)
	{ super(addedpoints); }
	
	/**
	 * Creates a Bezaie spline with no control points. Point must be added for generation to work
	 */
	public BezierSpline()
	{}
	
	/**
	 * Returns the minimyn ecessary control points needed to control the Bezier spline
	 */
	public int minNeccesaryPoint()
	{ return minimunNumberOfPoints; }

	/**
	 * This method overrides the super class method to allow for optimizations. Other wise it does the samething
	 */
	public void generateSpline()
	{	
		if(!canGenerateSpline())
			return;
		
		double increment = 1.0 / (double)splineResolution;
		
		splinePoints = new ArrayList<Point2D>(splineResolution);
		vectors = new ArrayList<Point2D>(splineResolution);
		
		long[] pascals = pascals(super.controlPoints.size() - 1);
		
		for(double t=0; t < 1; t += increment)
		{
			splinePoints.add(generateSplinePoint(t, pascals));
			vectors.add(generateSplineGradient(t, pascals));
		}
	}
	
	/**
	 * Generates a bezier spline at the current t with the known pascals triangle layer setup
	 * @param t the currne point of generation
	 * @param pascals a long array with pascals triangle number
	 * @return
	 */
	public Point2D generateSplinePoint(double t, long[] pascals)
	{
		double tPrime = 1-t;
		int degree = super.controlPoints.size();
		
		double x = 0, y = 0;
		
		for(int i = 0; i < degree; i++)
		{
			 double value = pascals[i] * Math.pow(t,i) * Math.pow(tPrime, degree-i-1);
			 x += value * super.controlPoints.get(i).getX();
			 y += value * super.controlPoints.get(i).getY();	
		}
		return new Point2D.Double(x,y);
	}
	
	public Point2D generateSplineGradient(double t, long[] pascals)
	{
		double tPrime = 1-t;
		int degree = super.controlPoints.size();
		
		double x = 0, y = 0;
		
		for(int i = 0; i < degree; i++)
		{
			 double value = pascals[i] * (Math.pow(tPrime, degree - i - 2) * Math.pow(t, i-1) * (i * tPrime - (degree-i-1) * t));
			 x += value * super.controlPoints.get(i).getX();
			 y += value * super.controlPoints.get(i).getY();	
		}
		return new Point2D.Double(x,y);
	}
	
	public Point2D generateSplineGradient(double t)
	{
		return generateSplinePoint(t, pascals(super.controlPoints.size() - 1));
	}
	
	public Point2D generateSplinePoint(double t)
	{ return generateSplinePoint(t, pascals(super.controlPoints.size() - 1)); }
	
	private long[] pascals(int line)
	{
		long[] pascalsValue = new long[line + 1];
		
		for(int i=0; i <= line; i++)
			pascalsValue[i] = combination(line,i);
		return pascalsValue;
	}
	
	private long combination(int n, int k)
	{ 
		int res = 1; 
      
        if (k > n - k) 
        	k = n - k; 
              
        for (int i = 0; i < k; ++i) 
        { 
            res *= (n - i); 
            res /= (i + 1); 
        } 
        return res; 
	}

}
