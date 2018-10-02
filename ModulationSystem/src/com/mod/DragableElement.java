package com.mod;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cmn.utilslib.math.Maths;

public class DragableElement extends Container implements MouseListener, MouseMotionListener
{

	private static final long serialVersionUID = 1L;
	
	public int MouseOffsetX = 0;
	public int MouseOffsetY = 0;
	
	protected DrawArea drawArea;
	
	public DragableElement()
	{
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void setDrawArea(DrawArea area)
	{
		this.drawArea = area;
	}
	
	public DrawArea getDrawArea()
	{
		return this.drawArea;
	}
	
	public void mouseDragged(MouseEvent e)
	{
		int posX = e.getXOnScreen() - this.drawArea.getLocation().x - this.MouseOffsetX;
		int posY = e.getYOnScreen() - this.drawArea.getLocation().y - this.MouseOffsetY;
		
		posX = Maths.clamp(posX, this.drawArea.getLocation().x, this.drawArea.getLocation().x + this.drawArea.getSize().width - getSize().width);
		posY = Maths.clamp(posY, this.drawArea.getLocation().y, this.drawArea.getLocation().y + this.drawArea.getSize().height- getSize().height);
		setLocation(posX, posY);
		
		this.drawArea.repaint();
	}

	public void mouseMoved(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		this.MouseOffsetX = e.getXOnScreen() - this.drawArea.getLocation().x - getLocation().x;
		this.MouseOffsetY = e.getYOnScreen() - this.drawArea.getLocation().y - getLocation().y;
	}

	public void mouseReleased(MouseEvent e)
	{
	}
}
