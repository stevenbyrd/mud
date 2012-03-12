package command;

import driver.Connection;

public interface Command 
{
	public void execute(Connection connection, String target);
}
