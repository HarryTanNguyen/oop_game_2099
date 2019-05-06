package game;
import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Goons extends Actor {

	public Goons(String name, Actor player) {
		// TODO Auto-generated constructor stub
		super(name,'G',4,100);
		addBehaviour(new FollowBehaviour(player));
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

}
