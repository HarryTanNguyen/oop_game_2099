package game;

import edu.monash.fit2099.engine.*;

public class ExitGameAction extends Action {
	
	private WorldGame world;
	
	public ExitGameAction(WorldGame world) {
		this.world=world;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		// remove player from the world so the player in the world is null 
		this.world.setWantExitGame(true);
		return "Bye Bye Bye and see you again";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Exit Game";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
