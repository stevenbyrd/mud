package affect;

import driver.ANSI;
import engine.RoomEngine;
import actor.Actor;
import actor.Player;

public class PCRecall extends AffectPipelineComponent 
{
	@Override
	void execute(Actor caster, Actor target, String[] retVal)
	{	
		RoomEngine.getRoomEngine().movePlayer((Player)caster, "0");
		
		retVal[0] = ANSI.WHITE + "You teleported home!" + ANSI.SANE + "";
	}
}
