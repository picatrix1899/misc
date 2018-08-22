package com.mod;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import cmn.utilslib.events.Event;
import cmn.utilslib.events.EventArgs;

public class Dock extends JPanel implements MouseListener
{

	private static final long serialVersionUID = 1L;
	
	public Event<DockClickEventArgs> ClickEvent = new Event<DockClickEventArgs>();
	
	public final int id;
	
	public final Element parent;
	
	public final DockMode mode;
	
	public Connection c;
	
	public Data d;
	
	public Dock(int id, DockMode mode, Element parent)
	{
		this.id = id;
		this.parent = parent;
		this.mode = mode;
		
		addMouseListener(this);
	}
	
	public int getCenterLocationX()
	{
		if(this.mode == DockMode.INPUT)
			return this.parent.getX() + this.parent.getInputStripX() + getLocation().x + getSize().width / 2;
		else
			return this.parent.getX() + this.parent.getOutputStripX() + getLocation().x + getSize().width / 2;
	}
	
	public int getCenterLocationY()
	{
		if(this.mode == DockMode.INPUT)
			return this.parent.getY() + this.parent.getInputStripY() + getLocation().y + getSize().height / 2;
		else
			return this.parent.getY() + this.parent.getOutputStripY() + getLocation().y + getSize().height / 2;
	}
	
	public static class DockClickEventArgs implements EventArgs
	{
		public Dock d;
		public int button;
		
		public DockClickEventArgs(Dock d, int button)
		{
			this.d = d;
			this.button = button;
		}
		
		public EventArgs cloneArgs()
		{
			return new DockClickEventArgs(this.d, this.button);
		}
	}

	public static enum DockMode
	{
		INPUT,
		OUTPUT;
	}



	public void mouseClicked(MouseEvent e)
	{
		this.ClickEvent.raise(new DockClickEventArgs(this, e.getButton()));
	}

	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
}
