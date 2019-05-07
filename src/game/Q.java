package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Q extends Actor {

	public Q(String name, Actor player) {
		super(name, 'q', 6, 0);
		// TODO Auto-generated constructor stub
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

}
