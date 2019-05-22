package game;
import edu.monash.fit2099.engine.*;

public class Rock extends Ground {

	public Rock() {
		super('o');
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
