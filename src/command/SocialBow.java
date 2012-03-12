package command;

import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.ActorEngine;
import engine.RoomEngine;
import environment.Room;

public class SocialBow implements Command
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
					p.getConnection().sendFinal(ANSI.WHITE + "You bow deeply." + ANSI.SANE);
				}
				else
				{
					p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " bows deeply." + ANSI.SANE);
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
						p.getConnection().sendFinal(ANSI.WHITE + "You bow before " + other.getName() + "." + ANSI.SANE);
					}
					else if (p == other)
					{
						p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " bows before you." + ANSI.SANE);
					}
					else
					{
						p.getConnection().sendFinal(ANSI.WHITE + player.getName() + " bows deeply before " + other.getName() + "." + ANSI.SANE);
					}
				}
			}
		}
	}
}
