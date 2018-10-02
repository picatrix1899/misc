import cmn.utilslib.net.common.IngoingPacket;
import cmn.utilslib.net.common.PacketHandler;

public class PacketHandlerImpl implements PacketHandler
{

	ServerClient client;
	
	
	public PacketHandlerImpl(ServerClient client)
	{
		this.client = client;
	}

	public void handleIngoing(IngoingPacket packet)
	{
		
		if(packet instanceof TestPacket)
			handlePacket((TestPacket)packet);
		else if(packet instanceof HandShakePacket)
			handlePacket((HandShakePacket)packet);
		
	}

	public void handlePacket(HandShakePacket packet)
	{
		this.client.name = packet.name;
		System.out.println("Hello " + packet.name);
	}
	
	public void handlePacket(TestPacket packet)
	{
		System.out.println("[Server]<" + client.name +"> says: " + packet.text);
		
		TestPacket p = new TestPacket();

		p.text = "<" + this.client.name + ">: " + packet.text;
		
		this.client.c.getServer().broadcastPacket(p);
	}
	
}
