package pathfinder;

import java.awt.geom.Point2D;

class LOSStarGroup implements Comparable<LOSStarGroup>
{
    private Point2D point;
    private double gScore = Double.POSITIVE_INFINITY;
    private double fScore = Double.POSITIVE_INFINITY;

    public LOSStarGroup(Point2D point)
    { this.point = point; }

    public LOSStarGroup(Point2D point, double gScore, double fScore)
    {
        this.point = point;
        this.gScore = gScore;
        this.fScore = fScore;
    }

    public double getGScore()
    { return gScore; }

    public void setGScore(double gScore)
    { this.gScore = gScore; }

    public double getFScore()
    { return fScore; }

    public void setFScore(double fScore)
    { this.fScore = fScore; }

    public Point2D getPoint()
    { return point; }

    public int compareTo(LOSStarGroup other)
    { return (int) (this.getGScore() + this.fScore - (other.getGScore() + other.fScore)); }

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
    { return point.toString(); }
}
