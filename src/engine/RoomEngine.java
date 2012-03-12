package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import actor.NPC;
import actor.Player;
import environment.Exit;
import environment.Room;

public class RoomEngine extends ContentEngine
{
	private static RoomEngine		self;
	
	private HashMap<String, Room>	roomMap;
	private LinkedList<Room>		roomList;
	private ActorEngine			 	actorEngine;
	
	public static RoomEngine getRoomEngine()
	{
		if (self == null)
		{
			self = new RoomEngine();
		}
		
		return self;
	}
	
	private RoomEngine()//ActorEngine aE)
	{
		roomMap		= new HashMap<String, Room>();
		roomList	= new LinkedList<Room>();
		actorEngine	= ActorEngine.getActorEngine();
		
		buildWorld();
	}
	
	public void buildWorld()
	{
		String 		worldDirectoryPath	= gameContent + "/world";
		File 		worldDirectory 		= new File(worldDirectoryPath);
		String[]	fileList			= worldDirectory.list();
		
		try
		{
			for(String fileName : fileList)
			{
				if(fileName.contains(".txt~") == false)
				{
					Room	room		= new Room();
					File 	roomFile 	= new File(worldDirectoryPath + "/" + fileName);
					Scanner lineScanner	= new Scanner(roomFile);
					Scanner wordScanner;
					
					while(lineScanner.hasNextLine())
					{
						String line = lineScanner.nextLine();
						wordScanner = new Scanner(line);
						String word = wordScanner.next();
						
						if(word.equals("<roomID>"))
						{
							room.setRoomID(wordScanner.next());
						}
						else if(word.equals("<name>"))
						{
							room.setName(wordScanner.nextLine().trim());
						}
						else if(word.equals("<description0>"))
						{
							room.getDescription()[0] = wordScanner.nextLine().trim();
						}
						else if(word.equals("<description1>"))
						{
							room.getDescription()[1] = wordScanner.nextLine().trim();
						}
						else if(word.equals("<description2>"))
						{
							room.getDescription()[2] = wordScanner.nextLine().trim();
						}
						else if(word.equals("<description3>"))
						{
							room.getDescription()[3] = wordScanner.nextLine().trim();
						}
						else if(word.equals("<description4>"))
						{
							room.getDescription()[4] = wordScanner.nextLine().trim();
						}
						else if(word.equals("<exit>"))
						{
							Exit exit = new Exit();
							
							exit.setName(wordScanner.next());
							exit.setDestination(wordScanner.next());
							
							room.getExitList().add(exit);
						}
						else if(word.equals("<hidden_exit>"))
						{
							Exit exit = new Exit();
							
							exit.setName(wordScanner.next());
							exit.setDestination(wordScanner.next());
							
							room.getHiddenExits().add(exit);
						}
						else if (word.equals("<npc_spawn>"))
						{
							String 	npcID		= wordScanner.next();
							double	spawnRate 	= wordScanner.nextDouble();
							double	wanderRate	= wordScanner.nextDouble();
							int		minimumWait = wordScanner.nextInt();
							double	fleeRate	= wordScanner.nextDouble();
							NPC		npc			= actorEngine.getNPC(npcID);
							
							if (npc != null)
							{
								room.addSpawnableNPC(npc, spawnRate, wanderRate, minimumWait, fleeRate);
							}							
						}
					}
					
					roomMap.put(room.getRoomID(), room);
					roomList.add(room);
					
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FileNotFound");
		}
	}
	
	public synchronized Room getRoom(String ID)
	{
		if (roomMap.containsKey(ID))
		{
			return roomMap.get(ID);
		}
		else return null;
	}
	
	public HashMap<String, Room> getRoomMap()
	{
		return roomMap;
	}
	
	public LinkedList<Room> getRoomList()
	{
		return roomList;
	}
	
	public void movePlayer(Player p, String destinationID)
	{
		Room destination	= this.getRoom(destinationID);
		Room current		= this.getRoom(p.getRoomID());
		
		current.removePlayer(p);
		destination.addPlayer(p);
		
		p.getConnection().getInputBuffer().add("look");
	}
}
