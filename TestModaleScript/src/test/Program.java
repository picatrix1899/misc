package test;

public class Program
{
	
	public Program()
	{
		ModaleScript script = new ModaleScript();
		
		script.commandStack.add(new ModaleCommand() { public void execute() { System.out.println("Hello World"); } });

		script.execute();
	}
	
	
	
	
}
