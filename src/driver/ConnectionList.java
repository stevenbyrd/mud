package driver;

import java.util.LinkedList;

public class ConnectionList 
{
	private LinkedList<Connection> 	connectionList;
	private int						numPasscards;
	
	public ConnectionList()
	{
		connectionList 	= new LinkedList<Connection>();
		numPasscards	= 3;
	}
	
	public synchronized void requestPasscards(int requestedNumber)
	{
		try
		{
			while ((numPasscards - requestedNumber) < 0)
			{
				wait();
			}
			
			numPasscards -= requestedNumber;
		}
		catch (InterruptedException e)
		{
			System.out.println("Problem obtaining lock in ConnectionList");
		}
	}
	
	public synchronized void returnPasscards(int numberReturned)
	{
		numPasscards += numberReturned;
		notifyAll();
	}
	
	public synchronized void addConnection(Connection connection)
	{
		if (connectionList.contains(connection) == false)
		{
			connectionList.add(connection);
		}
	}
	
	public synchronized void removeConnection(Connection connection)
	{
		if (connectionList.contains(connection) == true)
		{
			connectionList.remove(connection);
		}
	}
	
	public synchronized LinkedList<Connection> getConnectionList()
	{
		return connectionList;
	}
}
