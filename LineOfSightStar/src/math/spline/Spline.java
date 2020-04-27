package math.spline;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Spline 
{
	LinkedList<Point2D> controlPoints;
	ArrayList<Point2D> splinePoints;
	ArrayList<Point2D> vectors;
	int splineResolution = 100; 
	
	/**
	 * Creates A spline with 4 control Points;
	 * @param p1 the first control point
	 * @param p2 the second control point
	 * @param p3 the third control point
	 * @param p4 the forth control point
	 */
	public Spline(Point2D p1, Point2D p2, Point2D p3, Point2D p4)
	{
		controlPoints = new LinkedList<Point2D>();
		splinePoints = new ArrayList<Point2D>();
		vectors = new ArrayList<Point2D>();
		controlPoints.add(p1);
		controlPoints.add(p2);
		controlPoints.add(p3);
		controlPoints.add(p4);
	}
	
	/**
	 * Creates a spline using a List of control points
	 * @param addedpoints list of control points
	 */
	public Spline (List<Point2D> addedpoints)
	{
		controlPoints = new LinkedList<Point2D>();
		splinePoints = new ArrayList<Point2D>();
		vectors = new ArrayList<Point2D>();
		controlPoints.addAll(addedpoints); 
	}

	/**
	 * Creates a Spline with no control points. Points must be added to generate spline
	 */
	public Spline()
	{
		controlPoints = new LinkedList<Point2D>();
		splinePoints = new ArrayList<Point2D>();
		vectors = new ArrayList<Point2D>();
	}
	
	/**
	 * Adds a control point at the given index
	 * @param index the index
	 * @param point control point
	 */
	public void addPoint(int index, Point2D point)
	{ controlPoints.add(index, point); }
	
	/**
	 * Adds a control point at the end of the control points
	 * @param point the control Point
	 */
	public void addPoint(Point2D point)
	{ controlPoints.add(point); }
	
	/**
	 * Clears all points from control points
	 */
	public final void clearPoints()
	{ controlPoints.clear(); }
	
	/**
	 * Removes the point at the given index
	 * @param index the given index
	 */
	public final void removePoint(int index)
	{ controlPoints.remove(index); }
	
	/**
	 * Gets the point at the given index
	 * @param index the index where the point is located at
	 * @return the point at the index
	 */
	public final Point2D getPoint(int index)
	{ return controlPoints.get(index); }
	
	/**
	 * Gets a List of all of the control points
	 * @return List<Point2D> of control Points
	 */
	public List<Point2D> getControlPoints()
	{ return controlPoints; }
	
	/**
	 * Gets all of the points on the spline. This will return an empty array until generate spline is called
	 * @return
	 */
	public List<Point2D> getSplinePoints()
	{ return splinePoints; }
	
	/**
	 * Gets all of the points on the spline. This will return an empty array until generate spline is called
	 * @return
	 */
	public List<Point2D> getSplineGradient()
	{ return vectors; }
	
	/**
	 * Gets the Resolution of the spline points. The higher the resolution the more points generated
	 * @return the resolution of the spline
	 */
	public int getSplineResolution()
	{ return splineResolution; }
	
	/**
	 * Sets the Resolution of the spline points. THe higher the resolution the more points will be generated. It is defaulted at 100.
	 * Note that this is not the number of points that are generatented instead it is used to indicat how close the points are 
	 * @param splineResolution the resolution as an int value. Note a negative value or zero will lead to an exception
	 */
	public void setSplineResolution(int splineResolution)
	{ 
		if(splineResolution <= 0)
			throw new IllegalArgumentException("spline Resolution must be > 0");
		this.splineResolution = splineResolution; 
	}
	
	/**
	 * This method should return the minimum necessary controlPoints needed to run spline generation
	 * @return the minimum number of points necessary
	 */
	public abstract int minNeccesaryPoint();
	
	/**
	 * This method should be overridden if you wish to control how points are generated. By default it will use 
	 * controlPoints.size() - (minNeccesaryPoint() - 1 to allow each point to be used for generation. For other splines. This method
	 * should be changed.
	 * @return Upper bound for t value during spline generation
	 */
	public int loopUpperBound()
	{ return controlPoints.size() - (minNeccesaryPoint() - 1); }
	
	/**
	 * Checks if splines can be generated. Override this method if a certain spline has other criteria.
	 * @return true if yes, false otherwise. 
	 */
	public boolean canGenerateSpline()
	{ return controlPoints.size() >= minNeccesaryPoint(); }
	
	/**
	 * Generates the spline
	 */
	public void generateSpline()
	{	
		if(!canGenerateSpline())
			return;
		
		double increment = 1.0 / (double)splineResolution;
			
		splinePoints = new ArrayList<Point2D>((int)(loopUpperBound() / increment) + 1);
		vectors = new ArrayList<Point2D>((int)(loopUpperBound() / increment) + 1);
		
		for(double t=0; t < loopUpperBound(); t += increment)
		{
			splinePoints.add(generateSplinePoint(t));
			vectors.add(generateSplineGradient(t));
		}
	}
	
	/**
	 * Generates the spline at a given t using the parametric equation
	 * @param t the value at which it will be generated
	 * @return the Point that is generated
	 */
	public abstract Point2D generateSplinePoint(double t);
	
	public abstract Point2D generateSplineGradient(double t);
	
	/**
	 * Displays all of the spline points
	 */
	public String toString()
	{ return splinePoints.toString(); }
}
