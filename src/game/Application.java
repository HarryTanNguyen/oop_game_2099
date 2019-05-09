package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(), new RocketPad());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....+...+....#....#....",
				"....#####....##+###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".........=.............",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 2, 2);
		
		
		Item Key= Item.newInventoryItem("Key", 'k');
		Item RocketPlan= new Item ("Rocket Plan",'[');
		Item RocketBody= Item.newFurniture("Rocket Body",'&');
		
		gameMap.addItem(RocketPlan, 14, 3);
	
		Q q=new Q("Bad Guy",player);
		q.addItemToInventory(RocketBody);
		gameMap.addActor(q, 2, 3);
				
		Goons goon=new Goons("Harry",player);
		goon.addItemToInventory(Key);
		gameMap.addActor(goon, 3, 3);
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0, 0);
		
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  10, 10);
		
		Ninja ninja = new Ninja("Naruto", player);
		Item StunPowder = Item.newFurniture("Stun Powder", '*');
		ninja .addItemToInventory(StunPowder);
		gameMap.addActor(ninja, 1, 2);
			
		Doctor doctorMaybe = new Doctor("DoctorMaybe", player);
		Item RocketEngine= Item.newInventoryItem("Rocket Engine",'E');		
		doctorMaybe.addItemToInventory(RocketEngine);
		gameMap.addActor(doctorMaybe, 6, 2);
		
		world.run();
	}
}
