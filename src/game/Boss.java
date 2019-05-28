package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class Boss extends Actor{

	/**
	 * Constructor
	 * @param name: name of monster
	 * @param player
	 * Boss is represented by 'B' with 20 hit points
	 */
	public Boss(String name, Actor player) {
		// TODO Auto-generated constructor stub
		super(name,'B',4,20);
		addBehaviour(new FollowBehaviour(player));
		addBehaviour(new InsultBehaviour(player));
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	/**
	 * add behavior to the action factories of actor
	 * @param behaviour
	 */
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
    /** Select and return an action to perform on the current turn.
    *
    *
    * @param actions collection of possible Actions for this Actor
	 * @param map     the map containing the Actor
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
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
