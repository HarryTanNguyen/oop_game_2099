package game;

import edu.monash.fit2099.engine.*;

public class GivePlanAction extends Action {
	
	private Actor actor;
	private Actor player;
	
	public GivePlanAction(Actor actor,Actor player) {
		this.actor=actor;
		this.player=player;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor +" give plans to"+this.actor+"to get the Rocket body";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
