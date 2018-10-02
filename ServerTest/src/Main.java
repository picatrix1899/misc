

public class Main
{
	

	public static TestServer core;

	public static void main(String[] args)
	{

		core = new TestServer("localhost", 25566, 3);

		core.addPacket(0, HandShakePacket.class);
		core.addPacket(1, TestPacket.class);
		core.start();

	}


}
