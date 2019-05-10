package game;

import edu.monash.fit2099.engine.*;

public class RocketPad extends Ground {
	/**
	 * Constructor
	 * Rocket pad is represented by '='
	 */
	public RocketPad() {
		// TODO Auto-generated constructor stub
		super('=');
	}
	/**
	 * When player near to rocket pad player can assemble full rocket
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new GiveFullRocketAction(actor));
	}

}
