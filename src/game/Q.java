package game;

import java.util.ArrayList;
import java.util.List;
import edu.monash.fit2099.engine.*;

public class Q extends Actor {

	public Q(String name, Actor player) {
		super(name, 'q', 2, 1000);
		// TODO Auto-generated constructor stub
	
	
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.clear();
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
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}

}
