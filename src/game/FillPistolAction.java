package game;


import edu.monash.fit2099.engine.*;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;

public class FillPistolAction extends Action {

	private Actor player;
	
	/**
	 * Constructor
	 * @param subject
	 */
	public FillPistolAction(Actor subject) {
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
		
		// Check target is the player
		if (actor instanceof Player) { 
			
			List<Item> tempList = actor.getInventory();
			
			// Check that pistol is in inventory of player
			for (int i = 0; i < tempList.size();i++) { 
				if (tempList.get(i).getDisplayChar()=='p') {
					Item FilledPistol = new Item("FilledPistol", 'P');
					player.addItemToInventory(FilledPistol);
					
					return actor + "filled up the pistol with water";				
				}
				
				else {
					return actor + "does not have a pistol";
				}
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
		return actor + " refills pistol";
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
