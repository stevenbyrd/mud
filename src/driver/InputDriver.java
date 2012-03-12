package driver;

import java.io.IOException;

public class InputDriver extends Thread
{
	private ConnectionList connectionList;
	
	public InputDriver(ConnectionList cL)
	{
		connectionList = cL;
		
		start();
	}
	
	public void run()
	{
		while(true)
		{
			connectionList.requestPasscards(1);
			
			try
			{
				for (Connection connection : connectionList.getConnectionList())
				{	
					//if the input stream for this connection is guaranteed not to block,
					//read from it and add non-null input to the connection's input buffer
					if (connection.getInputStream().ready())
					{
						String input = connection.getInputStream().readLine();
						
						if (input != null)
						{
							connection.getInputBuffer().add(input);
						}
					}
				}
			}
			catch(IOException e)
			{
				
			}
			
			connectionList.returnPasscards(1);
			
			try
			{
				sleep(5);
			}
			catch(InterruptedException e)
			{
				System.err.println("Error when trying to sleep in UpdateDriver");
				e.printStackTrace();
			}
		}
	}
}
