# CSGSO_BCBGSO_Fall_2021_Hackathon_stdafx

### HackerS
_**An RPG where you are immersed as a hacker**_
_into a hackathon and must create the best project._
_Failing to make the best project will result in your peril!_

#### Categories
* Single Player
* Text-Based
* Funny

## Testing
**Project Details** This is a Hackathon Simulation RPG Game.  It uses an algorithm to generate [Arenas]() from a **`Collection`** of Custom Enemies.  This **`Collection`** is completely expandable such that:
```
// Number of Enemies               (N)
// Number of Enemy Tasks (Weapons) (W)
// Time (ms)                       (T)
   1 ≤ N ≤ 1,000,000,000
   1 ≤ W ≤ 1,000,000,000
   0 ≤ T ≤ 5,000
```

### Algorithms

**generateArena()**
_Create a **`Collection`** of type `<Enemy>`.  These are randomly assigned names and weapons from the catalog of names and weapons provided.  The enemies are then sorted from least powerful to most powerful for a sequential flow during battles._
```java
// Create Enemies with Random Values
for(int i = 0; i < state.getOpponents(); i++) {
	enemies[i] = (new Enemy(Enemy.EnemyInfo.valueOf(random.nextInt(Enemy.EnemyInfo.values().length)),  Enemy.EnemyWeapon.valueOf(random.nextInt(Enemy.EnemyWeapon.values().length))));
}
//Sort Enemies by their Weapon Power Level
for (int i = 0; i < state.getOpponents() -1; i++){
   for (int j = 0; j < state.getOpponents() -1; j++){
      if (enemies[j].weapon.getValue() > enemies[j+1].weapon.getValue()){
         Enemy temp = enemies[j];
		   enemies[j] = enemies[j + 1];
		   enemies[j + 1] = temp;
      }
   }    
}
```
**Actions**
```java
// Each page uses an action sequence
switch(getInput()){
   case (1-4):
      action();
}
```
### Data

**Storage and Selection**
_Hashmapping O(1)_
```C
enum {
   HashMap<Index, ENUM>
```

**Arena**
_Contains Enemies and ArenaState_
```java
// Expandable ArenaState Type
// Stores an index, total possible points, total opponents, and a description
enum ArenaState {
	TITLE(int index, int points, int opponents, String title),
	HACKATHON(1, 1000, 2, "Hackathon"),
```

**Player**
_Contains Player Data of PlayerWeapon and PlayerArmor type_
```java
PlayerWeapon playerWeapon;
PlayerArmor playerArmor;
int health;
int score;
```
```java
enum PlayerWeapon {
   TITLE(int index, String name, String description),
   MOUSE(1, "Mouse", "Click"),
```

**Specifications**
* Algorithm for generating arenas
* Interactive Game
* Ability to Adapt
