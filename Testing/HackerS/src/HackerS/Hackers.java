package HackerS;

import java.util.Scanner;

public class Hackers {

	
	Scanner scnr;
	String playerName;
	PlayerWeapon playerWeapon;
	EnemyWeapon enemyWeapon;
	
	static int HP = 10;
	int playerHealth;
	
	
	enum PlayerWeapon
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
	
	enum EnemyWeapon
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
	
	enum PlayerMap
	{
		HACKATHON(1000, 10, "Hackathon");
		
		private final int points;
		private final int opponents;
		private final String name;
		
		PlayerMap(int points, int opponents, String name) {
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
		playerName = scnr.nextLine(); 
		
		System.out.println(PlayerWeapon.TOUCHSCREEN.getName());
		
	}
	
	private void home()
	{
		
	}
	
	String getHealth()
	{
		String h = "";
		
		int[] t = new int[HP];
		
		for(int i = 0; i < playerHealth; i++) {
			h += "*";
		}
		for(int i = playerHealth; i < HP; i++) {
			h += "-";
		}
		
		return h;
	}
	
	void printGameState()
	{
		System.out.printf("HackerS%t%s%tHealth: [%s]%tWeapon: %s%n%n", playerName, getHealth(), playerWeapon.getName());
	}
}
