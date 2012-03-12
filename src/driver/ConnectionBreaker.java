package driver;

public class ConnectionBreaker extends Thread
{
	private ConnectionList connectionList;
	private Connection	   connection;
	
	public ConnectionBreaker(ConnectionList cL, Connection conn)
	{
		connectionList 	= cL;
		connection		= conn;
		
		start();
	}
	
	public void run()
	{
		connectionList.requestPasscards(3);
		connectionList.removeConnection(connection);
		connection.disconnect();
		connectionList.returnPasscards(3);
	}
}
