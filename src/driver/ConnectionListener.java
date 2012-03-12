package driver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener extends Thread
{
	private ServerSocket 	serverSocket;
	private ConnectionList	connectionList;
	
	public ConnectionListener(ConnectionList cL)
	{		
		try 
		{
			serverSocket	= new ServerSocket(8888);
			connectionList	= cL;

			start();
		} 
		catch (IOException e) 
		{
			System.err.println("problem with server socket (in constructor)");
		}
	}
	
	public void run()
	{
		try 
		{
			while (true)
			{
				Socket socket = serverSocket.accept();
				
				if (socket != null)
				{
					new ConnectionBuilder(socket, connectionList);
				}
				
				sleep(500);
			}
		} 
		catch (IOException e) 
		{
			System.out.println("problem with server socket (in run())");
		} 
		catch (InterruptedException e) 
		{
			System.out.println("problem with sleep in ConnectionListener");
		}
	}
}
