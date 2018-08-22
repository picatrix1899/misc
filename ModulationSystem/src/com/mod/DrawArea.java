package com.mod;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import cmn.utilslib.essentials.Auto;
import cmn.utilslib.math.Maths;

public class DrawArea extends JPanel implements MouseMotionListener, MouseListenerAdapter
{
	private static final long serialVersionUID = 1L;
	
	public Dock selected;
	
	private int MouseX = 0;
	private int MouseY = 0;
	
	
	public ArrayList<Connection> connections = Auto.ArrayList();
	
	
	
	public void addConnection(Connection c)
	{
		this.connections.add(c);
		repaint();
	}
	
	public void removeConnection(Connection c)
	{
		this.connections.remove(c);
		c.input.c = null;
		c.output.c = null;
		repaint();
	}
	
	public void connectTo(Dock d)
	{
		if(this.selected != null)
		{
			addConnection(new Connection(this.selected, d));
			unselect();
			repaint();
		}
	}
	
	public void addDragable(DragableElement e)
	{
		e.setDrawArea(this);

		add(e);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void mouseDragged(MouseEvent e)
	{
	}

	public void mouseMoved(MouseEvent e)
	{
		if(this.selected != null)
		{
			int posX = e.getXOnScreen() - getLocation().x;
			int posY = e.getYOnScreen() - getLocation().y;
			
			MouseX = Maths.clamp(posX, getLocation().x, getLocation().x + getSize().width);
			MouseY = Maths.clamp(posY, getLocation().y, getLocation().y + getSize().height);
			repaint();
		}
		
	}
	
	public boolean hasActiveSelection()
	{
		return this.selected != null;
	}
	
	public void select(Dock d)
	{
		this.selected = d;
	}
	
	public void unselect()
	{
		this.selected = null;
	}
	
	public Dock getSelection()
	{
		return this.selected;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(Connection c : this.connections)
		{
			g.drawLine(c.input.getCenterLocationX(), c.input.getCenterLocationY(), c.output.getCenterLocationX(), c.output.getCenterLocationY());
		}
		
		if(hasActiveSelection())
		{
			g.drawLine(this.selected.getCenterLocationX(), this.selected.getCenterLocationY(), MouseX, MouseY -32);			
		}

	}

	public void mouseClicked(MouseEvent e)
	{
		if(hasActiveSelection())
		{
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				unselect();
				repaint();
			}			
		}

	}
}
