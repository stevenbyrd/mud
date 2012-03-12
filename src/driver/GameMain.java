package driver;

import java.util.HashMap;
import command.*;
import engine.AffectEngine;

public class GameMain
{
	private HashMap<String, Command>	commandList;
	private ConnectionList 				connectionList;
	
	public GameMain()
	{
		commandList		= new HashMap<String, Command>();	
		connectionList	= new ConnectionList();
		
		//create a hashmap of string->command classes
		generateCommandList();
		
		AffectEngine.init();
		
		//spin off the connection listener thread...
		new ConnectionListener(connectionList);
		
		//these two threads will constantly be pulling data from connections'
		//input streams and pushing data to their output streams (to/from buffers,
		//on both accounts)
		new InputDriver(connectionList);
		new OutputDriver(connectionList);
		
		//spin off the updater thread (collects player input from input buffers and crunches it through the game)
		new UpdateDriver(connectionList, commandList);
		
		//spin off the room driver thread, which cycles through all the rooms and "ages" the ones with players/NPCs
		new RoomDriver();
	}
	
	private void generateCommandList()
	{
		//looking
		commandList.put("look", new Look());
		commandList.put("Look", commandList.get("look"));
		commandList.put("l", commandList.get("look"));
		commandList.put("L", commandList.get("look"));
		commandList.put("ls", commandList.get("look"));
		
		//quitting
		commandList.put("quit", new Disconnect(connectionList));
		commandList.put("exit", commandList.get("quit"));
		
		//movement
		commandList.put("go", new MovePlayer());
		
		//social
		commandList.put("say", new Say());
		commandList.put("broadcast", new Broadcast(connectionList));
		commandList.put("broad", commandList.get("broadcast"));
		commandList.put("smile", new Smile());
		commandList.put("smil", commandList.get("smile"));
		commandList.put("wave", new Wave());
		commandList.put("laugh", new Laugh());
		commandList.put("grin", new Grin());
		commandList.put("nod", new Nod());
		commandList.put("shake", new Shake());
		commandList.put("bow", new SocialBow());
		commandList.put("tell", new Tell());
		
		//score
		commandList.put("stat", new Stats());
		commandList.put("st", commandList.get("stat"));
		commandList.put("stats", commandList.get("stat"));
		
		//casting
		commandList.put("cast", new Cast());
	}
	
	public static void main(String[] args)
	{
		new GameMain();
	}
}
