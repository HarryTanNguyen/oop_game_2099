package edu.monash.fit2099.engine;

/**
 * Class representing terrain type
 */
public class Ground implements Skilled, Printable {
	
	
	private Skills skills = new Skills();
	protected char displayChar;

	/**
	 * Constructor.
	 *
	 * @param displayChar character to display for this type of terrain
	 */
	public Ground(char displayChar) {
		this.displayChar = displayChar;
	}

	public char getDisplayChar() {
		return displayChar;
	}

	/**
	 * Returns an empty Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions();
	}

	/**
	 * Returns a MoveActorAction that will move actor to location if the terrain type allows.
	 *
	 * @param actor the Actor moving
	 * @param location the destination
	 * @param direction the direction of the destination from actor
	 * @param hotKey the character that will trigger the movement command
	 * @return a MoveActorAction that allows actor to move to location if allowed; otherwise null
	 */
	public MoveActorAction getMoveAction(Actor actor, Location location, String direction, String hotKey) {
		if(canActorEnter(actor))
			return new MoveActorAction(location, direction, hotKey);
		
		return null;
	}

	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor
	 * @return true
	 */
	public boolean canActorEnter(Actor actor) {
		return true;
	}

	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 * @return true
	 */
	public boolean blocksThrownObjects() {
		return false;
	}

	public boolean hasSkill(Enum skill) {
		return skills.hasSkill(skill);
	}

	public void addSkill(Enum skill) {
		skills.addSkill(skill);
	}

	public void removeSkill(Enum skill) {
		skills.removeSkill(skill);
	}
}
