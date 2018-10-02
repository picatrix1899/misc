package com.mod;

import com.mod.Dock.DockMode;

public class Connection
{
	public Dock input;
	public Dock output;
	
	public Connection(Dock a, Dock b)
	{
		if(a.mode == DockMode.INPUT)
		{
			this.input = a;
			this.output = b;
		}
		else
		{
			this.input = b;
			this.output = a;
		}
		
		this.input.c = this;
		this.output.c = this;
	}
}
