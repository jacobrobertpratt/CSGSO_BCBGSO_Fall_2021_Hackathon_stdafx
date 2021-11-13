package HackerS;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Hackers {

	private static int HP = 10;
	private String errorMessage = "";
	private static Random random;
	private Scanner scnr;
	private Player player;
	private Arena arena;
	
	private static class Arena
	{
		ArenaState state;
		ArrayList<ArenaState> arenas = new ArrayList<ArenaState>();
		Enemy[] enemies;
		private int arenaIndex;
		private int enemyIndex;
		
		Arena()
		{
			this.state = ArenaState.NONE;
			generateArena();
			enemyIndex = 0;
			arenaIndex = 0;
			enemies = new Enemy[state.getOpponents() + 1];
		}
		Arena(ArenaState arena)
		{
			this.state = arena;
			enemyIndex = 0;
			arenaIndex = 0;
			enemies = new Enemy[state.getOpponents() + 1];
			generateArena();
		}
		
		private void generateArena()
		{
			for(int i = 0; i < state.getOpponents(); i++)
			{
				//for(int j = 0; j < )
				/*
				 * Add new loop
				 * 
				 * algorithm for sorting the enemies by level
				 * 
				 * numEnemies
				 * numWeapons
				 * numRequired
				 * 
				 * numEnemies randomize
				 * 
				 * numWeapons randomize then sort
				 * 
				 */
				
				
				enemies[i] = (new Enemy(Enemy.EnemyInfo.valueOf(random.nextInt(Enemy.EnemyInfo.values().length)), Enemy.EnemyWeapon.valueOf(random.nextInt(Enemy.EnemyWeapon.values().length))));
				System.out.println("Info: " + enemies[i].state.values().length + " Weapons: " + enemies[i].weapon.values().length);
			}
			
			for (int i = 0; i < state.getOpponents() -1; i++){

		        for (int j = 0; j < state.getOpponents() -1; j++){

		            if (enemies[j].weapon.getValue() > enemies[j+1].weapon.getValue()){

		                Enemy temp = enemies[j];
		                enemies[j] = enemies[j + 1];
		                enemies[j + 1] = temp;
		            }
		            
		            System.out.println(enemies[j].state.getValue());
		        }    
		        System.out.println("err");
		    }
		}
		
		private int getArenaIndex()
		{
			return arenaIndex + 1;
		}
		
		private int getEnemyIndex()
		{
			return enemyIndex + 1;
		}
		
		private Enemy getEnemy()
		{
			System.out.println(enemyIndex);
			return enemies[enemyIndex];
		}
		
		private String printEnemy()
		{
			return getEnemy().printEnemy();
		}
		
		private enum ArenaState
		{
			NONE(0, 0, 0, "No Arena Selected"),
			HACKATHON(1, 1000, 2, "Hackathon"),
			INTERVIEW(2, 1000, 2, "Coding Interview");
			
			private final int value;
			private final int points;
			private final int opponents;
			private final String name;
			
			private static Map map = new HashMap<>();
			
			ArenaState(int value, int points, int opponents, String name) {
				this.value = value;
				this.points = points;
				this.opponents = opponents;
				this.name = name;
			}
			
			static {
				for(ArenaState arenaState : ArenaState.values()) {
					map.put(arenaState.value, arenaState);
				}
			}
			
			public static ArenaState valueOf(int key)
			{
				return (ArenaState) map.get(key);
			}
			
			public int getRank()
			{
				return value;
			}
			public int getPoints()
			{
				return points;
			}
			
			public int getOpponents()
			{
				return opponents;
			}
			
			public String getName()
			{
				return name;
			}
		}
	}
	
	
	private static class Enemy
	{
		EnemyInfo state;
		EnemyWeapon weapon;
		private int health;
		
		public Enemy()
		{
			state = EnemyInfo.STAN;
			weapon = EnemyWeapon.TEXTENTRY;
			this.health = HP;
		}
		
		public Enemy(EnemyInfo name, EnemyWeapon weapon)
		{
			this.state = name;
			this.weapon = weapon;
			this.health = HP;
		}
		
		public int getHealth()
		{
			return health;
		}
		
		public String printHealth()
		{
			String h = "";
			
			int[] t = new int[HP];
			
			for(int i = 0; i < health; i++) {
				h += "*";
			}
			for(int i = health; i < HP; i++) {
				h += "-";
			}
			
			return h;
		}
		
		public boolean damage(int damage)
		{
			//Return false if not possible (dead)
			if(damage >= health) return false;
			//Return true if damage is possible (and do damage)
			else {
				health -= damage;
				return true;
			}
		}
		
		private String printEnemy()
		{
			return (state.getName() + "\tHealth: [" + this.printHealth() + "]\tChallenge: " + this.weapon.getName());
		}
		
		
		private enum EnemyWeapon
		{
			TEXTENTRY(0, 1, "Text Entry"),
			TERMINALAPP(1, 2, "Terminal Application"),
			POPQUIZ(2, 3, "Pop Quiz");
			
			private final int value;
			private final int power;
			private final String name;
			
			private static Map map = new HashMap<>();
			
			EnemyWeapon(int value, int power, String name) {
				this.value = value;
				this.power = power;
				this.name = name;
			}
			
			static {
				for(EnemyWeapon enemyWeapon : EnemyWeapon.values()) {
					map.put(enemyWeapon.value, enemyWeapon);
				}
			}
			
			public static EnemyWeapon valueOf(int key)
			{
				return (EnemyWeapon) map.get(key);
			}
			
			public int getValue()
			{
				return value;
			}
			
			public int getPower()
			{
				return power;
			}
			
			public String getName()
			{
				return name;
			}
		}
		
		private enum EnemyInfo
		{
			STAN(0, "Stan", "Stan is a grad student.  He was forced to teach a course.  He makes you create a text entry coding project.  No IDE allowed.");
			
			private final int value;
			private final String name;
			private final String story;
			
			private static Map map = new HashMap<>();
			
			EnemyInfo(int value, String name, String story){
				this.value = value;
				this.name = name;
				this.story = story;
			}
			
			static {
				for(EnemyInfo enemyInfo : EnemyInfo.values()) {
					map.put(enemyInfo.value, enemyInfo);
				}
			}
			
			public static EnemyInfo valueOf(int key)
			{
				return (EnemyInfo) map.get(key);
			}
			
			public int getValue()
			{
				return value;
			}
			
			public String getName()
			{
				return name;
			}
			
			public String getStory()
			{
				return story;
			}
		}
		
	}
	
	private static class Player
	{
		public PlayerWeapon playerWeapon;
		public PlayerArmor playerArmor;
		
		private static String name;
		private int health;
		
		public Player(String playerName)
		{
			this.name = playerName;
			this.health = HP;
			playerWeapon = PlayerWeapon.MOUSE;
			playerArmor = PlayerArmor.FACEMASK;
		}
		
		public boolean doDamage(Enemy enemy)
		{
			return enemy.damage(playerWeapon.getPower());
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getHealth()
		{
			return health;
		}
		
		public String printHealth()
		{
			String h = "";
			
			int[] t = new int[HP];
			
			for(int i = 0; i < health; i++) {
				h += "*";
			}
			for(int i = health; i < HP; i++) {
				h += "-";
			}
			
			return h;
		}
		
		private enum PlayerWeapon
		{
			MOUSE(1, "Mouse", "Click"),
			KEYBOARD(2, "Keyboard", "Press a key or something"),
			TOUCHSCREEN(3, "Touch-Screen", "Touch the screen!");
			
			private final int power;
			private final String name;
			private final String description;
			
			PlayerWeapon(int power, String name, String description) {
				this.power = power;
				this.name = name;
				this.description = description;
			}
			
			public int getPower()
			{
				return power;
			}
			
			public String getName()
			{
				return name;
			}
			
			public String getAttack() {
				return description;
			}
		}
		
		private enum PlayerArmor
		{
			FACEMASK(1, "Face Mask"),
			HOODIE(2, "Hoodie"),
			WINTERJACKET(3, "Winter Jacket"),
			CARBOARDBOX(4, "Cardboard Box");
			
			private final int power;
			private final String name;
			
			PlayerArmor(int power, String name) {
				this.power = power;
				this.name = name;
			}
			
			public int getPower()
			{
				return power;
			}
			
			public String getName()
			{
				return name;
			}
		}
		
		
	}
	
	private enum Page
	{
		HOME,
		SHOP,
		MISSION,
		FIGHT,
		WIN,
		LOSE
	}
	
	
	public Hackers()
	{
		scnr = new Scanner(System.in);
		random = new Random();
		startGame();
	}
	
	public static void main(String[] args)
	{
		Hackers hack = new Hackers();
	}
	
	private void startGame()
	{
		System.out.printf("Welcome to THE HACKATHON!%n");
		
		System.out.printf("Enter your name.%n");
		reset(getInput());
		home();
	}
	
	private void home()
	{
		printGameState();
		printHome();
		
			switch(getInput().charAt(0))
			{
			case '1':
				arena.arenaIndex++;
				arena = new Arena(arena.state.valueOf(arena.arenaIndex));
				mission();
				break;
			case '2':
				shop();
				break;
			}
		
	}
	
	private void shop()
	{
		printGameState();
	}
	
	private void mission()
	{
		printGameState();
		printMission();
		printStory();
		printActions(Page.MISSION);
		
		try {
			switch(getInput().charAt(0))
			{
			case '1':
				fight();
				break;
			case '2':
				evade();
				break;
			case '3':
				run();
			}
		} catch(Exception e) {
			errorMessage = ("Command not understood. Try again.\n");
			mission();
		}
	}
	
	private void fight()
	{
		printGameState();
		printFight();
		printStory();
		printActions(Page.FIGHT);
		
		try {
			switch(getInput().charAt(0))
			{
			case '1':
				// Swing First
				boolean doDamage = player.doDamage(arena.getEnemy());
				if(!doDamage && arena.getEnemyIndex() >= arena.state.getOpponents())
					{
					arena.enemyIndex--;
					win();
					}
				else if (!doDamage) arena.enemyIndex++;
				// Swing back
				
				
				break;
			case '2':
				evade();
				break;
			case '3':
				run();
				break;
			}
		} catch(Exception e) {
			errorMessage = ("Command not understood. Try again.\n");
		}
		fight();
	}
	
	private void evade()
	{
		
	}
	
	private void run()
	{
		
	}
	
	private void win()
	{
		System.out.printf("%s%n%nYou beat %s!%n%nTry again?%n%n%n%n", printSpace(), arena.getEnemy().state.getName());
		// arena.enemyIndex = 0;
		arena.arenaIndex++;
		printActions(Page.WIN);
		try {
			switch(getInput().charAt(0))
			{
			case '1':
				home();
				break;
			case '2':
				evade();
				break;
			case '3':
				run();
			}
		} catch(Exception e) {
			errorMessage = ("Command not understood. Try again.\n");
			win();
		}
		
	}
	
	private void lose()
	{
		System.out.printf("%s%n%nYou lost :(%nTry again?%n%n%n%n", printSpace());
		printActions(Page.LOSE);
		try {
			switch(getInput().charAt(0))
			{
			case '1':
				fight();
				break;
			case '2':
				evade();
				break;
			case '3':
				run();
			}
		} catch(Exception e) {
			errorMessage = ("Command not understood. Try again.\n");
			win();
		}
	}
	
	private void reset(String playerName)
	{
		resetPlayer(playerName);
		resetGame();
	}
	
	private void resetPlayer(String playerName)
	{
		player = new Player(playerName);
		
	}
	
	private void resetGame()
	{
		arena = new Arena();
	}
	
	private String getInput()
	{
		System.out.printf("%sEnter Command: ", errorMessage);
		errorMessage = "";
		return scnr.nextLine();
	}
	
	private String getAction(Page page)
	{
		switch(page)
		{
		case HOME:
			return "(1) Start Next Arena\t(2) Shop";
		case MISSION:
			return "(1) Fight\t(2) Evade\t(3) Run for your life!";
		case FIGHT:
			return "(1) " + player.playerWeapon.getAttack() + "\t(2) Evade\t(3) Run";
		default:
			return "No actions allowed.";
		}
	}
	
	private void printActions(Page page)
	{
		System.out.printf("Actions\t\t%s%n", getAction(page));
	}
	
	private void printHome()
	{
		System.out.printf("Home\t\t%s%n%n%n%n%n%n%n%n", getAction(Page.HOME));
	}
	
	private void printStory()
	{
		System.out.printf("Story\t\t%s%n\t\tWhat do you do?%n%n%n%n", arena.getEnemy().state.getStory());
	}
	
	private void printMission()
	{
		System.out.printf("Mission\t\tYou Encounter a new Enemy!%n%n");
	}
	
	private void printFight()
	{
		System.out.printf("Challenge %d/%d\t%s%n%n", arena.getEnemyIndex(), arena.state.getOpponents(), arena.printEnemy());
	}
	
	private void printGameState()
	{
		System.out.printf("%sHackerS\t\t%s\tHealth: [%s]\tWeapon: %s\tArmor: %s\tArena: %s%n%n", printSpace(), player.getName(), player.printHealth(), player.playerWeapon.getName(), player.playerArmor.getName(), arena.state.getName());
	}
	
	private String printSpace()
	{
		return "\n\n\n\n\n\n\n\n\n\n\n\n";
	}
}
