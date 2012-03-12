package command;

import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.RoomEngine;
import environment.Room;

public class Say implements Command 
{
	public void execute(Connection connection, String target) 
	{
		if ((target != null) && (!target.equals("")))
		{
			Player	player 	= connection.getPlayer();
			Room 	room 	= RoomEngine.getRoomEngine().getRoom(player.getRoomID());
			
			
			for(Player p : room.getPlayers())
			{
				if (p == player)
				{
					p.getConnection().sendFinal(ANSI.WHITE + "You say, \"" + target + "\"." + ANSI.SANE);
				}
				else
				{
					p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " says, \"" + target + "\"." + ANSI.SANE);
				}
			}
		}
		else
		{
			connection.sendFinal(ANSI.DARK_YELLOW + "Say what?" + ANSI.SANE);
		}
	}
}
