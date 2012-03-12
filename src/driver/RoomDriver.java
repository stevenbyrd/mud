package driver;

import actor.NPC;
import engine.RoomEngine;
import environment.Room;

public class RoomDriver extends Thread
{
	public RoomDriver()
	{
		start();
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				for(Room room : RoomEngine.getRoomEngine().getRoomList())
				{
					if(room.hasPlayers())
					{
						for(NPC spawn : room.getSpawnableNPCs())
						{
							if (spawn.getSpawnRate() > Math.random())
							{
								room.addNPC(spawn.getInstance());
								spawn.incrementNumberInRoom();
							}
						}
					}
					
					if(room.hasNPC())
					{
						for(NPC npc : room.getNPCs())
						{						
							if ((npc.getWanderRate() > Math.random()) &&
								(((int)System.currentTimeMillis() - npc.getSpawnTime()) >= npc.getMinimumWait()))
							{
								room.removeNPC(npc);
															
								for(NPC spawn : room.getSpawnableNPCs())
								{
									if (spawn.getActorID().equals(npc.getActorID()))
									{
										spawn.decrementNumberInRoom();
										break;
									}
								}
							}
						}
					}
				}
				
				sleep(5000);
			}
		}
		catch(InterruptedException e)
		{
			System.err.println("RoomDriver interrupted!");
		}
	}
}
