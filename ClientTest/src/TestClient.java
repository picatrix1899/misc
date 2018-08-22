import cmn.utilslib.net.client.ClientCore;
import cmn.utilslib.net.client.ClientServerConnection;

public class TestClient extends ClientCore
{

	public Client client;
	
	public TestClient(String address, int port, Client client)
	{
		super(address, port);
		this.client = client;
	}

	public void startConnection(ClientServerConnection c)
	{
		this.client.connection = c;
		this.client.connection.handler = new PacketHandlerImpl(this.client);
		c.start();
	}
	
}
