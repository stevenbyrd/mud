package environment;

import actor.NPC;

public class RoomUpdater extends Thread
{
	private Room room;
	private int	 sleepTime;
	
	public RoomUpdater(Room r, int sT)
	{
		room		= r;
		sleepTime	= sT;
		
		start();
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				if (room.getPlayers().size() != 0)
				{
					for(NPC spawn : room.getSpawnableNPCs())
					{
						if (spawn.getSpawnRate() > Math.random())
						{
							room.addNPC(spawn.getInstance());
							spawn.incrementNumberInRoom();
						}
					}
					
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
					
					sleep(sleepTime);
				}
				else
				{
					sleep(5000);
				}				
			}
		}
		catch (InterruptedException e) 
		{
			System.out.println("InterruptedException while room updater sleeping");
		}
	}
}
