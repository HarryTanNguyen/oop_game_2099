package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Doctor extends Actor{
	
	// Ninja have 25 hitpoints and are always represented with a "D"
	public Doctor(String name, Actor player) {
		super(name, 'D', 7, 25);
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
