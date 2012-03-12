package command;

import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.ActorEngine;
import engine.RoomEngine;
import environment.Room;

public class Shake implements Command
{
	public void execute(Connection connection, String target) 
	{
		Player	player 	= connection.getPlayer();
		Room 	room 	= RoomEngine.getRoomEngine().getRoom(player.getRoomID());
		
		if (target == null)
		{
			for(Player p : room.getPlayers())
			{
				if (p == player)
				{
					p.getConnection().sendFinal(ANSI.WHITE + "You shake your head." + ANSI.SANE);
				}
				else
				{
					p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " shakes his head." + ANSI.SANE);
				}
			}
		}
		else
		{
			char 	firstCharacter 	= target.charAt(0);
			String 	name 			= ("" + firstCharacter).toUpperCase() + target.substring(1);
			Player 	other 			= ActorEngine.getActorEngine().getPlayer(name);
			
			if ((other == null) || (other == player))
			{
				connection.sendFinal(ANSI.DARK_YELLOW + "You don't see that here." + ANSI.SANE);
			}
			else
			{
				for(Player p : room.getPlayers())
				{
					if (p == player)
					{
						p.getConnection().sendFinal(ANSI.WHITE + "You shake " + other.getName() + "'s hand." + ANSI.SANE);
					}
					else if (p == other)
					{
						p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " shakes your hand." + ANSI.SANE);
					}
					else
					{
						p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " shakes " + other.getName() + "'s hand." + ANSI.SANE);
					}
				}
			}
		}
	}
}