package demo;

import com.mod.Dock;
import com.mod.Element;
import com.mod.Dock.DockMode;

public class TestElement extends Element
{

	private static final long serialVersionUID = 1L;

	public TestElement(int id)
	{
		super(id, 1, 1);
	}

	@Override
	public double getDataFromDock(Dock d)
	{
		if(d.id == 0)
		{
			if(d.mode == DockMode.INPUT)
			{
				return d.c.output.parent.getDataFromDock(d.c.output);
			}
			else
			{
				return getDataFromDock(getInputDock(0)) + 25;
			}
		}
		
		return 0.0;
	}

}
