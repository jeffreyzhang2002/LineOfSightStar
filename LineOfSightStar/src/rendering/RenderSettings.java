package rendering;

import java.awt.Color;

public class RenderSettings 
{
	private Color fillColor, strokeColor;
	
	public RenderSettings(Color fillColor, Color strokeColor)
	{
		this.fillColor = fillColor;
		this.strokeColor = strokeColor;
	}
	
	public RenderSettings()
	{
		fillColor = Color.BLACK;
		strokeColor = Color.BLACK;
	}
	
	public void setFillColor(Color fillColor)
	{ this.fillColor = fillColor; }
	
	public Color getFillColor()
	{ return fillColor; }
	
	public void setStrokeColor(Color strokeColor)
	{ this.strokeColor = strokeColor; }
	
	public Color getStrokeColor()
	{ return strokeColor; }
	
	public String toString()
	{ return "Fill Color: " + fillColor.toString() + " Stroke Color: " + strokeColor.toString(); }
}
