package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Ninja extends Actor {
/**
 * Constructor for Ninja
 * @param name
 * @param player
 * 
 * Symbol "N" represents ninja, with 50hp and priority 6
 * StunBehaviour is added to ninja
 */
	// Ninja have 50 hitpoints and are always represented with a "N"
	public Ninja(String name, Actor player) {
		super(name, 'N', 6, 50);
		addBehaviour(new StunBehaviour(player));
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
