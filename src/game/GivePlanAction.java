package game;

import edu.monash.fit2099.engine.*;
import java.util.List;

public class GivePlanAction extends Action {
	
	private Actor actor;
	private Actor player;
	
	public GivePlanAction(Actor actor,Actor player) {
		this.actor=actor;
		this.player=player;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Item neededRocketPlan;
		Item rocketBody = null;
		List<Item> tempList=this.actor.getInventory();
		for (int i=0;i<tempList.size();i++) {
			if (tempList.get(i).getDisplayChar()=='&') {
				rocketBody=tempList.get(i);
			}  
		}
		tempList=player.getInventory();
		for (int i=0;i<tempList.size();i++) {
			if (tempList.get(i).getDisplayChar()=='[') {
				neededRocketPlan=tempList.get(i);
				player.removeItemFromInventory(neededRocketPlan);
				player.addItemToInventory(rocketBody);
				
				map.removeActor(this.actor);
				return this.actor+" gave the rocket body to "+ player +" and  phew phew" +this.actor +"disapear";
			}
		}
		return player+" doesnt have rocket plan to get rocket body";
		
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor +" give plans to "+this.actor+" to get the Rocket body";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
