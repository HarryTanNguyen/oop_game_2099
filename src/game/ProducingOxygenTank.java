package game;

import java.util.List;

import edu.monash.fit2099.engine.*;
public class ProducingOxygenTank extends Action {
	private Actor player;
	private boolean hasOxygenTank;
	private boolean canProduceOxygenTank;
	private int timeCanProduce;
	public ProducingOxygenTank(Actor player) {
		this.player=player;
		canProduceOxygenTank=true;
		timeCanProduce=2;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor instanceof Player) {
			if(canProduceOxygenTank==true) {
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
					canProduceOxygenTank=false;
					return "A oxygen tank is being produced";
				}
			}
			else if (canProduceOxygenTank==false) {
				timeCanProduce--;
				if(timeCanProduce==0) {
					Item oxygentank =new Item("OxygenTank",']');
					map.addItem(oxygentank,map.locationOf(actor).x(),map.locationOf(actor).y());
					canProduceOxygenTank=true;
					timeCanProduce=2;
				}
				return "Oxygen Dispenser is producing oxygen tank "+ this.player +" have to wait";
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
