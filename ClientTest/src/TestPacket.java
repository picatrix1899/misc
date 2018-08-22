import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cmn.utilslib.net.common.IngoingPacket;
import cmn.utilslib.net.common.OutgoingPacket;

public class TestPacket implements IngoingPacket, OutgoingPacket
{

	public String text;
	
	public TestPacket() {}
	
	@Override
	public void write(OutputStream stream) throws IOException
	{
		DataOutputStream dos = new DataOutputStream(stream);
		dos.writeUTF(text);
	}

	@Override
	public void read(InputStream stream) throws IOException
	{
		DataInputStream dis = new DataInputStream(stream);
		text = dis.readUTF();
	}

}
