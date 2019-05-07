package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Ninja extends Actor {

	// Ninja have 50 hitpoints and are always represented with a "N"
	public Ninja(String name, Actor player) {
		super(name, 'N', 6, 50);
		addBehaviour(new FollowBehaviour(player));
	}	
	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}	
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}	
	
}
