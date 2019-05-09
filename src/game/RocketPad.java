package game;

import edu.monash.fit2099.engine.*;

public class RocketPad extends Ground {

	public RocketPad() {
		// TODO Auto-generated constructor stub
		super('=');
	}
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new GiveFullRocketAction(actor));
	}

}
