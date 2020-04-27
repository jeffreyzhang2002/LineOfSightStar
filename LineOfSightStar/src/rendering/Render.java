package rendering;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Render 
{
	Color strokeColor;
	Color fillColor;
	
	public Render(Color strokeColor, Color fillColor)
	{
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
	}
	
	public Render()
	{
		strokeColor = Color.BLACK;
		fillColor = Color.BLACK;
	}
	
	public final void setFill(Color fillColor)
	{ this.fillColor = fillColor; }
	
	public final void setStroke(Color strokeColor)
	{ this.strokeColor = strokeColor; }
	
	public final void render(Graphics g)
	{
		renderEdge(g);
		renderBody(g);
	}
	
	public final void renderBody(Graphics g)
	{
		g.setColor(fillColor);
		drawBody(g);
	}
	
	public final void renderEdge(Graphics g)
	{
		g.setColor(strokeColor);
		drawEdge(g);
	}
	
	public abstract void drawBody(Graphics g);

	public abstract void drawEdge(Graphics g);
}
