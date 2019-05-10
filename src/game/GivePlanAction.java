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
		// check who can do this action
		if (actor instanceof Player) {
			for (int i=0;i<tempList.size();i++) {
				if (tempList.get(i).getDisplayChar()=='&') {
					rocketBody=tempList.get(i);
				}  
			}
			tempList=player.getInventory();
			// check whether rocket plan is inside inventory of player or not
			for (int i=0;i<tempList.size();i++) {
				//if so add  rocket body to inventory of player and remove rocket plan from player 
				if (tempList.get(i).getDisplayChar()=='[') {
					neededRocketPlan=tempList.get(i);
					player.removeItemFromInventory(neededRocketPlan);
					player.addItemToInventory(rocketBody);
					// remove Q from the map after it give rocket body
					map.removeActor(this.actor);
					return this.actor+" gave the rocket body to "+ player +" and  phew phew" +this.actor +"disapear";
				}
			}
			return player+" doesnt have rocket plan to get rocket body";
		}
		return null;
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
