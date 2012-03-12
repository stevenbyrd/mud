package affect;

import actor.Actor;

abstract class AffectPipelineComponent 
{
	abstract void execute(Actor caster, Actor target, String[] inputs);
}
