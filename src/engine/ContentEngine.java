package engine;

import java.io.File;

public class ContentEngine 
{
	protected String gameContent;
	
	public ContentEngine()
	{
		gameContent	= getGameContentDirectory(System.getProperty("user.dir"));
	}
	
	public String getGameContentDirectory(String pwdPath)
	{
		File		pwdFile	= new File(pwdPath);
		String[]	dirList	= pwdFile.list();
		
		if (dirList != null)
		{
			for(String fileName : dirList)
			{
				if (fileName.equals("gameContent"))
				{
					return pwdPath + "/gameContent";
				}
				else
				{
					File recurseFile = new File(pwdPath + "/" + fileName);
					
					if (recurseFile.isDirectory())
					{
						String recursiveSearch = getGameContentDirectory(pwdPath + "/" + fileName);
						
						if (recursiveSearch != null)
						{
							return recursiveSearch;
						}
					}
				}
			}
		}
		
		return null;
	}
}
