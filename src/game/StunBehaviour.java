package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StunBehaviour implements ActionFactory{

	private Actor target;
	private List<Actor> stunned_actors = new ArrayList<Actor>();
	private List<Integer> remaining_turns = new ArrayList<Integer>();

	public StunBehaviour(Actor subject) {
		this.target = subject;
	}	

	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		// check if player is within 5 Manhattan distance (execute following if so)
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		
		int currentDistance = distance(here, there);
		
		// if player is within 5 Manhattan distance:
		if (currentDistance <= 5) {
				
			// stun player that has 50% chance of hitting
			Random rand = new Random();
			int n = rand.nextInt(10);
			
			// when n < 5, stun is successful 
			if (n < 5) {
				// successfully stun a target that is not currently stunned
				if( checkStun(target) == -1) { 
					stunned_actors.add(target);
					remaining_turns.add(2);
				}
				// stun has no impact if target is already stunned
				else {
					System.out.println(target + "is already stunned.");
				}
				
			}
			
			// Ninja moving one space away from player after trying to stun him
			
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					int newDistance = distance(destination, there);
					if (newDistance == currentDistance + 1) {
						return new MoveActorAction(destination, exit.getName());
					}
				}
			}
		}
		
		else {
			;
		}
			return null;
	}

	// Manhattan distance is used here and we want ninja to move if Manhattan distance is 5
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}	
	
	// check if a given target is currently stunned and the number of stun turns left if so
	private int checkStun(Actor target) {
		
		int turnsRemain = -1;
		Actor stunned_actor;
		
		for (int i = 0; i <stunned_actors.size(); i++) {
			stunned_actor = stunned_actors.get(i);
			if (stunned_actor == target) {
				turnsRemain = remaining_turns.get(i);
				break;
			}
		}
			
		return turnsRemain;
	}

}
