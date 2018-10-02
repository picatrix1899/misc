package demo;

import com.mod.Dock;
import com.mod.Dock.DockMode;
import com.mod.OutputElement;

public class DefaultOutputElement extends OutputElement
{

	private static final long serialVersionUID = 1L;

	public DefaultOutputElement(int id)
	{
		super(id, 1);
	}
	
	@Override
	public double getDataFromDock(Dock d)
	{
		if(d.id == 0 && d.mode == DockMode.INPUT)
		{
			
			System.out.println(d.c);
			
			if(d.c != null)
			{
				return d.c.output.parent.getDataFromDock(d.c.output);
			}
		}
		
		return 0.0d;
	}
	
}
