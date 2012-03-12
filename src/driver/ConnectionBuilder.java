package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import command.Broadcast;

import actor.Player;
import engine.ActorEngine;
import engine.RoomEngine;

public class ConnectionBuilder extends Thread
{
	private Socket			socket;
	private PrintWriter		output;
	private BufferedReader	input;
	private ConnectionList 	connectionList;
	private Connection		connection;
	
	public ConnectionBuilder(Socket s, ConnectionList cL)
	{
		try
		{
			socket			= s;
			output 			= new PrintWriter(socket.getOutputStream(), true);
			input			= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			connectionList 	= cL;
			connection		= null;
		
			start();
		}
		catch(IOException e)
		{
			System.err.println("Error setting up connection constructor");
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		String playerInput = null;
		
		output.println("");
		output.println("Welcome! Enter your name:");
		
		System.out.println("somebody connected");
		
		//load character
		try
		{
			while (playerInput == null)
			{
				playerInput = input.readLine();
				playerInput = ("" + playerInput.charAt(0)).toUpperCase() + playerInput.substring(1);
				
				if (playerInput != null)
				{
					Player player = ActorEngine.getActorEngine().loadPlayer(playerInput);
	
					if (player == null)
					{
						playerInput = null;
						output.println("Character name not recognized!");
						output.println("Enter your name:");
					}
					else
					{
						connection = new Connection(socket, player);
						
						player.setConnection(connection);
						
						//broadcast player's arrival on the server
						broadcastArrival(player);
						
						//add player to appropriate room and print that room's name
						//and description to the player's output buffer
						RoomEngine.getRoomEngine().getRoom(player.getRoomID()).addPlayer(player);
						connection.send("look");
						
						//add player to the connection list (semi-mutex)
						connectionList.requestPasscards(3);
						connectionList.addConnection(connection);
						connectionList.returnPasscards(3);
					}
				}
			}
		}
		catch (IOException e)
		{
			System.err.println("Error in connection builder run()");
			e.printStackTrace();
		}
	}
	
	private void broadcastArrival(Player player)
	{
		Broadcast broadcast = new Broadcast(connectionList);
		
		broadcast.execute(null, player.getName() + " has just connected.");		
	}
}
