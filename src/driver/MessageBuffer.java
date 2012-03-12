package driver;

import java.util.LinkedList;

public class MessageBuffer 
{
	private LinkedList<String> buffer;
	
	public MessageBuffer()
	{
		buffer = new LinkedList<String>();
	}
	
	public synchronized void add(String str)
	{
		buffer.add(str);
	}
	
	public synchronized String poll()
	{
		if (buffer.size() >= 1)
		{
			return buffer.poll();
		}
		else return null;
	}
}
