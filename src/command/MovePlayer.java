package command;

import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.RoomEngine;
import environment.Exit;
import environment.Room;

public class MovePlayer implements Command 
{
	public void execute(Connection connection, String target)
	{
		Player 	player		= connection.getPlayer();
		Room	room		= RoomEngine.getRoomEngine().getRoom(player.getRoomID());
		String	direction	= target.toLowerCase();
		Exit	exit		= null;
		
		if (direction.length() == 1)
		{
			for(Exit e : room.getExitList())
			{
				if (e.getName().toLowerCase().charAt(0) == direction.toLowerCase().charAt(0))
				{
					exit = e;
					break;
				}
			}
			
			if (exit == null)
			{
				for(Exit e : room.getHiddenExits())
				{
					if (e.getName().toLowerCase().charAt(0) == direction.toLowerCase().charAt(0))
					{
						exit = e;
						break;
					}
				}
			}
		}
		else
		{
			for(Exit e : room.getExitList())
			{
				if (e.getName().toLowerCase().equals(direction))
				{
					exit = e;
					break;
				}
			}
			
			if (exit == null)
			{
				for(Exit e : room.getHiddenExits())
				{
					if (e.getName().toLowerCase().equals(direction))
					{
						exit = e;
						break;
					}
				}
			}
		}
		
		if (exit == null)
		{
			connection.sendFinal(ANSI.DARK_YELLOW + "You can't go that way!" + ANSI.SANE);
		}
		else
		{
			Room destination = RoomEngine.getRoomEngine().getRoom(exit.getDestination());
			
			RoomEngine.getRoomEngine().movePlayer(player, destination.getRoomID());
			
			for(Player other : room.getPlayers())
			{
				if (other != player)
				{
					other.getConnection().sendFinal(ANSI.WHITE + player.getName() + " goes to the " + exit.getName().toLowerCase() + "." + ANSI.SANE);
				}
			}
		}
	}
}
