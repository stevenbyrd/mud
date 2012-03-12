package engine;

import java.util.HashMap;

public class AffectEngine 
{
	private static HashMap<String, String[]> nameMap = new HashMap<String, String[]>();
	
	public static void init()
	{
		nameMap.put("recall", new String[]{"PCRecall"});
	}
	
	public static String[] getComponentsForAffect(String affect)
	{
		String[] className = new String[]{};
		
		if (nameMap.containsKey(affect))
		{
			className = nameMap.get(affect);
		}
		
		return className;
	}
}
