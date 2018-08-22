package demo;

import com.mod.Dock;
import com.mod.Dock.DockMode;
import com.mod.InputElement;

public class ConstantInputElement extends InputElement
{

	private static final long serialVersionUID = 1L;

	private double value = 25;
	
	public ConstantInputElement(int id)
	{
		super(id, 1);
	}

	@Override
	public double getDataFromDock(Dock d)
	{
		if(d.id == 0 && d.mode == DockMode.OUTPUT)
		{
			return this.value;
		}
		
		return 0.0d;
	}
	
}
