package math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Plane 
{
	private ArrayList<Line2D> barriers;
	 
    public Plane()
    { barriers = new ArrayList<>(); }

    public void addBarrier(Line2D line)
    { barriers.add(line); }

    public void addBarrier(ArrayList<Line2D.Double> lines)
    { barriers.addAll(lines); }

    public boolean LineOfSight(Point2D startPoint, Point2D endPoint)
    {
        Line2D.Double intersectionLine = new Line2D.Double(startPoint,endPoint);

        for(Line2D currentLine : barriers)
            if(currentLine.intersectsLine(intersectionLine))
                return false;
        
        return true;
    }

    public ArrayList<Line2D> getBarriers()
    { return barriers; }

    public ArrayList<Point2D> getKeyPoints(double mag)
    {
        ArrayList<Point2D> keyPoints = new ArrayList<>();
        
        for(Line2D currentLine: barriers)
        {
        	keyPoints.add(currentLine.getP1());
        	keyPoints.add(currentLine.getP2());
        }
        
        return keyPoints;
    }

    public ArrayList<Point2D> getPropagatedPoints(double mag) 
    {
        ArrayList<Point2D> propagatedPoint = new ArrayList<>();

        for(Line2D currentLine: barriers)
        	propagatedPoint.addAll(getPropogatedEndPoints(currentLine, mag));
        
        return propagatedPoint;
    }
    
    private ArrayList<Point2D> getPropogatedEndPoints(Line2D line, double mag)
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
}
