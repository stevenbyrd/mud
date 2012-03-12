package command;

import actor.Player;
import driver.ANSI;
import driver.Connection;
import driver.ConnectionList;

public class Broadcast implements Command
{
	private ConnectionList connectionList;
	
	public Broadcast(ConnectionList cL)
	{
		connectionList = cL;
	}
	
	public void execute(Connection connection, String target)
	{	
		if (connection != null)
		{
			Player player = connection.getPlayer();
			new BroadcastThread(connectionList, ANSI.YELLOW + "### " + player.getName() + " broadcasts: \"" + target + "\"." + ANSI.SANE);
		}
		else
		{
			new BroadcastThread(connectionList, ANSI.DARK_YELLOW + "### " + target + ANSI.SANE);
		}
	}
}
