package pathfinder;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import math.Plane;

public class LOSStar
{
    private Plane plane;
    private Point2D start, end;
    
    private PriorityQueue<LOSStarGroup> openSet;
    private HashSet<Point2D> closedSet;
    private HashMap<Point2D, Point2D> cameFrom;

    /**
     * Creates a LOSStar pathfinding algorithum
     * @param plane the plane it will run on
     * @param start the starting point
     * @param end the ending point
     */
    public LOSStar(Plane plane, Point2D start, Point2D end)
    {
        this.plane = plane;
        this.start = start;
        this.end = end;
    }

    /**
     * generates the optimal Path from start to end
     * @param mag how far points are offset from the end points of each line segment
     * @return the optimal path
     */
    public final ArrayList<Point2D> generatePath(double mag)
    {
        openSet = new PriorityQueue<>();
        closedSet = new HashSet<>();
        cameFrom = new HashMap<>();

        if(plane.LineOfSight(start, end))
        {
        	ArrayList<Point2D> path = new ArrayList<>();
        	path.add(start);
        	path.add(end);
        	return path;
        }
        
        ArrayList<Point2D> keyPoints = plane.getPropagatedPoints(mag);
        keyPoints.add(start);
        keyPoints.add(end);

        LOSStarGroup startGroup = new LOSStarGroup(start);
        startGroup.setGScore(0);
        startGroup.setFScore(heuristic(start,end));
        openSet.add(startGroup);

        while(!openSet.isEmpty())
        {
            LOSStarGroup currentGroup = openSet.poll();
            closedSet.add(currentGroup.getPoint());

            if(currentGroup.getPoint().equals(end))
                return reconstructPath();

            for(Point2D currentKeyPoint : keyPoints)
            {
                if(!closedSet.contains(currentKeyPoint) && !openSetContains(currentKeyPoint) && plane.LineOfSight(currentGroup.getPoint(), currentKeyPoint))
                {
                    openSet.add(new LOSStarGroup(currentKeyPoint,
                            heuristic(currentGroup.getPoint(),currentKeyPoint) + currentGroup.getGScore(),
                            heuristic(currentKeyPoint, end)));
                    cameFrom.put(currentKeyPoint,currentGroup.getPoint());
                }
            }
        }
        return null;
    }

    private ArrayList<Point2D> reconstructPath()
    {
        Point2D current = end;
        ArrayList<Point2D> path = new ArrayList<>();
        path.add(end);
        while(cameFrom.get(current) != null)
        {
            current = cameFrom.get(current);
            path.add(current);
        }
        return path;
    }

    private boolean openSetContains(Point2D point)
    {
        for(LOSStarGroup group : openSet)
            if(group.equals(point))
                return true;
        return false;
    }

    /**
     * Cost of arriving at a certain point. This is set to be the distance squared formula
     * @param start first point
     * @param end second point
     * @return distance squared between them
     */
    public double heuristic(Point2D start, Point2D end)
    { return start.distanceSq(end); }
    
    /** 
     * Gets the starting point
     * @return start point
     */
    public Point2D getStart()
    { return start; }
    
    /**
     * Gets the ending point
     * @return the end point
     */
    public Point2D getEnd()
    { return end; }
    
    /**
     * Getsthe plane that pathfinder is using
     * @return the current Plane
     */
    public Plane getPlane()
    { return plane; }
    
    /**
     * Sets the starting point
     * @param start the starting point
     */
    public void setStart(Point2D start)
    { this.start = start; }
    
    /**
     * Sets the ending point
     * @param end the ending point
     */
    public void setEnd(Point2D end)
    { this.end = end; }
    
    /**
     * Sets the plane the LOSStar will use
     * @param plane the LOSStar will use
     */
    public void setPlane(Plane plane)
    { this.plane = plane; }
    
    public String toString()
    { return start + " -> " + end; }
}

