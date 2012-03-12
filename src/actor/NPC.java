package actor;

public class NPC extends Humanoid
{
	protected boolean	isAggro;
	protected double	wanderRate;
	protected double	fleeRate;
	protected double	spawnRate;
	protected String	pluralName;
	protected int		numberInRoom;
	protected int		minimumWait;
	protected int		spawnTime;
	
	public NPC()
	{
		super();
		
		isAggro 	 = false;
		wanderRate	 = 0;
		fleeRate	 = 0;
		description	 = new String[2];
		spawnRate	 = 0;
		numberInRoom = 0;
		minimumWait	 = 5000;
		spawnTime	 = (int)System.currentTimeMillis();
		uniqueID	 = "" + spawnTime;
	}
	
	public boolean isAggro() 
	{
		return isAggro;
	}

	public void setAggro(boolean isAggro) 
	{
		this.isAggro = isAggro;
	}

	public double getWanderRate() 
	{
		return wanderRate;
	}

	public void setWanderRate(double wanderRate) 
	{
		this.wanderRate = wanderRate;
	}

	public double getFleeRate() 
	{
		return fleeRate;
	}

	public void setFleeRate(double fleeRate) 
	{
		this.fleeRate = fleeRate;
	}
	
	public double getSpawnRate()
	{
		return spawnRate;
	}
	
	public void setSpawnRate(double rate)
	{
		spawnRate = rate;
	}
	
	public NPC getInstance()
	{
		NPC instance = new NPC();
		
		instance.setActorID(actorID);
		instance.setAggro(isAggro);
		instance.setDescription(description);		
		instance.setFleeRate(fleeRate);
		instance.setName(name);
		instance.setRace(race);
		instance.setWanderRate(wanderRate);
		instance.setStats(stats);
		instance.setSpawnRate(spawnRate);
		instance.setNumberInRoom(numberInRoom);
		instance.setPluralName(pluralName);
		instance.setMinimumWait(minimumWait);
		
		return instance;
	}
	
	public String getPluralName()
	{
		return pluralName;
	}
	
	public void setPluralName(String pN)
	{
		pluralName = pN;
	}
	
	public int getNumberInRoom()
	{
		return numberInRoom;
	}
	
	public void setNumberInRoom(int number)
	{
		numberInRoom = number;
	}
	
	public void incrementNumberInRoom()
	{
		numberInRoom++;
	}
	
	public void decrementNumberInRoom()
	{
		numberInRoom--;
	}
	
	public int getMinimumWait()
	{
		return minimumWait;
	}
	
	public void setMinimumWait(int wait)
	{
		minimumWait = wait;
	}
	
	public int getSpawnTime()
	{
		return spawnTime;
	}
	
	public void setSpawnTime(int time)
	{
		spawnTime = time;
	}
}
