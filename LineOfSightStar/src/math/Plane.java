package math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 2D repersentation of a plane on which LOSStar pathfinder is run on. This class allows for the addition of barriers
 * @author Jeffrey
 *
 */
public class Plane 
{
	private ArrayList<Line2D> barriers;
	
	/**
	 * Constructor for Plane class
	 */
    public Plane()
    { barriers = new ArrayList<>(); }
    
    /**
     * Constructor for Plane using another Plane class
     * @param other
     */
    public Plane(Plane other)
    { barriers = other.getBarriers();}

    /**
     * Adds a Line2D from Java.awt into the current Plane
     * @param line the current Line
     */
    public final void addBarrier(Line2D line)
    { barriers.add(line); }

    /**
     * Adds a Collection of Line2D into the current Plane
     * @param lines
     */
    public final void addBarrier(Collection<Line2D.Double> lines)
    { barriers.addAll(lines); }
 
    /**
     * Checks if the startPoint has direct line of sight to the endPoint. This means that there are not obstacles blocking it way
     * @param startPoint the startingPoint
     * @param endPoint the endingPoint
     * @return true if their is line of sight false otherwise
     */
    public final boolean LineOfSight(Point2D startPoint, Point2D endPoint)
    {
        Line2D.Double intersectionLine = new Line2D.Double(startPoint,endPoint);

        for(Line2D currentLine : barriers)
            if(currentLine.intersectsLine(intersectionLine))
                return false;
        return true;
    }

    /**
     * Gets all of the barriers on the current Plane
     * @return Arraylist containing all of the barruers
     */
    public final ArrayList<Line2D> getBarriers()
    { return barriers; }
    
    /**
     * Clears the plane of all Items
     */
    public final void clearPlane()
    { barriers.clear(); }

    /**
     * Gets all of the endpoints on the current Plane. End points are at the end of each line Segment
     * @return the End points of all lines in the plane
     */
    public final ArrayList<Point2D> getEndPoints()
    {
        ArrayList<Point2D> keyPoints = new ArrayList<>();
        
        for(Line2D currentLine: barriers)
        {
        	keyPoints.add(currentLine.getP1());
        	keyPoints.add(currentLine.getP2());
        }
        return keyPoints;
    }

    /**
     * Gets all of the porpagated points on each Line. Propagated pointsare collinear with each on the Barriers but the are not on the line. 
     * They are all offset from the line by the magnitude
     * @param mag How far the progated points is from the endpoint
     * @return All of the propagated point
     */
    public final ArrayList<Point2D> getPropagatedPoints(double mag) 
    {
        ArrayList<Point2D> propagatedPoint = new ArrayList<>();

        for(Line2D currentLine: barriers)
        	propagatedPoint.addAll(getPropogatedEndPoints(currentLine, mag));
        
        return propagatedPoint;
    }
    
    /**
     * Gets the Propagated end point of the line by a mag
     * @param line the line where the points are propogated
     * @param mag the magnitue of propagation
     * @return
     */
    public ArrayList<Point2D> getPropogatedEndPoints(Line2D line, double mag)
	{
		ArrayList<Point2D> propagatedPoints = new ArrayList<>();

        double angle = Math.atan2(line.getY2() - line.getY1(), line.getX2() - line.getX1());

        Point2D.Double P1 = new Point2D.Double();
        Point2D.Double P2 = new Point2D.Double();
          
        if (line.getX1() < line.getX2()) {
            P1.x = line.getX1() - Math.abs(Math.cos(angle)) * mag;
            P2.x = line.getX2() + Math.abs(Math.cos(angle)) * mag;
        } else {
            P1.x = line.getX1() + Math.abs(Math.cos(angle)) * mag;
            P2.x = line.getX2() - Math.abs(Math.cos(angle)) * mag;
        }

        if (line.getY1() < line.getY2()) {
            P1.y = line.getY1() - Math.abs(Math.sin(angle)) * mag;
            P2.y = line.getY2() + Math.abs(Math.sin(angle)) * mag;
        } else {
            P1.y = line.getY1() + Math.abs(Math.sin(angle)) * mag;
            P2.y = line.getY2() - Math.abs(Math.sin(angle)) * mag;
        }

        propagatedPoints.add(P1);
        propagatedPoints.add(P2);

        return propagatedPoints;
	}

    public String toString()
    { return barriers.toString(); }
}
