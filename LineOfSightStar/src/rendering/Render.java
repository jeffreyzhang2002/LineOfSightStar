package rendering;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Render<E> 
{
	E renderObject;
	RenderSettings renderSetting;
	
	public Render(E renderObject, Color fillColor, Color strokeColor)
	{
		this.renderObject = renderObject;
		renderSetting = new RenderSettings(fillColor, strokeColor);
	}
	
	public Render(E renderObject)
	{
		this.renderObject = renderObject;
		renderSetting = new RenderSettings();
	}
	
	public Render()
	{
		renderObject = null;
		renderSetting = new RenderSettings();
	}
	
	public void set(E renderObject)
	{ this.renderObject = renderObject; }
	
	public E get()
	{ return renderObject; }
	
	public final void setFill(Color fillColor)
	{ renderSetting.setFillColor(fillColor); }
	
	public final void setStroke(Color strokeColor)
	{ renderSetting.setStrokeColor(strokeColor); }
	
	public final void setRenderSettings(RenderSettings renderSetting)
	{ this.renderSetting = renderSetting; }
	
	public final void render(Graphics g)
	{
		renderEdge(g);
		renderBody(g);
	}
	
	public final void render(E renderObject, Graphics g)
	{
		this.renderObject = renderObject;
		render(g);
	}
	
	public final void renderBody(Graphics g)
	{
		g.setColor(renderSetting.getFillColor());
		drawBody(g);
	}
	
	public final void renderEdge(Graphics g)
	{
		g.setColor(renderSetting.getStrokeColor());
		drawEdge(g);
	}
	
	public abstract void drawBody(Graphics g);

	public abstract void drawEdge(Graphics g);
}
