package command;

import actor.ActorDefinitions;
import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.ActorEngine;

public class Stats implements Command 
{	
	public void execute(Connection connection, String target) 
	{
		Player 	player = connection.getPlayer();
		int[]	stats  = player.getStats();
		
		connection.send(ANSI.DARK_MAGENTA);		
		connection.send(".-\"-._,-'_`-._,-'_`-._,-'_`-._,-'_`-,_,-'_`-,_,-'_`-,_,-'-`.");
		connection.send("(  ,-'_,-\".>-'_,-\".>-'_,-" + ANSI.MAGENTA + "STATISTICS" + ANSI.DARK_MAGENTA + "\".>-_,-~.=-'_,-~.=-'_,~'};");
		connection.send("`-' `-._,-' `-._,-' `-._,-' `-._,-' `-._,-' `-._,-' `-._,-'");	
		connection.send(ANSI.SANE);
		
		String name 		= ANSI.MAGENTA + "  Name: " + ANSI.WHITE + player.getName() + ANSI.SANE;
		String strength		= ANSI.MAGENTA + "\t\tStrength: " + ANSI.WHITE + stats[ActorDefinitions.stat_strength] + ANSI.SANE;
		String constitution	= ANSI.MAGENTA + "\tConstitution: " + ANSI.WHITE + stats[ActorDefinitions.stat_constitution] + ANSI.SANE;
		
		String race			= ANSI.MAGENTA + "  Race: " + ANSI.WHITE + ActorEngine.parceRace(player.getRace()) + ANSI.SANE;
		String energy		= ANSI.MAGENTA + "\t\tEnergy: " + ANSI.WHITE + stats[ActorDefinitions.stat_energy] + ANSI.SANE;
		String focus		= ANSI.MAGENTA + "\tFocus: " + ANSI.WHITE + stats[ActorDefinitions.stat_focus] + ANSI.SANE;
		
		String gender		= (player.getGender() == ActorDefinitions.gender_female) ? 
									(ANSI.MAGENTA + "  Gender: " + ANSI.WHITE + "Female" + ANSI.SANE) : 
									(ANSI.MAGENTA + "  Gender: " + ANSI.WHITE + "Male" + ANSI.SANE + "\t");
		
		String agility		= ANSI.MAGENTA + "\tAgility: " + ANSI.WHITE + stats[ActorDefinitions.stat_agility] + ANSI.SANE;
		String awareness	= ANSI.MAGENTA + "\tAwareness: " + ANSI.WHITE + stats[ActorDefinitions.stat_awareness] + ANSI.SANE;
		
		connection.send(name + strength + constitution);
		connection.send(race + energy + focus);
		connection.send(gender + agility + awareness);
		
		connection.sendFinal("");
	}
}
