package command;

import java.util.HashMap;

import actor.NPC;
import actor.Player;
import driver.ANSI;
import driver.Connection;
import engine.ActorEngine;
import engine.RoomEngine;
import environment.Exit;
import environment.Room;

public class Look implements Command
{	
	public void execute(Connection connection, String target)
	{
		HashMap<String, Room> 	roomMap	= RoomEngine.getRoomEngine().getRoomMap();
		Player					player	= connection.getPlayer();
		Room					room	= roomMap.get(player.getRoomID());
		
		if (target == null)
		{
			connection.send("");
			connection.send(ANSI.RED + room.getName() + ANSI.SANE);
			connection.send("");
			
			for(String line : room.getDescription())
			{
				if (line != null)
				{
					connection.send(ANSI.WHITE + line + ANSI.SANE);
				}
			}
			
			Exit	firstExit	= null;
			String 	exitList 	= ANSI.BLUE + "Obvious exits: ";
			
			if (room.getExitList().isEmpty() == false)
			{
				firstExit  = room.getExitList().getFirst();
				exitList   = exitList + firstExit.getName();
				
				if (room.getExitList().size() > 1)
				{
					for(Exit exit : room.getExitList())
					{
						if (exit != firstExit)
						{
							exitList = exitList + ", " + exit.getName();
						}
					}
				}				
			}
			else
			{
				exitList = exitList + "none";
			}
			
			connection.send(exitList + ANSI.SANE);
						
			if (room.getPlayers().size() > 1)
			{
				Player firstPlayer = null;
				String playerList  = ANSI.GREEN + "Players: " + ANSI.SANE;
				
				if (room.getPlayers().getFirst() != player)
				{
					firstPlayer = room.getPlayers().getFirst();
				}
				else
				{
					if (room.getPlayers().size() > 1)
					{
						firstPlayer = room.getPlayers().get(1);
					}
				}
				
				if (firstPlayer != null)
				{
					playerList  = playerList + ANSI.GREEN + firstPlayer.getName() + ANSI.SANE;
					
					if (room.getPlayers().size() > 1)
					{
						for(Player p : room.getPlayers())
						{
							if ((p != player) && (p != firstPlayer))
							{
								playerList = playerList + ANSI.GREEN + ", " + ANSI.SANE + p.getName();
							}
						}
					}
				}
				
				connection.send(playerList);
			}
			
			if (room.getNPCs().isEmpty() == false)
			{				
				String npcList = ANSI.DARK_CYAN + "NPCs:";
				
				for(NPC npc : room.getSpawnableNPCs())
				{
					if (npc.getNumberInRoom() > 0)
					{
						if (npc.getNumberInRoom() == 1)
						{
							if ((npc.getName().charAt(0) == 'a') ||
								(npc.getName().charAt(0) == 'e') ||
								(npc.getName().charAt(0) == 'i') ||
								(npc.getName().charAt(0) == 'o') ||
								(npc.getName().charAt(0) == 'u'))
							{
								npcList = npcList + " an " + npc.getName() + ",";
							}
							else
							{
								npcList = npcList + " a " + npc.getName() + ",";
							}
						}
						else
						{
							npcList = npcList + " " + parseInteger(npc.getNumberInRoom()) + " " + npc.getPluralName() + ",";
						}
					}
				}
				
				connection.send(npcList.substring(0, (npcList.length() - 1)) + ANSI.SANE);
			}
						
			connection.sendFinal("");
		}
		else
		{	
			//first check to see if there is a player by name "target"
			char firstCharacter = target.charAt(0);
			String name = ("" + firstCharacter).toUpperCase() + target.substring(1);

			Player p = ActorEngine.getActorEngine().getPlayer(name);
		
			if (p != null)
			{			
				if (room.getPlayers().contains(p))
				{
					connection.send(ANSI.MAGENTA);
					connection.send("Name: " + p.getName());
					connection.send("Race: " + ActorEngine.parceRace(p.getRace()));
					
					for(String line : p.getDescription())
					{
						if (line != null)
						{
							connection.send("Description: " + line);
						}
					}
					
					connection.sendFinal(ANSI.SANE);
				}
			}
			else
			{
				//look for an NPC by that name
				NPC npc = null;
				
				name = name.toLowerCase();
				
				for(NPC n : room.getNPCs())
				{
					if ((n.getName().toLowerCase().startsWith(name)) || 
						(name.equals(n.getName().toLowerCase())))
					{
						npc = n;
						break;
					}
				}
				
				if (npc != null)
				{
					connection.send(ANSI.MAGENTA);
				
					for(String line : npc.getDescription())
					{
						if (line != null)
						{
							connection.send(line);
						}
					}
					connection.sendFinal(ANSI.SANE);
				}
				else connection.sendFinal(ANSI.DARK_YELLOW + "You don't see that here." + ANSI.SANE);
			}
		}
	}
	
	private String parseInteger(int number)
	{
		switch(number)
		{
			case 1: return "one";
			case 2: return "two";
			case 3: return "three";
			case 4: return "four";
			case 5: return "five";
			case 6: return "six";
			case 7: return "seven";
			case 8: return "eight";
			case 9: return "nine";
			default: return "" + number;
		}
	}
}
