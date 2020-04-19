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

    public LOSStar(Plane plane, Point2D.Double start, Point2D.Double end)
    {
        this.plane = plane;
        this.start = start;
        this.end = end;
    }

    public ArrayList<Point2D> generatePath(double mag)
    {
        openSet = new PriorityQueue<>();
        closedSet = new HashSet<>();
        cameFrom = new HashMap<>();

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

    public double heuristic(Point2D start, Point2D end)
    { return start.distance(end); }
}

