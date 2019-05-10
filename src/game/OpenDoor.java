package game;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;


public class OpenDoor extends Action {
	private Actor player;
	private Location doorLocation;
	private boolean hasKey;
	private Item key;
	public OpenDoor(Actor player,Location doorLocation) {
		this.player=player;
		this.doorLocation=doorLocation;
		//find the key in the inventory of player
		List<Item> tempList= player.getInventory();
		for (int i=0;i<tempList.size();i++) {
			if (tempList.get(i).getDisplayChar()=='k') {
				hasKey=true;
				key=tempList.get(i);
				break;
			}
		}

		
	}

	@Override
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//Check only player can open the door
		if (actor instanceof Player==true) {
			
		//check whether player has key or not
			if (hasKey==true) {
				//if player has key, change door to the floor type
				map.add(new Floor(), doorLocation);
				//remove item from the inventory of player
				player.removeItemFromInventory(key);
				return actor +" opened a door and key were removed form the inventory"; 
			}
			else {
				return actor+" dooesn't have any key so cannot open";
			}
		}
		else {
			return actor +" is monster so cannot open door";
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" open door to enter a room";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
