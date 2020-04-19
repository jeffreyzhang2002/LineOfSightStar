package pathfinder;

import java.awt.geom.Point2D;

/**
 * Helper class for LOSStar. This should not be used outside of LOSStar
 * @author Jeffrey
 *
 */
class LOSStarGroup implements Comparable<LOSStarGroup>
{
    private Point2D point;
    private double gScore = Double.POSITIVE_INFINITY;
    private double fScore = Double.POSITIVE_INFINITY;

    /**
     * Creates an LOSStarGroup with the given point from LOSStar
     * @param point a point on the Plane. 
     * In LOSStar points will by either the starting point, the endingpoint or propagated points of each barrier
     */
    public LOSStarGroup(Point2D point)
    { this.point = point; }

    /**
     * Creates an LOSStarGroup with the given point from LOSStar
     * @param point point a point on the Plane. 
     * In LOSStar points will by either the starting point, the endingpoint or propagated points of each barrier
     * @param gScore the gScore is the cumulative distance necessary to arrive at the certain point. Is should the previous gScore + distance betwen that point and the current point
     * @param fScore the future distance still needed to travel. Distance formula from the current point to the end point
     */
    public LOSStarGroup(Point2D point, double gScore, double fScore)
    {
        this.point = point;
        this.gScore = gScore;
        this.fScore = fScore;
    }

    /**
     * Gets the gScore of the current Point This is a value repersenting the cost of arriving at the certain point
     * @return the gScore
     */
    public double getGScore()
    { return gScore; }

    /**
     * sets the gScore of the current Point. 
     * @param gScore the gScore for the current point
     */
    public void setGScore(double gScore)
    { this.gScore = gScore; }

    /**
     * Gets the fScore of the current Point. This is an estimate of how much distance still needs to be traveled
     * @return the fScore
     */
    public double getFScore()
    { return fScore; }

    /**
     * sets the fScore of the current Point. 
     * @param fScore the fScore for the current point
     */
    public void setFScore(double fScore)
    { this.fScore = fScore; }

    /**
     * gets the current Point stored inside the LOSStar
     * @return
     */
    public Point2D getPoint()
    { return point; }

    /**
     * Compares this LOSStar group to another LOSStar group. They compare the sum of gScore + fScore 
     */
    public int compareTo(LOSStarGroup other)
    { return (int) (this.getGScore() + this.fScore - (other.getGScore() + other.fScore)); }

    /**
     * Checks if o is equal to this LOSStarGroup. They are equivelent if the points are equal
     */
    public boolean equals(Object o) 
    {
        if(o instanceof  Point2D.Double)
            return ((Point2D.Double) o).equals(this.getPoint());
        else if(o instanceof LOSStarGroup)
            return((LOSStarGroup)o).getPoint().equals(this.getPoint());
        return false;
    }

    public int hashCode()
    { return 1; }

    public String toString()
    { return point.toString() + "GScore: " + gScore + "FScore: " + fScore; }
}
