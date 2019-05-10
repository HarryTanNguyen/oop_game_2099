package game;

import edu.monash.fit2099.engine.*;
import java.util.Random;

public class WanderBehavior implements ActionFactory {
	private Actor actor;
	public WanderBehavior(Actor actor) {
		this.actor=actor;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Random rand=new Random();
		int randomX=rand.nextInt(10);
		int randomY=rand.nextInt(10);
		Location here = map.locationOf(actor);
		// create the random destination
		Location there=new Location(map,randomX,randomY);
		
		//move to the random destination
		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return new SkipTurnAction();
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}


}
