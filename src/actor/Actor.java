package actor;

public class Actor extends ActorFlags
{	
	protected String	actorID;
	protected String	uniqueID;
	protected String 	name;
	protected String[] 	description;
	protected int		race;
	protected int		gender;
	protected String	roomID;
	
	//stats
	protected int[]		stats;
	protected int		currentHP;
	protected int		maxHP;
	protected int		currentMana;
	protected int		maxMana;
	
	public Actor()
	{
		stats 		= new int[6];
		currentHP	= 0;
		maxHP		= 0;
		currentMana	= 0;
		maxMana		= 0;
		gender		= gender_female;
	}
	
	public String[] getDescription() 
	{
		return description;
	}

	public void setDescription(String[] description) 
	{
		this.description = description;
	}

	public String getActorID() 
	{
		return actorID;
	}
	
	public void setActorID(String actorID) 
	{
		this.actorID = actorID;
	}

	public String getUniqueID() 
	{
		return uniqueID;
	}
	
	public void setUniqueID(String uniqueID) 
	{
		this.uniqueID = uniqueID;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public int getRace() 
	{
		return race;
	}
	
	public void setRace(int race) 
	{
		this.race = race;
	}

	public void setRoomID(String roomID) 
	{
		this.roomID = roomID;
	}
	
	public String getRoomID()
	{
		return roomID;
	}
	
	public int[] getStats()
	{
		return stats;
	}
	
	public void setStats(int[] statArray)
	{
		stats = statArray;
	}
	
	public void setMaxHP(int hp)
	{
		maxHP = hp;
	}
	
	public int getMaxHP()
	{
		return maxHP;
	}
	
	public void setCurrentHP(int hp)
	{
		currentHP = Math.min(maxHP, hp);
		currentHP = Math.max(0, hp);
	}
	
	public int getCurrentHP()
	{
		return currentHP;
	}
	
	public void setMaxMana(int mana)
	{
		maxMana = mana;
	}
	
	public int getMaxMana()
	{
		return maxMana;
	}
	
	public void setCurrentMana(int mana)
	{
		currentMana = Math.max(maxMana, mana);
		currentMana = Math.min(0, mana);
	}
	
	public int getCurrentMana()
	{
		return currentMana;
	}
	
	public void setGender(int gndr)
	{
		if(gndr == 1)
		{
			gender = gender_male;
		}
		else
		{
			gender = gender_female;
		}
	}
	
	public int getGender()
	{
		return gender;
	}
}
