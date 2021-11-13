package HackerS;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;

public class Hackers {

	private static int HP = 10;
	Scanner scnr;
	Player player;
	Arena arena;
	
	private static class Arena
	{
		ArenaState state;
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		Arena()
		{
			this.state = ArenaState.HACKATHON;
			generateArena();
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
		EnemyName name;
		EnemyWeapon weapon;
		private int health;
		
		public Enemy()
		{
			name = EnemyName.STAN;
			weapon = EnemyWeapon.TEXTENTRY;
			this.health = HP;
		}
		
		public Enemy(EnemyName name, EnemyWeapon weapon)
		{
			this.name = name;
			this.weapon = weapon;
		}
		
		public int getHealth()
		{
			return health;
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
		
		private enum EnemyName
		{
			STAN(1, "Stan");
			
			private final int value;
			private final String name;
			EnemyName(int value, String name){
				this.value = value;
				this.name = name;
			}
			
			public int getValue()
			{
				return value;
			}
			
			public String getName()
			{
				return name;
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
		
		System.out.printf("Enter your name: ");
		reset(scnr.nextLine());
		home();
	}
	
	private void home()
	{
		printGameState();
		printHome();
	}
	
	private void printHome()
	{
		System.out.printf("Home");
	}
	
	private void mission()
	{
		printGameState();
	}
	
	private void fight()
	{
		printGameState();
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
	
	
	void printGameState()
	{
		player.health -= 3;
		System.out.printf("HackerS\t%s\tHealth: [%s]\tWeapon: %s\tArmor: %s\tArena: %s%n%n", player.getName(), player.printHealth(), player.playerWeapon.getName(), player.playerArmor.getName(), arena.state.getName());
	}
}
