import cmn.utilslib.net.server.ServerClientConnection;

public class ServerClient
{
	public ServerClientConnection c;
	
	public PacketHandlerImpl handler;
	
	public String name;
	
	public ServerClient(ServerClientConnection c)
	{
		this.c = c;
	}
	
	public void setHandler(PacketHandlerImpl handler)
	{
		this.c.handler = this.handler = handler;
		
	}
}
