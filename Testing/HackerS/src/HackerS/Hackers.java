package HackerS;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
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
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		private int arenaIndex;
		private int enemyIndex;
		
		Arena()
		{
			this.state = ArenaState.NONE;
			generateArena();
			enemyIndex = 0;
			arenaIndex = 0;
		}
		Arena(ArenaState arena)
		{
			this.state = arena;
			generateArena();
		}
		
		private void generateArena()
		{
			for(int i = 0; i < state.getOpponents(); i++)
			{
				//for(int j = 0; j < )
				
				
				enemies.add(new Enemy(Enemy.EnemyInfo.valueOf(random.nextInt(Enemy.EnemyInfo.values().length)), Enemy.EnemyWeapon.valueOf(random.nextInt(Enemy.EnemyWeapon.values().length))));
				System.out.println("Info: " + enemies.get(i).numi + " Weapons: " + enemies.get(i).numw);
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
			return enemies.get(enemyIndex);
		}
		
		private String printEnemy()
		{
			return getEnemy().printEnemy();
		}
		
		private enum ArenaState
		{
			NONE(0, 0, 0, "No Arena Selected"),
			HACKATHON(1, 1000, 10, "Hackathon");
			
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
		
		private static int numw;
		private static int numi;
		
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
				numw = 0;
			}
			
			static {
				for(EnemyWeapon enemyWeapon : EnemyWeapon.values()) {
					map.put(enemyWeapon.value, enemyWeapon);
					numw++;
				}
			}
			
			public static EnemyWeapon valueOf(int key)
			{
				return (EnemyWeapon) map.get(key);
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
				numi = 0;
			}
			
			static {
				for(EnemyInfo enemyInfo : EnemyInfo.values()) {
					map.put(enemyInfo.value, enemyInfo);
					numi++;
				}
			}
			
			public static EnemyInfo valueOf(int key)
			{
				return (EnemyInfo) map.get(key);
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
		FIGHT
	}
	
	
	public Hackers()
	{
		scnr = new Scanner(System.in);
		random = new Random();
		startGame();
	}
	
	public static void main(String[] args)
	{
		/*
	     Game:
	     New Journey
	     Introduction
	     Setup - >
	        Show Health Bar
	        Show Name
	        Show Stage / Status
	     
	     Interaction - >
	        Ask for decisions and interpret to number or start by asking number
	        Roll random for what is going to happen from decision
	        Change decisions
	        
	     
	        
	     
	     GameEngine:
	        
	     */
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
		try {
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
		} catch(Exception e) {
			errorMessage = ("Command not understood. Try again.%n");
			home();
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
		printMission();
		printStory();
		printFight();
		printActions(Page.FIGHT);
		
		try {
			switch(getInput().charAt(0))
			{
			case '1':
				if(!player.doDamage(arena.getEnemy())) win();
				break;
			case '2':
				evade();
				break;
			case '3':
				run();
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
		System.out.printf("You beat %s!%nContinue:", arena.getEnemy().state.getName());
		arena.enemyIndex++;
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
		System.out.printf("Story\t\t%s%n\t\tWhat do you do?%n%n%n", arena.getEnemy().state.getStory());
	}
	
	private void printMission()
	{
		System.out.printf("Enemy %d/%d\t%s%n%n", arena.getEnemyIndex(), arena.enemies.size(), arena.printEnemy());
	}
	
	private void printFight()
	{
		
	}
	
	void printGameState()
	{
		System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%nHackerS\t\t%s\tHealth: [%s]\tWeapon: %s\tArmor: %s\tArena: %s%n%n", player.getName(), player.printHealth(), player.playerWeapon.getName(), player.playerArmor.getName(), arena.state.getName());
	}
}
