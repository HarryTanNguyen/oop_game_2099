package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player that is being run in the application.
 */
public class GamePlayer extends Player{
	
	private Actor subject = this;
	private boolean stunned = false;
	
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param priority How early in the turn the player can act
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public GamePlayer(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
	}

	/**
	 * Play a turn. Doing this means displaying a menu to the user and getting their selected option.
	 *
	 * Ignores more than 26 options. We could do better. We could also roll out a
	 * dedicated menu class instead of having it here. Player is 90% menu.
	 *
	 * @param actions the actions to display
	 * @param map the map to display
	 * @param display the object that performs the console I/O
	 * @return the Action that the user selects
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		//GamePlayer subject = this;
		StunBehaviour stun_checker = new StunBehaviour(this.subject);
		int x = stun_checker.checkStun(this.subject);
		
		if (x != -1) {
			actions = new Actions();
			actions.add(new SkipTurnAction());
			
			int index;
			index = stun_checker.get_stunned_actor_index(subject);
			stun_checker.change_turn(index);

		}
		return showMenu(actions, display);
	}

	/**
	 * Display a menu to the user and have them select an option.
	 *
	 * @param actions the Actions that the user can choose from
	 * @param display the I/O object that will display the map
	 * @return the Action selected by the user
	 */
	protected Action showMenu(Actions actions, Display display) {
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();
		
		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);

		for (Action action : actions) {
			String hotKey = action.hotKey();
			if (hotKey != "") {
				if (freeChars.isEmpty())
					break;
				char c = hotKey.charAt(0);
				freeChars.remove(Character.valueOf(c));
				keyToActionMap.put(c, action);
				display.println(hotKey + ": " + action.menuDescription(this));
			}
		}

		for (Action action : actions) {
			if (action.hotKey() == "") {
				if (freeChars.isEmpty())
					break;
				char c = freeChars.get(0);
				freeChars.remove(0);
				keyToActionMap.put(c, action);
				display.println(c + ": " + action.menuDescription(this));
			}
		}

		char key;
		do {
			key = display.readChar();
		} while (!keyToActionMap.containsKey(key));
		
		return keyToActionMap.get(key);
		}

	}

