package affect;

import java.util.LinkedList;

import actor.Actor;

public class Affect 
{
	public LinkedList<AffectPipelineComponent> pipeline;
	
	public Affect(String[] pipelineComponentNames)
	{
		pipeline = new LinkedList<AffectPipelineComponent>();
		
		for(String componentName : pipelineComponentNames)
		{
			try
			{
				Class<?> 				className = Class.forName("affect." + componentName);
				AffectPipelineComponent component = (AffectPipelineComponent)className.newInstance();
				
				pipeline.add(component);
			}
			catch (Exception ex)
			{
				System.err.println( ex + ": " + componentName + " class must be in class path.");
		    }
		}
	}
	
	public String[] execute(Actor caster, Actor target)
	{
		String[] retVal = new String[]{"", ""};
		
		for(AffectPipelineComponent component : pipeline)
		{
			component.execute(caster, target, retVal);
		}
		
		return retVal;
	}
}
