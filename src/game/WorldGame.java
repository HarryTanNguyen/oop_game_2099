package game;
import edu.monash.fit2099.engine.*;

public class WorldGame extends World {
	private boolean wantEndGame;


	public WorldGame(Display display) {
		super(display);
		this.wantEndGame=false;
		// TODO Auto-generated constructor stub
	}
	
	public void setWantExitGame(boolean decision) {
		wantEndGame=decision;
	}
	/**
	 * Condition to win the game
	 * @return true if player has body of final Boss in the earth
	 */
	public boolean isWinning() {
		if(actorLocations.locationOf(player).getDisplayChar()=='.') {
			
		
			for (int i=0;i<player.getInventory().size();i++) {
				if (player.getInventory().get(i).getDisplayChar()=='U') {
					return true;
				}
			}
		}
		return false;
	}
	
	
	@Override
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			for (Actor actor : actorLocations) {
				processActorTurn(actor);
				
			}
			//if player want to out game
			if (wantEndGame==true) {
				break;
			}
			//if player hit point <0, Game Over
			if (player.isConscious()==false) {
				System.out.println("Bye Bye and see you again");
				break;
			}
			
			if(isWinning()) {
				display.println(winGameMessage());
				break;
			}
			
		}
		if (wantEndGame==true || player.isConscious()==false)
		{
			display.println(endGameMessage());
		}
	}
	@Override
	/**
	 * Gives an Actor its turn.
	 *
	 * The Actions an Actor can take include:
	 * <ul>
	 *  <li> those conferred by items it is carrying </li>
	 *  <li> movement actions for the current location and terrain </li>
	 *  <li> actions that can be done to Actors in adjacent squares </li>
	 *  <li> actions that can be done using items in the current location </li>
	 *  <li> skipping a turn</li>
	 * </ul>
	 *
	 * @param actor the Actor whose turn it is.
	 */
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (actorLocations.isAnActorAt(destination)) {
				Actor adjacentActor = actorLocations.actorAt(destination);
				actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
			} else {
				Ground adjacentGround = map.groundAt(destination);
				actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
				actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
			}
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
		}
		actions.add(new SkipTurnAction());
		//Add option that player can exit game whenever they want
		if (actor instanceof GamePlayer) {
			actions.add(new ExitGameAction(this));
		}
		
		Action action = actor.playTurn(actions, map, display);
		String result = action.execute(actor, map);
		display.println(result);
	}
	/**
	 * return a string can be displayed if player wins the game
	 * @return
	 */
	private String winGameMessage() {
		return "Woah Woah You Won the GAME";
	
	}
	
}
