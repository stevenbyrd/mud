package command;

import actor.Actor;
import actor.Player;
import affect.Affect;
import driver.ANSI;
import driver.Connection;
import engine.ActorEngine;
import engine.AffectEngine;

public class Cast implements Command
{
	public void execute(Connection connection, String argument) 
	{
		try
		{
			String[]	splitArgs	= argument.split(" ");
			String		spellName	= splitArgs[0];
			String		targetName	= (splitArgs.length >= 2) ? splitArgs[1] : null;
			String[]	components	= AffectEngine.getComponentsForAffect(spellName);
			Actor		caster 		= connection.getPlayer();
			Actor		target		= (targetName == null) ? null : ActorEngine.getActorEngine().getActorByNameFromRoom(targetName, caster.getRoomID());
			Affect		affect		= new Affect(components);
			String[]	output		= affect.execute(caster, target);
			
			if(caster.getClass() == Player.class)
			{
				Player player = (Player)caster;
				
				player.getConnection().sendFinal(ANSI.GREEN + output[0] + ANSI.SANE);
			}
		}
		catch(Exception ex)
		{
			System.err.println("Error in Cast command: " + ex.getMessage());
		}
	}
}
