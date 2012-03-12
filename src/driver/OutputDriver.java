package driver;

public class OutputDriver extends Thread
{
	private ConnectionList connectionList;
	
	public OutputDriver(ConnectionList cL)
	{
		connectionList = cL;
		
		start();
	}
	
	public void run()
	{
		while(true)
		{
			connectionList.requestPasscards(1);

			for (Connection connection : connectionList.getConnectionList())
			{	
				//if the output buffer for this connection is not empty, push its
				//contents to the connection's output stream
				String output = connection.getOutputBuffer().poll();
				
				if ((output != null) && !output.equals(""))
				{
					connection.getOutputStream().println(output);
				}
				
				connection.getOutputStream().flush();
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
