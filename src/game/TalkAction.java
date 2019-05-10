package game;
import java.util.List;

import edu.monash.fit2099.engine.*;
public class TalkAction extends Action {
	/**
	 * Talk to player.
	 *
	 * @param actor The actor performing the action.
	 * @param player 
	 * @return a description of what happened that can be displayed to the user.
	 */
	private Actor q;
	private Actor player;
	public TalkAction (Actor q,Actor player) {
		this.q=q;
		this.player=player;
	}
	
	@Override
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//Check who can do this action
		if (actor instanceof Player) {
			//check whether player has rocket plan or not
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
	/**
	 * return a choice that menu can choose
	 * @return a choice that can be chosen by player
	 */
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
