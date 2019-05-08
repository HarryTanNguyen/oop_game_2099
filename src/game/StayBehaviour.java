package game;

import edu.monash.fit2099.engine.*;

public class StayBehaviour implements ActionFactory {
	
	private Actor target;

	public StayBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		return new SkipTurnAction();
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
