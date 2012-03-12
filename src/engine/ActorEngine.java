package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import environment.Room;
import actor.Actor;
import actor.ActorDefinitions;
import actor.NPC;
import actor.Player;

public class ActorEngine extends ContentEngine
{
	private static ActorEngine		self;
	
	private HashMap<String, Player>	playerMap;
	private HashMap<String, NPC>	npcMap;
	
	
	public static ActorEngine getActorEngine()
	{
		if (self == null)
		{
			self = new ActorEngine();
		}
		
		return self;
	}
	
	private ActorEngine()
	{
		super();
		
		playerMap 	= new HashMap<String, Player>();
		npcMap		= new HashMap<String, NPC>();
		
		loadAllNPC();
	}
	
	public void loadAllNPC()
	{
		String 		npcDirectoryPath	= gameContent + "/npc";
		File 		npcDirectory 		= new File(npcDirectoryPath);
		String[]	npcList				= npcDirectory.list();
		
		try
		{
			for(String fileName : npcList)
			{
				if(fileName.contains(".txt~") == false)
				{
					NPC		npc			= new NPC();
					File 	npcFile 	= new File(npcDirectoryPath + "/" + fileName);
					Scanner lineScanner	= new Scanner(npcFile);
					Scanner wordScanner;
					
					while(lineScanner.hasNextLine())
					{
						String line = lineScanner.nextLine();
						
						wordScanner = new Scanner(line);
						
						String word = wordScanner.next();
						
						if(word.equals("<race>"))
						{
							String race = wordScanner.next();
							
							if(race.equals("race_avian"))
							{
								npc.setRace(Actor.race_avian);
							}
							else if(race.equals("race_drake"))
							{
								npc.setRace(Actor.race_drake);
							}
							else if(race.equals("race_gloam"))
							{
								npc.setRace(Actor.race_gloam);
							}
							else if(race.equals("race_sol"))
							{
								npc.setRace(Actor.race_sol);
							}
							else if(race.equals("race_hulk"))
							{
								npc.setRace(Actor.race_hulk);
							}
							else if(race.equals("race_human"))
							{
								npc.setRace(Actor.race_human);
							}
							else if(race.equals("race_nymph"))
							{
								npc.setRace(Actor.race_nymph);
							}
							else if(race.equals("race_tigris"))
							{
								npc.setRace(Actor.race_tigris);
							}
						}
						else if (word.equals("<actorID>"))
						{
							npc.setActorID(wordScanner.next());
						}
						else if (word.equals("<name>"))
						{
							npc.setName(wordScanner.nextLine().trim());
						}
						else if (word.equals("<plural_name>"))
						{
							npc.setPluralName(wordScanner.nextLine().trim());
						}
						else if (word.equals("<description0>"))
						{
							npc.getDescription()[0] = wordScanner.nextLine().trim();
						}
						else if (word.equals("<description1>"))
						{
							npc.getDescription()[1] = wordScanner.nextLine().trim();
						}						
						else if (word.equals("<roomID>"))
						{
							npc.setRoomID(wordScanner.next());
						}
						else if (word.equals("<stat_strength>"))
						{
							npc.getStats()[ActorDefinitions.stat_strength] = wordScanner.nextInt();
						}
						else if (word.equals("<stat_constitution>"))
						{
							npc.getStats()[ActorDefinitions.stat_constitution] = wordScanner.nextInt();
						}
						else if (word.equals("<stat_energy>"))
						{
							npc.getStats()[ActorDefinitions.stat_energy] = wordScanner.nextInt();
						}
						else if (word.equals("<stat_focus>"))
						{
							npc.getStats()[ActorDefinitions.stat_focus] = wordScanner.nextInt();
						}
						else if (word.equals("<stat_agility>"))
						{
							npc.getStats()[ActorDefinitions.stat_agility] = wordScanner.nextInt();
						}
						else if (word.equals("<stat_awareness>"))
						{
							npc.getStats()[ActorDefinitions.stat_awareness] = wordScanner.nextInt();
						}
						else if (word.equals("<gender>"))
						{
							String gender = wordScanner.next();
							
							if (gender.equals("female"))
							{
								npc.setGender(ActorDefinitions.gender_female);
							}
							else
							{
								npc.setGender(ActorDefinitions.gender_male);
							}
						}
						else if (word.equals("<is_aggro>"))
						{
							if (wordScanner.next().equals("true"))
							{
								npc.setAggro(true);
							}
							else npc.setAggro(false);							
						}
						else if (word.equals("<hp>"))
						{
							npc.setMaxHP(wordScanner.nextInt());
							npc.setCurrentHP(wordScanner.nextInt());
						}
						else if (word.equals("<mana>"))
						{
							npc.setMaxMana(wordScanner.nextInt());
							npc.setCurrentMana(wordScanner.nextInt());
						}
						
						npcMap.put(npc.getActorID(), npc);
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FileNotFound");
		}
	}
	
	public Player loadPlayer(String playerName)
	{
		try
		{
			File	playerFile	= new File(gameContent + "/players/" + playerName + ".txt");
			Player	player		= new Player();
			Scanner lineScanner	= new Scanner(playerFile);
			Scanner wordScanner;
			
			while(lineScanner.hasNextLine())
			{
				String line = lineScanner.nextLine();
				
				wordScanner = new Scanner(line);
				
				String word	= wordScanner.next();
				
				if(word.equals("<race>"))
				{
					String race = wordScanner.next();
					
					if(race.equals("race_avian"))
					{
						player.setRace(Actor.race_avian);
					}
					else if(race.equals("race_drake"))
					{
						player.setRace(Actor.race_drake);
					}
					else if(race.equals("race_gloam"))
					{
						player.setRace(Actor.race_gloam);
					}
					else if(race.equals("race_sol"))
					{
						player.setRace(Actor.race_sol);
					}
					else if(race.equals("race_hulk"))
					{
						player.setRace(Actor.race_hulk);
					}
					else if(race.equals("race_human"))
					{
						player.setRace(Actor.race_human);
					}
					else if(race.equals("race_nymph"))
					{
						player.setRace(Actor.race_nymph);
					}
					else if(race.equals("race_tigris"))
					{
						player.setRace(Actor.race_tigris);
					}
				}
				else if (word.equals("<actorID>"))
				{
					player.setActorID(wordScanner.next());
				}
				else if (word.equals("<name>"))
				{
					player.setName(wordScanner.next());
				}
				else if (word.equals("<description0>"))
				{
					player.getDescription()[0] = wordScanner.nextLine().trim();
				}
				else if (word.equals("<description1>"))
				{
					player.getDescription()[1] = wordScanner.nextLine().trim();
				}
				else if (word.equals("<description2>"))
				{
					player.getDescription()[2] = wordScanner.nextLine().trim();
				}
				else if (word.equals("<description3>"))
				{
					player.getDescription()[3] = wordScanner.nextLine().trim();
				}
				else if (word.equals("<description4>"))
				{
					player.getDescription()[4] = wordScanner.nextLine().trim();
				}
				else if (word.equals("<roomID>"))
				{
					player.setRoomID(wordScanner.next());
				}
				else if (word.equals("<stat_strength>"))
				{
					player.getStats()[ActorDefinitions.stat_strength] = wordScanner.nextInt();
				}
				else if (word.equals("<stat_constitution>"))
				{
					player.getStats()[ActorDefinitions.stat_constitution] = wordScanner.nextInt();
				}
				else if (word.equals("<stat_energy>"))
				{
					player.getStats()[ActorDefinitions.stat_energy] = wordScanner.nextInt();
				}
				else if (word.equals("<stat_focus>"))
				{
					player.getStats()[ActorDefinitions.stat_focus] = wordScanner.nextInt();
				}
				else if (word.equals("<stat_agility>"))
				{
					player.getStats()[ActorDefinitions.stat_agility] = wordScanner.nextInt();
				}
				else if (word.equals("<stat_awareness>"))
				{
					player.getStats()[ActorDefinitions.stat_awareness] = wordScanner.nextInt();
				}
				else if (word.equals("<gender>"))
				{
					String gender = wordScanner.next();
					
					if (gender.equals("female"))
					{
						player.setGender(ActorDefinitions.gender_female);
					}
					else
					{
						player.setGender(ActorDefinitions.gender_male);
					}
				}
				else if (word.equals("<hp>"))
				{
					player.setMaxHP(wordScanner.nextInt());
					player.setCurrentHP(wordScanner.nextInt());
				}
				else if (word.equals("<mana>"))
				{
					player.setMaxMana(wordScanner.nextInt());
					player.setCurrentMana(wordScanner.nextInt());
				}
			}	
			
			playerMap.put(player.getName(), player);
			
			return player;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Player " + playerName + " not found!");
			return null;
		}
	}
	
	public Player getPlayer(String ID)
	{
		if (playerMap.containsKey(ID))
		{
			return playerMap.get(ID);
		}
		else return null;
	}
	
	public static String parceRace(int race)
	{
		switch(race)
		{
			case ActorDefinitions.race_avian: 	return "Avian";
			case ActorDefinitions.race_drake:	return "Drake";
			case ActorDefinitions.race_gloam: 	return "Gloam";
			case ActorDefinitions.race_hulk:  	return "Hulk";
			case ActorDefinitions.race_human: 	return "Human";
			case ActorDefinitions.race_nymph: 	return "Nymph";
			case ActorDefinitions.race_sol:   	return "Sol";
			case ActorDefinitions.race_tigris: 	return "Tigris";
			default: 							return "Human";
		}
	}
	
	public NPC getNPC(String id)
	{
		if (npcMap.containsKey(id))
		{
			return npcMap.get(id).getInstance();
		}
		else return null;
	}
	
	public Actor getActorByNameFromRoom(String name, String roomID)
	{
		Room 				room 	= RoomEngine.getRoomEngine().getRoom(roomID);
		LinkedList<NPC> 	NPCs	= room.getNPCs();
		LinkedList<Player>	players	= room.getPlayers();
		
		for(NPC actor : NPCs)
		{
			if(actor.getName().toLowerCase().startsWith(name.toLowerCase()))
			{
				return actor;
			}
		}
		
		for(Player actor : players)
		{
			if(actor.getName().toLowerCase().startsWith(name.toLowerCase()))
			{
				return actor;
			}
		}
		
		return null;
	}
}
