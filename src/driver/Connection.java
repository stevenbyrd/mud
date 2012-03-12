package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import actor.Player;

public class Connection
{
	private Socket 				socket;
	private PrintWriter			output;
	private BufferedReader		input;
	private Player				player;
	private MessageBuffer		inputBuffer;
	private MessageBuffer		outputBuffer;
	private boolean				connected;
	
	public Connection(Socket sckt, Player p)
	{
		try
		{
			socket 			= sckt;
			output 			= new PrintWriter(socket.getOutputStream(), true);
			input			= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			player			= p;
			inputBuffer		= new MessageBuffer();
			outputBuffer	= new MessageBuffer();
			connected		= true;
		}
		catch(IOException e1)
		{
			System.out.println("Problem accepting connection for player: " + player.getName());
		}
	}
	
	public PrintWriter getOutputStream()
	{
		return output;
	}
	
	public BufferedReader getInputStream()
	{
		return input;
	}
	
	public synchronized void send(String message)
	{
		outputBuffer.add(message);
	}
	
	public synchronized void sendFinal(String message)
	{
		outputBuffer.add(message);
		outputBuffer.add(ANSI.DARK_MAGENTA + "[" + ANSI.YELLOW + "HP: " + ANSI.WHITE + player.getCurrentHP() + ANSI.YELLOW + ", Mana: " + ANSI.WHITE + player.getCurrentMana() + ANSI.DARK_MAGENTA + "]" + ANSI.SANE);
	}
	
	public MessageBuffer getInputBuffer()
	{
		return inputBuffer;
	}
	
	public MessageBuffer getOutputBuffer()
	{
		return outputBuffer;
	}
	
	public void disconnect()
	{
		connected = false;
		
		try 
		{
			output.flush();
			output.close();
			input.close();
			socket.close();
		} 
		catch (IOException e) 
		{
			System.err.println("Error disconnecting player: " + player.getName());
			e.printStackTrace();
		}
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public synchronized boolean isConnected()
	{
		return connected;
	}
}
