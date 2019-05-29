package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;
import java.util.Random;

public class ShootBossAction extends Action {

	private Actor player;
	private Actor target;
	
	/**
	 * Constructor
	 * @param subject
	 */
	public ShootBossAction(Actor subject, Actor target) {
		this.player=subject;
		this.target = target;

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
		
		// Check if target is player
		if (actor instanceof Player) {
			
			List<Item> tempList = actor.getInventory();
			// Check if player has a filled pistol
			for (int i = 0; i <tempList.size(); i++) { 
				if (tempList.get(i).getDisplayChar()=='P') {
					int FilledPistolIndex = i;
					
					// If player has a filled pistol, check if exoskeleton is broken
					List<Item> targetBag = target.getInventory();
					
					for (int j = 0; j < targetBag.size() ; j++) { 
						if(targetBag.get(j).getDisplayChar() == ';') {
							// Skeleton already broken, calculate if hits
							target.hurt(5);
							tempList.remove(FilledPistolIndex);
							return player + " shoots " + target + " for 5 damage with pistol. Pistol is empty now";
						}
						else {
							// skeleton not broken
							Random rand = new Random();
							int n = rand.nextInt(10);
							// 70% chance of destroying exoskeleton
							if (n < 7) {
								Item brokenSkeleton = new Item("BrokenSkeleton", ';');
								target.addItemToInventory(brokenSkeleton);
								tempList.remove(FilledPistolIndex);
								return player + " shoots water from pistol and breaks " + target +"'s exoskeleton";
							}
							tempList.remove(FilledPistolIndex);
							return player + " shoots water from pistol but did not break " + target + "'s exoskeleton";
						}
						
					}
					
				}
				return actor + "does not have a filled pistol to shoot";
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
		return actor + " shoots Yugo Maxx";
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
