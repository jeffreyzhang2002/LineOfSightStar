package rendering;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PointRender extends Render<Point2D>
{
	private int pointDiameter = 2;
	
	public PointRender(Point2D point)
	{ super(point); }
	
	public PointRender()
	{ }
	
	public void setPointDiameter(int pointDiameter)
	{ this.pointDiameter = pointDiameter; }
	
	public int getPointDiameter()
	{ return pointDiameter; }
	
	public void drawBody(Graphics g)
	{ g.fillOval( (int)renderObject.getX(), (int)renderObject.getY(), pointDiameter, pointDiameter); }
	
	public void drawEdge(Graphics g)
	{ g.drawOval( (int)renderObject.getX(), (int)renderObject.getY(), pointDiameter, pointDiameter); }
}
