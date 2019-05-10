package game;

import java.util.ArrayList;
import java.util.List;
import edu.monash.fit2099.engine.*;

public class Q extends Actor {
	/**
	 * Constructor
	 * @param name
	 * @param player
	 * Q is represented by 'Q' with 1000 hit point 
	 */
	public Q(String name, Actor player) {
		// TODO Auto-generated constructor stub
		super(name, 'Q', 2, 1000);
		addBehaviour(new WanderBehavior(player));
			
	
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	/**
     * Returns a collection of the Actions that doesn't contain an AttackAction 
     * add 2 new actions: action to give rocket body action and action to talk to player
     * @param otherActor the Actor that might be performing actor
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.clear();
		list.add(new GivePlanAction(this,otherActor));
		list.add(new TalkAction(this,otherActor));
		return list;
	}
	/**
	 * Set the damage of weapon =0 
	 * by default Weapon has 5 damage but Q is NPC so it cannot deal any damage
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(0, "is a NPC so lich");
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this,map);
			if(action != null || action instanceof AttackAction==false )
				return action;
		}
		
		return new SkipTurnAction();
	}

}
