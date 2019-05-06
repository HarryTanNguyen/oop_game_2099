package game;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
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
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if (hasKey==true) {
			map.add(new Floor(), doorLocation);
			player.removeItemFromInventory(key);
			return actor +"opened a door and key were removed form the inventory"; 
		}
		else {
			return actor+"dont have any key so cannot open";
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+"is finding the key";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
