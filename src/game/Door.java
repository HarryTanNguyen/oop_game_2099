package game;
import game.OpenDoor;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Door extends Ground{
	private boolean isOpen=false;
	public Door() {
		// TODO Auto-generated constructor stub
		super('+');
	}
	@Override
	public boolean canActorEnter(Actor actor) {
		return isOpen;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new OpenDoor(actor,location));
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	

}