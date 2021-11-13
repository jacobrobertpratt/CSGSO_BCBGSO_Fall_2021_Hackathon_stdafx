package HackerS;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Hackers {

	private static int HP = 10;
	private String errorMessage = "";
	private Random random;
	private Scanner scnr;
	private Player player;
	private Arena arena;
	
	private static class Arena
	{
		ArenaState state;
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		private int index;
		
		Arena()
		{
			this.state = ArenaState.HACKATHON;
			generateArena();
			index = 0;
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
				enemies.add(new Enemy());
			}
		}
		
		private int getIndex()
		{
			return index + 1;
		}
		
		private Enemy getEnemy()
		{
			return enemies.get(index);
		}
		
		private String printEnemy()
		{
			return getEnemy().printEnemy();
		}
		
		private enum ArenaState
		{
			HACKATHON(1000, 10, "Hackathon");
			
			private final int points;
			private final int opponents;
			private final String name;
			
			ArenaState(int points, int opponents, String name) {
				this.points = points;
				this.opponents = opponents;
				this.name = name;
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
		EnemyInfo name;
		EnemyWeapon weapon;
		private int health;
		
		public Enemy()
		{
			name = EnemyInfo.STAN;
			weapon = EnemyWeapon.TEXTENTRY;
			this.health = HP;
		}
		
		public Enemy(EnemyInfo name, EnemyWeapon weapon)
		{
			this.name = name;
			this.weapon = weapon;
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
			return (name.getName() + "\tHealth: [" + this.printHealth() + "]\tChallenge: " + this.weapon.getName());
		}
		
		
		private enum EnemyWeapon
		{
			TEXTENTRY(1, "Text Entry"),
			TERMINALAPP(2, "Terminal Application"),
			POPQUIZ(3, "Pop Quiz");
			
			private final int power;
			private final String name;
			
			EnemyWeapon(int power, String name) {
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
		
		private enum EnemyInfo
		{
			STAN(1, "Stan", "Stan is a grad student.  He was forced to teach a course.  He makes you create a text entry coding project.  No IDE allowed.");
			
			private final int value;
			private final String name;
			private final String story;
			
			EnemyInfo(int value, String name, String story){
				this.value = value;
				this.name = name;
				this.story = story;
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
			MOUSE(1, "Mouse"),
			KEYBOARD(2, "Keyboard"),
			TOUCHSCREEN(3, "Touch-Screen");
			
			private final int power;
			private final String name;
			
			PlayerWeapon(int power, String name) {
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
				mission();
				break;
			case '2':
				shop();
				break;
			}
		} catch(Error e) {
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
		} catch(Error e) {
			errorMessage = ("Command not understood. Try again.%n");
			mission();
		}
	}
	
	private void fight()
	{
		printGameState();
		printFight();
		System.out.printf("A", args)printActions(Page.FIGHT);
	}
	
	private void evade()
	{
		
	}
	
	private void run()
	{
		
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
			return "(1) Start Arena\t(2) Shop";
		case MISSION:
			return "(1) Fight\t(2) Evade\t(3) Run for your life!";
		case FIGHT:
			return "Uhh";
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
		System.out.printf("Story\t\t%s%n\t\tWhat do you do?%n%n%n%n", arena.getEnemy().name.getStory());
	}
	
	private void printMission()
	{
		System.out.printf("Enemy %d/%d\t%s%n%n", arena.getIndex(), arena.enemies.size(), arena.printEnemy());
	}
	
	private void printFight()
	{
		
	}
	
	void printGameState()
	{
		System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%nHackerS\t\t%s\tHealth: [%s]\tWeapon: %s\tArmor: %s\tArena: %s%n%n", player.getName(), player.printHealth(), player.playerWeapon.getName(), player.playerArmor.getName(), arena.state.getName());
	}
}
