package game;

import edu.monash.fit2099.engine.*;

public class StayBehaviour implements ActionFactory {
	
	private Actor target;

	public StayBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		
		if (currentDistance == 1) {
			return new AttackAction(actor, target);
		}
		
		return new SkipTurnAction();
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
