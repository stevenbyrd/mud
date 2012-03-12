package driver;

import java.util.HashMap;
import java.util.Scanner;
import command.Command;

public class UpdateDriver extends Thread
{
	private ConnectionList 				connectionList;
	private HashMap<String, Command>	commandList;
	
	public UpdateDriver(ConnectionList cL, HashMap<String, Command> cmdList)
	{
		connectionList 	= cL;
		commandList		= cmdList;
		
		start();
	}
	
	public void run()
	{
		while(true)
		{
			connectionList.requestPasscards(1);
			
			for (Connection connection : connectionList.getConnectionList())
			{	
				MessageBuffer inputBuffer 	= connection.getInputBuffer();
				String		  input			= inputBuffer.poll();
				
				if (input != null)
				{
					processInput(connection, input);
				}
			}
			
			connectionList.returnPasscards(1);
			
			try
			{
				sleep(50);
			}
			catch(InterruptedException e)
			{
				System.err.println("Error when trying to sleep in UpdateDriver");
				e.printStackTrace();
			}
		}
	}
	
	private void processInput(Connection connection, String input)
	{		
		if ((input != null) &&
			!(input.equals("")))
		{
			Scanner parser  = new Scanner(input);
			String	cmd		= parser.next();
			
			if (cmd.toLowerCase().trim().equals("quit"))
			{
				new ConnectionBreaker(connectionList, connection);
			}
			else
			{
				if (commandList.containsKey(cmd))
				{
					if (parser.hasNext())
					{
						commandList.get(cmd).execute(connection, parser.nextLine().trim());
					}
					else
					{
						commandList.get(cmd).execute(connection, null);
					}
				}
				else
				{
					commandList.get("go").execute(connection, cmd);
				}
			}
		}
	}	
}
