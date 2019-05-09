package game;

import java.util.ArrayList;
import java.util.List;
import edu.monash.fit2099.engine.*;

public class Q extends Actor {

	public Q(String name, Actor player) {
		// TODO Auto-generated constructor stub
		super(name, 'Q', 2, 1000);
		addBehaviour(new WanderBehavior(player));
			
	
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new GivePlanAction(this,otherActor));
		list.add(new TalkAction(this,otherActor));
		return list;
	}
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
