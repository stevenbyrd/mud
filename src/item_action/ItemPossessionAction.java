package item_action;

import item.Item;
import actor.Actor;


public abstract class ItemPossessionAction 
{
	protected Item item;
	
	public void onObtain(Actor obtainer)
	{
		
	}
	
	public void onGive(Actor giver, Actor receiver)
	{
		
	}
	
	public void onDrop(Actor dropper)
	{
		
	}
}
