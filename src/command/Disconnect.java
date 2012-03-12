package command;

import actor.Player;
import driver.Connection;
import driver.ConnectionList;

public class Disconnect implements Command
{
	ConnectionList connectionList;
	
	public Disconnect(ConnectionList cL)
	{
		connectionList = cL;
	}
	
	public void execute(Connection connection, String target)
	{
		Player 		player 		= connection.getPlayer();
		Broadcast 	quitMessage = new Broadcast(connectionList);
		
		quitMessage.execute(null, player.getName() + " has disconnected.");
		
		connection.disconnect();
	}
}
