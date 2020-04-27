package rendering;

import java.awt.Graphics;
import java.awt.geom.Line2D;

public class LineRender extends Render
{
	private Line2D line;
	
	public LineRender(Line2D line)
	{ this.line = line; }

	public void drawBody(Graphics g) {
		return;
	}

	@Override
	public void drawEdge(Graphics g) {
		g.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());
	}
}
