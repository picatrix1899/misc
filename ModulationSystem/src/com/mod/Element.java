package com.mod;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.mod.Dock.DockClickEventArgs;
import com.mod.Dock.DockMode;

import cmn.utilslib.essentials.Auto;

public abstract class Element extends DragableElement
{
	private static final long serialVersionUID = 1L;

	private JPanel panBody = new JPanel();
	
	private Container inputStrip = new Container();
	private Container outputStrip = new Container();	
	
	private ArrayList<Dock> inputs = Auto.ArrayList();
	private ArrayList<Dock> outputs = Auto.ArrayList();
	
	private int countInputs = 0;
	private int countOutputs = 0;
	
	private int dockSizeX = 10;
	private int dockSizeY = 10;
	
	private int bodyX = 40;
	
	public final int id;
	
	public int getInputStripX() { return this.inputStrip.getX(); }
	public int getInputStripY() { return this.inputStrip.getY(); }
	
	public int getOutputStripX() { return this.outputStrip.getX(); }
	public int getOutputStripY() { return this.outputStrip.getY(); }
	
	public Element(int id, int inputs, int outputs)
	{
		this.id = id;
		
		this.countInputs = inputs;
		this.countOutputs = outputs;
		
		setLayout(null);
		
		int sizeY1 = generateInputs();
		int sizeY2 = generateOutputs();

		int totalHeight = sizeY1 > sizeY2 ? sizeY1 : sizeY2;
		int totalCenterY = totalHeight / 2;
		int totalWidth = bodyX;
		
		if(this.countInputs > 0) totalWidth += this.dockSizeX;
		if(this.countOutputs > 0) totalWidth += this.dockSizeX;
		
		if(this.countInputs > 0)
		{
			int inputHalfY = this.inputStrip.getSize().height / 2;
			this.inputStrip.setLocation(0, totalCenterY - inputHalfY);
			add(this.inputStrip);
		}
		
		if(this.countOutputs > 0)
		{
			int outputHalfY = this.outputStrip.getSize().height / 2;
			this.outputStrip.setLocation((this.countInputs > 0 ? this.dockSizeX : 0) + bodyX, totalCenterY - outputHalfY);
			add(this.outputStrip);
		}
		
		this.panBody.setSize(bodyX, totalHeight);
		this.panBody.setLocation(this.countInputs > 0 ? this.dockSizeX : 0, 0);
		this.panBody.setVisible(true);
		add(this.panBody);			

		
		setSize(totalWidth, totalHeight);
	}
	
	private int generateInputs()
	{
		Dock dock;
		int sizeContainerY = this.dockSizeY;
		int nextPosY = this.dockSizeY;
		for(int i = 0; i < this.countInputs; i++)
		{
			sizeContainerY += 2 * this.dockSizeY;
			
			dock = new Dock(i, DockMode.INPUT, this);
			
			dock.setSize(this.dockSizeX, this.dockSizeY);
			dock.setLocation(0, nextPosY);
			
			dock.setVisible(true);
			dock.setBackground(Color.GREEN);
			
			dock.ClickEvent.addHandler((src, ref) -> onInputDockClick(src));
			
			this.inputStrip.add(dock);
			this.inputs.add(dock);
			nextPosY += 2 * this.dockSizeY;
		}
		
		this.inputStrip.setSize(this.dockSizeX, sizeContainerY);
		this.inputStrip.setVisible(true);
		
		return sizeContainerY;
	}
	
	public Dock getInputDock(int id)
	{
		return this.inputs.get(id);
	}
	
	public Dock getOutputDock(int id)
	{
		return this.outputs.get(id);
	}
	
	private int generateOutputs()
	{
		Dock dock;
		int sizeContainerY = this.dockSizeY;
		int nextPosY = this.dockSizeY;
		for(int i = 0; i < this.countOutputs; i++)
		{
			sizeContainerY += 2 * this.dockSizeY;
			
			dock = new Dock(i, DockMode.OUTPUT, this);
			
			dock.setSize(this.dockSizeX, this.dockSizeY);
			dock.setLocation(0, nextPosY);
			
			dock.setVisible(true);
			dock.setBackground(Color.RED);
			
			dock.ClickEvent.addHandler((src, ref) -> onOutputDockClick(src));
			
			this.outputStrip.add(dock);
			this.outputs.add(dock);
			nextPosY += 2 * this.dockSizeY;
		}
		
		this.outputStrip.setSize(this.dockSizeX, sizeContainerY);
		this.outputStrip.setVisible(true);
		
		return sizeContainerY;
	}
	
	public void onInputDockClick(DockClickEventArgs args)
	{
		if(args.d.c == null)
		{
			if(this.drawArea.hasActiveSelection())
			{
				if(this.drawArea.getSelection().parent != this)
					if (args.button == MouseEvent.BUTTON1) this.drawArea.connectTo(args.d);
			}
			else
			{
				if(args.button == MouseEvent.BUTTON1) this.drawArea.select(args.d);			
			}
		}
		else
		{
			if(args.button == MouseEvent.BUTTON3) this.drawArea.removeConnection(args.d.c);
		}
	}
	
	public void onOutputDockClick(DockClickEventArgs args)
	{
		if(args.d.c == null)
		{
			if(this.drawArea.hasActiveSelection())
			{
				if(this.drawArea.getSelection().parent != this)
					if (args.button == MouseEvent.BUTTON1) this.drawArea.connectTo(args.d);
			}
			else
			{
				if(args.button == MouseEvent.BUTTON1) this.drawArea.select(args.d);			
			}
		}
		else
		{
			if(args.button == MouseEvent.BUTTON3) this.drawArea.removeConnection(args.d.c);
		}
	}

	public void setSize(Dimension d) { }
	
	public abstract double getDataFromDock(Dock d);
}
