package game;
import edu.monash.fit2099.engine.*;
public class ShoutingAction extends Action {
	private String shoutingSetence;
	private Actor target;
	
	public ShoutingAction(Actor subject,String sentence) {
		this.target=subject;
		this.shoutingSetence=sentence;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return this.shoutingSetence;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}

}
