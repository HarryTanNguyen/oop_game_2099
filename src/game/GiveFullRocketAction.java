package game;

import java.util.List;

import edu.monash.fit2099.engine.*;

public class GiveFullRocketAction extends Action {
	private Actor player;
	private Item neededRocketEngine=null;
	private Item neededRocketBody=null;
	/**
	 * Constructor
	 * @param subject
	 */
	public GiveFullRocketAction(Actor subject) {
		this.player=subject;
	}
	
	
	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//check who can do this action
		if (actor instanceof Player==true) {
			List<Item> tempList= actor.getInventory();
			// check rocket body and rocket engine in the inventory of player
			for (int i=0;i<tempList.size();i++) {
				if(tempList.get(i).getDisplayChar()=='&') {
					neededRocketBody=tempList.get(i);
				}
				else if(tempList.get(i).getDisplayChar()=='E') {
					neededRocketEngine=tempList.get(i);
				}
			}
			// if player has rocket body and rocket engine, give player full rocket
			if (neededRocketEngine!=null && neededRocketBody!=null) {
				Item Rocket=new Item("Rocket",'^');
				actor.removeItemFromInventory(neededRocketBody);
				actor.removeItemFromInventory(neededRocketEngine);
				actor.addItemToInventory(Rocket);
				return actor + " got the rocket after assembling rocket engine and rocket body";
			}
			else {
				return actor + " doesn't have enough parts of rocket to get the full rocket";
			}
		}
		return null;
		
	}
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " assembles rocket";
	}
	/**
	 * Returns the key used in the menu to trigger this Action.
	 * @return The key we use for this Action in the menu.
	 */

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
