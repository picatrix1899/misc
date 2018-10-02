package test;

import java.util.ArrayList;

public class ModaleScript
{
	public ArrayList<ModaleCommand> commandStack = new ArrayList<ModaleCommand>();
	
	public void execute()
	{
		for(ModaleCommand c : commandStack)
		{
			c.execute();
		}
	}
}
