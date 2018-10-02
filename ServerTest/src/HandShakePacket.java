import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import cmn.utilslib.net.common.IngoingPacket;

public class HandShakePacket implements IngoingPacket
{

	public String name;
	
	@Override
	public void read(InputStream stream) throws IOException
	{
		DataInputStream dis = new DataInputStream(stream);
		
		name = dis.readUTF();
	}

}
