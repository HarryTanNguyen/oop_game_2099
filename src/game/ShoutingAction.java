package game;
import edu.monash.fit2099.engine.*;
public class ShoutingAction extends Action {
	private String shoutingSentence;
	private Actor target;
	
	public ShoutingAction(Actor subject,String sentence) {
		this.target=subject;
		this.shoutingSentence=sentence;
	}

	@Override
	/**
	 * return a text to describe shouting action 
	 * @param actor: is Goon
	 * return a string with shouting sentence
	 */
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return actor +" is shouting "+ this.shoutingSentence;
	}

	@Override
	/**
	 * This action do randomly so we dont need to have a choice
	 */
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
