package game;
import java.util.List;

import edu.monash.fit2099.engine.*;
public class TalkAction extends Action {
	private Actor q;
	private Actor player;
	public TalkAction (Actor q,Actor player) {
		this.q=q;
		this.player=player;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if (actor instanceof Player) {
			List<Item> tempList=actor.getInventory();
			for (int i=0;i<tempList.size();i++) {
				if(tempList.get(i).getDisplayChar()=='[') {
					return "Hand them over I dont have all day";
				}
			}
			return "I can give you something but im going to need rocket plan";
		}
		return "";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" talks to "+q;
		
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
