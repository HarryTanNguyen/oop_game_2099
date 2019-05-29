package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Water extends Ground{

	public Water() {
		super('W');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	/**
	 * return action to let player refill pistol
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new FillPistolAction(actor));
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
