package game;

import java.util.List;

import edu.monash.fit2099.engine.*;
public class ProducingOxygenTank extends Action {
	private Actor player;
	private boolean hasOxygenTank;
	public ProducingOxygenTank(Actor player) {
		this.player=player;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor instanceof Player) {
			List<Item> itemList=map.locationOf(actor).getItems();
			for (int i=0;i<itemList.size();i++) {
				if (itemList.get(i).getDisplayChar()==']') {
					hasOxygenTank=true;
				}
			}
			if (hasOxygenTank==true) {
				return this.player+" is standing in a oxygen tank so cannot produce another one in same location";
			}
			else {
				Item oxygentank =new Item("OxygenTank",']');
				map.addItem(oxygentank,map.locationOf(actor).x(),map.locationOf(actor).y());
				return "A oxygen tank is produced";
			}
		}
		
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return this.player +" turns on oxygen dispenser to produce oxygen tank";
	
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
