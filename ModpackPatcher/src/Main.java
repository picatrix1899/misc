import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main
{

	public static final String name = "patch.zip";
	public static final String patchfile = "patchfile.txt";
	
	public static final int  BUFFER_SIZE = 4096;
	
	public static File root;
	public static File patch;
	
	public static final String CMD_MOVE = "move";
	public static final String CMD_RENAME = "rename";
	public static final String CMD_DELETE = "delete";
	public static final String CMD_COPY = "copy";
	
	static
	{
		try
		{
			root = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		patch = new File(root, name);
	}
	
	
	public static void copy(ZipFile zip, String src, String dest) throws Exception
	{
		if(new File(root, src).isFile())
		{
			copyFile(zip, src, dest);
		}
		else
		{
			copyDir(zip, src, dest);
		}
		
	}
	
	public static void copyDir(ZipFile zip, String src, String dest) throws Exception
	{
		boolean internal = src.startsWith("::");
		if(internal) src = src.replaceFirst("::", "");
		
		File destFolder = new File(root, dest);
		
		InputStream in;
		File dst;		
		String internalPath;
		
		if(internal)
		{
			System.out.print("copy internal directory \"" + src + "\" to \"" + dest + "\"....");
			
			Enumeration<? extends ZipEntry> entries = zip.entries();
			
			ZipEntry entry;
			
			
			while(entries.hasMoreElements())
			{
				entry = entries.nextElement();
				
				if(!entry.isDirectory())
				{
					if(entry.getName().startsWith(src))
					{
						internalPath = entry.getName().replace(src, "");
						if(internalPath.startsWith(File.pathSeparator)) internalPath = internalPath.replaceFirst(File.pathSeparator, "");
						dst = new File(destFolder, internalPath);
						
						if(!dst.getParentFile().exists())
						{
							dst.getParentFile().mkdirs();
						}
						
						if(dst.exists())
						{
							dst.delete();
						}
						
						if(!dst.exists())
						{
							dst.createNewFile();
						}
						
						in = zip.getInputStream(entry);
						
						writeDataToFile(in, dst);
					}
				}
			}
			
			System.out.println("finished!");
		}
		else
		{
			System.out.print("copy external directory \"" + src + "\" to \"" + dest + "\"....");
			
			File dir = new File(root, src);
			
			if(!dir.exists())
			{
				System.out.println("failed!");
				return;
			}
			
			copyFullFolderStructure(dir, destFolder);
			
			System.out.println("finished!");
		}

	}
	
	public static void copyFullFolderStructure(File src, File dest) throws Exception
	{
		for(File f : src.listFiles())
		{
			if(f.isDirectory())
			{
				File f2 = new File(dest, f.getName());
				
				f2.mkdirs();
				
				copyFullFolderStructure(f, f2);
			}
			else
			{
				Files.copy(f.toPath(), new File(dest, f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
	
	public static void copyFile(ZipFile zip, String src, String dest) throws Exception
	{
		
		boolean internal = src.startsWith("::");
		if(internal) src = src.replaceFirst("::", "");
		
		File f = new File(root, dest);
		
		if(f.exists())
		{
			f.delete();
		}
		
		if(!f.getParentFile().exists())
		{
			f.getParentFile().mkdirs();
		}
		
		f.createNewFile();
		

		
		InputStream in;
		
		if(internal)
		{
			System.out.print("copy internal file \"" + src + "\" to \"" + dest + "\"....");
			ZipEntry e = zip.getEntry(src);
			if(e == null)
			{
				System.out.println("failed!");
				return;
			}
			in = zip.getInputStream(e);
			
			writeDataToFile(in, f);	
		}
		else
		{
			System.out.print("copy external file \"" + src + "\" to \"" + dest + "\"....");
			File f2 = new File(root, src);
			if(!f2.exists())
			{
				System.out.println("failed!");
				return;
			}
			
			Files.copy(f2.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}

		
		


		System.out.println("finished!");
	}
	
	public static void writeDataToFile(InputStream in, File dest) throws Exception
	{
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
		
		byte[] buffer = new byte[BUFFER_SIZE];
		
	    int count = -1;
	    while ((count = in.read(buffer)) != -1)
	      out.write(buffer, 0, count);
	    
	    out.flush();
	    out.close();
		
	    in.close();
	}
	
	
	public static void delete(String path) throws Exception
	{
		if(new File(root, path).isFile())
		{
			deleteFile(path);
		}
		else
		{
			deleteDir(path);
		}
	}
	
	public static void deleteFile(String path)
	{
		System.out.print("delete file " + "\"" + path + "\"....");
		
		File f = new File(root, path);
		
		f.delete();
		
		System.out.println("finished!");
	}
	
	
	
	public static void deleteDir(String path)
	{
		System.out.print("delete directory " + "\"" + path + "\"....");
		
		File f = new File(root, path);
		
		if(f.exists())
		{
			File[] content = f.listFiles();
			
			for(File c : content)
				c.delete();
			
			f.delete();
		}
		
		System.out.println("finished!");
	}
	
	public static void rename(String path, String name) throws Exception
	{
		System.out.print("renaming " + "\"" + path + "\" to \""+ name + "\"" + "\"....");
		File src = new File(root, path);
		File dest = new File(src.getParentFile(), name);
		
		src.renameTo(dest);
	}
	
	
	
	public static void main(String[] args)
	{
		try
		{
			if(!patch.exists())
			{
				System.out.println("patch.zip doesn't exists!");
				return;
			}
			
			ZipFile zip = new ZipFile(patch.getPath());
			
			if(zip.getEntry(patchfile) == null)
			{
				System.out.println("the patchfile \"" + patchfile + "\" doesn't exists.");
				zip.close();
				return;
			}
			
			InputStreamReader isr = new InputStreamReader(zip.getInputStream(zip.getEntry(patchfile)));
			BufferedReader br = new BufferedReader(isr);
			
			String line = "";
			
			while((line = br.readLine()) != null)
			{				
				if(line.startsWith(CMD_DELETE + ": "))
				{
					String[] parts = line.split(": ");
					delete(parts[1]);
				}
				else if(line.startsWith(CMD_COPY + ": "))
				{
					String[] parts = line.split(": ");
					String[] arguments = parts[1].split(" ; ");
					copy(zip, arguments[0] , arguments[1]);
				}
				else if(line.startsWith(CMD_MOVE + ": "))
				{
					String[] parts = line.split(": ");
					String[] arguments = parts[1].split(" ; ");
					copy(zip, arguments[0] , arguments[1]);
				}
				else if(line.startsWith(CMD_RENAME + ": "))
				{
					String[] parts = line.split(": ");
					String[] arguments = parts[1].split(" ; ");
					rename(arguments[0] , arguments[1]);
				}
			}
			
			zip.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
