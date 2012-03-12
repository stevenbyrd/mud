package command;

import driver.Connection;
import driver.ConnectionList;

public class BroadcastThread extends Thread
{
	private ConnectionList 	connectionList;
	private String			message;
	
	public BroadcastThread(ConnectionList cL, String msg)
	{
		connectionList	= cL;
		message			= msg;
		
		start();
	}
	
	public void run()
	{
		connectionList.requestPasscards(1);
		
		for(Connection connection : connectionList.getConnectionList())
		{
			connection.sendFinal(message);
		}
		
		connectionList.returnPasscards(1);
	}
}
