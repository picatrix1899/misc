
import java.util.ArrayList;

import cmn.utilslib.essentials.Auto;
import cmn.utilslib.net.common.OutgoingPacket;
import cmn.utilslib.net.server.ServerClientConnection;
import cmn.utilslib.net.server.ServerCore;

public class TestServer extends ServerCore
{

	private ArrayList<ServerClient> clients = Auto.ArrayList();
	
	public TestServer(String address, int port, int maxConnections)
	{
		super(address, port, maxConnections);
	}

	@Override
	public synchronized void joinConnection(ServerClientConnection c)
	{
		ServerClient client = new ServerClient(c);
		client.setHandler(new PacketHandlerImpl(client));
		clients.add(client);
		c.start();
	}
	
	@Override
	public void broadcastPacket(OutgoingPacket p)
	{
		for(ServerClient client : clients)
		{
			client.c.sendPacket(p);
		}
	}
}
