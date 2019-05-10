package game;

import java.util.List;

import edu.monash.fit2099.engine.*;

public class GiveFullRocketAction extends Action {
	private Actor player;
	private Item neededRocketEngine=null;
	private Item neededRocketBody=null;
	
	public GiveFullRocketAction(Actor subject) {
		this.player=subject;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if (actor instanceof Player==true) {
			List<Item> tempList= actor.getInventory();
			for (int i=0;i<tempList.size();i++) {
				if(tempList.get(i).getDisplayChar()=='&') {
					neededRocketBody=tempList.get(i);
				}
				else if(tempList.get(i).getDisplayChar()=='E') {
					neededRocketEngine=tempList.get(i);
				}
			}
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

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " assembles rocket";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
