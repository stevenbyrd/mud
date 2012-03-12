package actor;

import driver.Connection;

public class Player extends Humanoid
{
	private Connection connection;
	
	public Player()
	{
		super();
		
		description = new String[5];
		uniqueID	= "" + System.currentTimeMillis();
	}
	
	public void setConnection(Connection c)
	{
		connection = c;
	}
	
	public Connection getConnection()
	{
		return connection;
	}
}
