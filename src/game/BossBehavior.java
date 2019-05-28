package game;

import edu.monash.fit2099.engine.*;
import java.util.Random;

public class BossBehavior implements ActionFactory {

	private Actor target;
	
	public BossBehavior(Actor actor) {
		this.target=actor;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		// Check the distance between Yugo Maxx and player
		int distDiff;
		Location playerLocation = map.locationOf(this.target);
		Location bossLocation = map.locationOf(actor);
		
		distDiff = distance(bossLocation, playerLocation);
		
		// If player is not next to Yugo Maxx, Yugo Maxx wanders around
		if (distDiff > 1) {
			
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
		}
		
		// If player is next to Yugo Maxx, Yugo Maxx attacks player
		return new SkipTurnAction();
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
