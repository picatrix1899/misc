import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main
{
	
	public static TestClient client;
	
	public static void main(String[] args)
	{
		
		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
		String name = ""; 
		try
		{
			name = sin.readLine();
	
			
			Client c = new Client();
			c.name = name;
			
			client = new TestClient("localhost", 25566, c);
			
			client.addPacket(0, HandShakePacket.class);
			client.addPacket(1,  TestPacket.class);
			
			client.start();
			
			HandShakePacket handshake = new HandShakePacket();
			handshake.name = c.name;
			
			client.client.connection.sendPacket(handshake);
			
			while(true)
			{
				if(System.in.available() > 0)
				{
	
					String st = sin.readLine();
					
					TestPacket p = new TestPacket();
					p.text = st;
					client.client.connection.sendPacket(p);
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
