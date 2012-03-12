package environment;

import java.util.LinkedList;

import driver.ANSI;
import actor.*;

public class Room extends RoomFlags
{
	private String				roomID;
	private String				name;
	private String[]			description;
	private LinkedList<Exit>	exitList;
	private LinkedList<Exit>	hiddenExits;
	private LinkedList<Player>	players;
	private LinkedList<NPC>		NPCs;
	private LinkedList<NPC>		spawnableNPCs;
	private int					updateRate;
	
	public Room()
	{
		description 	= new String[5];
		exitList		= new LinkedList<Exit>();
		hiddenExits		= new LinkedList<Exit>();
		players			= new LinkedList<Player>();
		NPCs			= new LinkedList<NPC>();
		spawnableNPCs	= new LinkedList<NPC>();
		updateRate		= 1000;
		
		new RoomUpdater(this, updateRate);
	}
	
	public synchronized void addPlayer(Player player)
	{
		if (players.contains(player) == false)
		{
			players.add(player);
			
			player.setRoomID(roomID);
			
			for(Player other : players)
			{
				if (other != player)
				{
					other.getConnection().send(player.getName() + " has arrived.");
				}
			}
		}
	}
	
	public synchronized void removePlayer(Player player)
	{
		if (players.contains(player))
		{
			players.removeFirstOccurrence(player);
		}
	}

	public String getRoomID() 
	{
		return roomID;
	}

	public void setRoomID(String roomID) 
	{
		this.roomID = roomID;
	}

	public String[] getDescription() 
	{
		return description;
	}

	public LinkedList<Exit> getExitList() 
	{
		return exitList;
	}
	
	public LinkedList<Exit> getHiddenExits()
	{
		return hiddenExits;
	}

	public LinkedList<Player> getPlayers() 
	{
		return players;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<NPC> getNPCs()
	{
		return (LinkedList<NPC>)NPCs.clone();
	}
	
	public void addNPC(NPC npc)
	{
		NPCs.add(npc);
		
		for(Player p : players)
		{
			p.getConnection().send(ANSI.WHITE + "A " + npc.getName() + " has just arrived." + ANSI.SANE);
		}
	}
	
	public void removeNPC(NPC npc)
	{
		NPCs.remove(npc);
		
		for(Player p : players)
		{
			p.getConnection().send(ANSI.WHITE + "The " + npc.getName() + " has wandered off." + ANSI.SANE);
		}
	}
	
	public LinkedList<NPC> getSpawnableNPCs()
	{
		return spawnableNPCs;
	}
	
	public void addSpawnableNPC(NPC npc, double spawnRate, double wanderRate, int minimumWait, double fleeRate)
	{
		npc.setSpawnRate(spawnRate);
		npc.setWanderRate(wanderRate);
		npc.setMinimumWait(minimumWait);
		npc.setFleeRate(fleeRate);
		
		spawnableNPCs.add(npc);
	}
	
	public void setUpdateRate(int rate)
	{
		updateRate = rate;
	}
	
	public boolean hasPlayers()
	{
		return !players.isEmpty();
	}
	
	public boolean hasNPC()
	{
		return !NPCs.isEmpty();
	}
}
