import cmn.utilslib.net.common.IngoingPacket;
import cmn.utilslib.net.common.PacketHandler;

public class PacketHandlerImpl implements PacketHandler
{

	Client c;
	
	public PacketHandlerImpl(Client c)
	{
		this.c = c;
	}
	
	public void handleIngoing(IngoingPacket packet)
	{
		if(packet instanceof TestPacket)
		 handlePacket((TestPacket)packet);
	}
	
	public void handlePacket(TestPacket packet)
	{
		System.out.println(packet.text);
	}

}
