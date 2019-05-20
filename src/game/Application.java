package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.engine.Ground;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());
		
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(),new Rock());
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
				".......................",
				".......................");
		
		
		List<String> marMap = Arrays.asList(
				"ooooooooooooooooooooooo",
				"ooooooo......oooooo...o",
				"ooooo....ooooo.......oo",
				"ooooooo..........oooooo",
				"ooooooo.....ooo.ooooooo",
				"ooooooooo..oooo.ooooooo",
				"ooooooooo.oooo...oooooo",
				"ooooo.....oooo.......oo",
				"oo.....oooooooo.....ooo",
				"o......ooooooooo......o",
				"o....ooooooooo........o");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		GameMap mars=new GameMap(groundFactory,marMap);
		world.addMap(mars);
		

		
		Actor player = new GamePlayer("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 2, 2);
		
		Item Key1= 	Item.newInventoryItem("Key", 'k');
		Item Key2 = Item.newInventoryItem("Key", 'k');
		
		Item RocketPlan= new Item ("Rocket Plan",'[');
		Item RocketBody= Item.newFurniture("Rocket Body",'&');
		
		Item RocketPad=Item.newFurniture("Rocket Pad", '=');
		gameMap.addItem(RocketPad, 10, 9);
		//RocketPad.addNewAction(new GiveFullRocketAction(player,gameMap.at(10,9),mars.at(8, 4)));
		RocketPad.getAllowableActions().add(new GiveFullRocketAction(player,gameMap.at(10,9),mars.at(8, 4)));
		gameMap.addItem(RocketPlan, 14, 3);
		
		Item Rocket=Item.newFurniture("Rocket", '^');
		mars.addItem(Rocket, 8, 4);
		Rocket.getAllowableActions().add(new MoveActorAction(gameMap.at(10, 9),"to Earth"));
	
		Q q=new Q("Bad Guy",player);
		q.addItemToInventory(RocketBody);
		gameMap.addActor(q, 2, 3);
				
		Goons goon1=new Goons("Harry",player);
		gameMap.addActor(goon1, 3, 3);
		goon1.addItemToInventory(Key1);
		
		Goons goon2=new Goons("Jayden",player);
		gameMap.addActor(goon2, 8, 8);
		goon2.addItemToInventory(Key2);
		
		Grunt grunt = new Grunt("Mongo", player);
		grunt.addItemToInventory(Key2);
		gameMap.addActor(grunt, 0, 0);
		
		
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
