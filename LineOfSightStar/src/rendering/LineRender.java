package rendering;

import java.awt.Graphics;
import java.awt.geom.Line2D;

public class LineRender extends Render<Line2D>
{	
	public LineRender(Line2D line)
	{ super(line); }
	
	public LineRender()
	{}
	
	public void drawBody(Graphics g) {
		return;
	}

	public void drawEdge(Graphics g) {
		g.drawLine((int)renderObject.getX1(), (int)renderObject.getY1(), (int)renderObject.getX2(), (int)renderObject.getY2());
	}
	
	public static void drawLine(Graphics g, Line2D line)
	{ g.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2()); }
}
