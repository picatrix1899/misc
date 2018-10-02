package org.geometryTester;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import cmn.utilslib.math.geometry.AABB2f;
import cmn.utilslib.math.geometry.Circle2f;
import cmn.utilslib.math.geometry.Point2f;
import cmn.utilslib.math.geometry.Rectangle2f;

public class DrawArea extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void drawCircle(Circle2f obj, Graphics g, Color c)
	{
		Color _c = g.getColor();
		g.setColor(c);
		
		AABB2f aabb = obj.getAABB();
		
		g.drawOval((int)aabb.getMin().x, (int)aabb.getMin().y, (int)obj.radius * 2, (int)obj.radius * 2);
		
		g.setColor(_c);
		
	}
	
	private void drawRectangle(Rectangle2f obj, Graphics g, Color c)
	{
		Color _c = g.getColor();
		g.setColor(c);
		
		g.drawRect((int)obj.getMin().x, (int)obj.getMin().y, (int)obj.halfExtend.x * 2, (int)obj.halfExtend.y * 2);
		
		g.setColor(_c);
	}
	
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Circle2f c = new Circle2f(new Point2f(100, 100), 20);
		
		drawCircle(c, g, Color.BLUE);
		drawRectangle(c.getAABB(), g, Color.RED);
	}
}
