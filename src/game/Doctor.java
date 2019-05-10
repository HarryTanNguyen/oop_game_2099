package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Doctor extends Actor{

/**
 * Constructor of Doctor
 * @param name
 * @param player
 * 
 * Symbol "D" represents DoctorMaybe, with 25hp and priority 7
 * StayBehaviour is added to Doctor
 */
	// Ninja have 25 hitpoints and are always represented with a "D"
	public Doctor(String name, Actor player) {
		super(name, 'D', 7, 25);
		addBehaviour(new StayBehaviour(player));
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
