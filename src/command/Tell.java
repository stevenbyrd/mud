package command;

import java.util.Scanner;

import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.ActorEngine;

public class Tell implements Command 
{
	public void execute(Connection connection, String target) 
	{
		Scanner parser     = new Scanner(target);
		String	targetName = parser.next();
		char	firstChar  = targetName.charAt(0);
		String	sendTo	   = ("" + firstChar).toUpperCase() + targetName.substring(1);
		
		if (parser.hasNext())
		{
			String message	= parser.nextLine().trim();
			Player sender 	= connection.getPlayer();
			Player receiver	= ActorEngine.getActorEngine().getPlayer(sendTo);
			
			if (receiver == null)
			{
				connection.sendFinal(ANSI.DARK_YELLOW + "Tell who?" + ANSI.SANE);
			}
			else
			{
				receiver.getConnection().sendFinal(ANSI.YELLOW + sender.getName() + " tells you, \"" + message + "\"." + ANSI.SANE);
				connection.sendFinal(ANSI.DARK_YELLOW + "Message sent." + ANSI.SANE);
			}
		}
		else
		{
			connection.sendFinal(ANSI.DARK_YELLOW + "Tell them what?" + ANSI.SANE);
		}
	}
}
