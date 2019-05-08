package game;
import edu.monash.fit2099.engine.*;
import java.util.ArrayList;
import java.util.Random;

public class InsultBehaviour implements ActionFactory {
	private ArrayList<String> listOfSentence= new ArrayList<String>();
	private Actor target;
	public InsultBehaviour(Actor subject) {
		this.target=subject;
		addInsultSentence();
	}
	private void addInsultSentence() {
		listOfSentence.add("Nupakachi");
		listOfSentence.add("Die Die Die");
		listOfSentence.add("Du Ma May");
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Random rand=new Random();
		int n=rand.nextInt(100);
		if (n<10) {
			int i=rand.nextInt(3);
			return new ShoutingAction(actor,listOfSentence.get(i));
		}
		else {
			return null;
		}
	}

}
