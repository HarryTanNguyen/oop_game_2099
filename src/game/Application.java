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
		WorldGame world = new WorldGame(new Display());
		
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(),new Water(), new Mud(), new Wall(),new Door(),new Rock());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....+...+....#....#....",
				"....#####....##+###....",
				".......................",
				".W.....................",
				".......................",
				".......................",
				".......................",
				".......................");
		
		
		List<String> marMap = Arrays.asList(
				"ooooooooooooooooooooooo",
				"ooooooo~~~~~~oooooo~~~o",
				"ooooo~~~~ooooo~~~~~~~oo",
				"ooooooo~~~~~~~~~~oooooo",
				"ooooooo~~~~~ooo~ooooooo",
				"ooooooooo~~oooo~ooooooo",
				"ooooooooo~oooo~~~oooooo",
				"ooooo~~~~~oooo~~~~~~~oo",
				"oo~~~~~oooooooo~~~~~ooo",
				"o~~~~~~ooooooooo~~~~~~o",
				"o~~~~ooooooooo~~~~~~~~o");
		
		// Creating Earth map and Moon map
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		GameMap mars=new GameMap(groundFactory,marMap);
		world.addMap(mars);
		
		// Creating all the actors and items involved 
		// Player
		Actor player = new GamePlayer("Player", '@', 1, 100,gameMap);
		world.addPlayer(player, gameMap, 2, 2);
		
		// Final Boss
		Boss boss = new Boss("YugoMaxx", player);
		Item exoskeleton = new Item("Exoskeleton", ':');
		Item bodyOfYugo= Item.newInventoryItem("Body of Yugo", 'U');
		exoskeleton.getAllowableActions().add(new ShootBossAction(player, boss));
		boss.addItemToInventory(exoskeleton);
		boss.addItemToInventory(bodyOfYugo);
		mars.addActor(boss, 20, 9);
		
		// Water pistol
		Item pistol = new Item("EmptyPistol", 'p');
		mars.addItem(pistol, 6, 7);
		
		
		// Two keys
		Item Key1= 	Item.newInventoryItem("Key", 'k');
		Item Key2 = Item.newInventoryItem("Key", 'k');
		
		// Rocket parts
		Item RocketPlan= new Item ("Rocket Plan",'[');
		Item RocketBody= Item.newFurniture("Rocket Body",'&');
		
		Item RocketPad=Item.newFurniture("Rocket Pad", '=');
		gameMap.addItem(RocketPad, 10, 9);
		player.addItemToInventory(RocketBody);
		//RocketPad.addNewAction(new GiveFullRocketAction(player,gameMap.at(10,9),mars.at(8, 4)));
		RocketPad.getAllowableActions().add(new GiveFullRocketAction(player,gameMap.at(10,9),mars.at(8, 4)));
		gameMap.addItem(RocketPlan, 14, 3);
		
		Item Rocket=Item.newFurniture("Rocket", '^');
		mars.addItem(Rocket, 8, 4);
		Rocket.getAllowableActions().add(new MoveActorAction(gameMap.at(10, 9),"to Earth"));
		
		// Spacesuit
		Item SpaceSuit= new Item("Space Suit",'[');
		SpaceSuit.addSkill(PlayerSkill.SPACETRAVELLER);
		gameMap.addItem(SpaceSuit,10,8);
		
		// Oxygen tank
		Item OxygenTank=new Item("OxygenTank",']');
		gameMap.addItem(OxygenTank, 10, 7);
		
		Item OxygenDispenser=Item.newFurniture("Oxygen Dispenser",'$');
		gameMap.addItem(OxygenDispenser, 9, 7);
		
		OxygenDispenser.getAllowableActions().add(new ProducingOxygenTank(player));
		
		// Q
		Q q=new Q("Bad Guy",player);
		//q.addItemToInventory(RocketBody);
		gameMap.addActor(q, 2, 3);
			
		// Goons
		Goons goon1=new Goons("Harry",player);
		gameMap.addActor(goon1, 3, 3);
		goon1.addItemToInventory(Key1);
		
		Goons goon2=new Goons("Jayden",player);
		gameMap.addActor(goon2, 8, 8);
		goon2.addItemToInventory(Key2);
		
		// Grunt
		Grunt grunt = new Grunt("Mongo", player);
		grunt.addItemToInventory(Key2);
		gameMap.addActor(grunt, 0, 0);
		
		// Ninja
		Ninja ninja = new Ninja("Naruto", player);
		Item StunPowder = Item.newFurniture("Stun Powder", '*');
		ninja .addItemToInventory(StunPowder);
		gameMap.addActor(ninja, 1, 2);
			
		// Doctor Maybe
		Doctor doctorMaybe = new Doctor("DoctorMaybe", player);
		Item RocketEngine= Item.newInventoryItem("Rocket Engine",'E');		
		doctorMaybe.addItemToInventory(RocketEngine);
		gameMap.addActor(doctorMaybe, 6, 2);
		//player.addItemToInventory(RocketEngine);
		
		world.run();
	}
}
