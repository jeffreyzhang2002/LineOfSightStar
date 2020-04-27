package rendering;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PointRender extends Render
{
	private Point2D point;
	private int pointDiameter = 2;
	
	public PointRender(Point2D point)
	{ this.point = point; }
	
	public void setPointDiameter(int pointDiameter)
	{ this.pointDiameter = pointDiameter; }
	
	public int getPointDiameter()
	{ return pointDiameter; }
	
	public void drawBody(Graphics g)
	{ g.fillOval( (int) point.getX(), (int) point.getY(), pointDiameter, pointDiameter); }
	
	public void drawEdge(Graphics g)
	{ g.drawOval( (int) point.getX(), (int) point.getY(), pointDiameter, pointDiameter); }
}
