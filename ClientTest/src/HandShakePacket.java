
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.OutputStream;


import cmn.utilslib.net.common.OutgoingPacket;

public class HandShakePacket implements OutgoingPacket
{

	public String name;

	@Override
	public void write(OutputStream stream) throws IOException
	{
		DataOutputStream dos = new DataOutputStream(stream);
		dos.writeUTF(name);
	}

}
