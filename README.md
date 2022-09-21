# OOAD5448: Raiders of the Lost Arctangent
by David Chaparro and Julia Kent

Java version: 17.0.1

--------------------------------

This project is a Java text-based adventure game where 4 classes of characters (Brawlers, Sneakers, Runners, and Thieves) encounter 3 classes of Creatures (Blinkers, Orbiters, and Seekers) in a 4-level 3x3 dungeon. The game ends when either: the characters have collectively found 10 treasures (win), all the creatures are defeated (win), or all of the characters are defeated (lose). At this iteration all character decisions and movement patterns are randomized.

Characters:
- Brawlers: +2 Strength Buff
- Sneakers: 50% Chance of Evasion
- Runners: +1 Move
- Thieves: +1 Strength Buff,  +1 Treasure Hunting Buff

Creatures:
- Blinkers: Begin on 4th level, move by "blinking" to any random room.
- Orbiters: Begin on any level and "orbit" its outer rooms. Can move clockwise or counterclockwise.
- Seekers: Begin on any level and awaits a character in a nearby room, then "seek out" a battle by moving towards the character.

## How to Run

To run the game, execute `Main.java` within the project. The terminal will then prompt you to press 'Enter' to begin the game.

## Results

Captured output for a single simulated game is in `SingleGameRun.txt` <br/>
Final summary captured output from 30 runs is in `MultipleGameRun.txt` <br/>

The results of the 30-game runs can be summarized as: <br/>
Characters won by finding 10 Treasures 29 times. <br/>
Characters won by defeating all creatures 0 times. <br/>
Characters lost by all being defeated 1 time. <br/>

From these results we can see that the game needs further tuning. When there were less creatures, sometimes characters would defeat all creatures, but still, the game usually ended when all treasure was found.

## Identified OO Elements

**Inheritance** is demonstrated in this codebase with our use of the abstract classes `Characters` and `Creatures` and the classes that inherit from them: `Brawlers`, `Sneakers`, `Runners`, `Thieves` and `Blinkers`, `Orbiters`, `Seekers`, respectively. These concrete classes acquire attribute and method properties from their parent abstract class.

**Polymorphism** lets you treat objects as instances of abstract classes while still accessing specific class behavior. A clear example of this OO element is when we call the `populateEntities()` method from within the `GameEngine` Class. Here we are able to Characters to a `CharactureList` and Creatures to a `CreatureList` despite the "form" of each Character and Creature being different. This allows us to then run through these lists processing the entities' turn with their specific moving, fighting, and treasure hunting behavior without needeing to worry about the differences in their moving, fighting, or treasurehunting behaviors.

**Cohesion** is closely associated with making sure each class is designed with a single purpose. It refers to how closely related the elements of the code are, and highlights that the more related each element is the harder it is to maintain code as small changes have ripple effects throughout the codebase. This project's best example of cohesion is in the `DiceRolls` inteface. `DiceRolls` is only responsible for rolling dice. It doesn't worry about summing up two dice rolls or adding any buffs. This interface can be accessed from anywhere, and those buffs and combinations of dice rolls are handled by fighting and treasure hunting methods elsewhere.

**Identity** is what distinguishes different objects in memory. It is possible to have multiple objects with identical attributes but different identity. This was the challenge we had to address when having multiple Hashtables of Room objects. There is the main Dungeon, and then there are sub-maps of the Dungeon with valid Creature locations. One bug that we encountered in the code was accidentally generating unique Room objects in those sub-maps of the Dungeon. So while the Creatures had a location that shared a name with a real dungeon Room, they did not appear on the map or encounter any Characters because the identy of the two rooms was different. We addressed this by passing the Dungeon object around instead of creating a new Dungeon to call its methods.

**Encapsulation** refers to objects having attributes and methods binded into a single unit, and the techniques used to protect implementation details or other information from within a unit from other parts of the system. Encapsulation occurs during implementation. This is demonstrated in the `Fight` and `TreasureHunt` classes which hide the unique behavior associated with each Character or Creature. This is as in the Strategy design pattern "Encapsulate What Varies."

**Abstraction** allows parts of the system to not have to think about the whole picture by chosing what is relevant and should be exposed at each design level. Abstraction occurs during the design process. During our initial UML design, we planned abstraction in the use of Character and Creature abstract classes. Objects of these parent classes have their information grouped together, allowing the user to interact with each Character or Creature without knowing the full details of their unique methods. Abstraction is also demonstrated where we chose to make methods private, such as the `Dungeon.generateMap()` method. Users can still access the Dungeon map with `Dungeon.getMap()` but only this object can generate the map.

## Changes to UML Diagram

The UML we developed during our planning phase went pretty far for setting up a successful game. We did not predict every "set" and "get" method that we would need to call, so that was a change. Also, we were unsure if Characters would need to store their location and if Rooms would need to store their occupancy. In the end we did because it helped with the Seekers movement pattern and with the dungeon rendering each turn, but we stored the Room occupancy as 2 ArrayLists, one of Characters and one of Creatures, as opposed to one main list of "Entities". There were several helper functions that we created for code readability, private methods only used within a method that was represented in the UML. This is prominent in the 'Printer' Class, where it felt cleanest and easiest to follow if we parsed getting the String for each room, printing a dungeon row, printing a dungeon level, and printing the entire dungeon into separate methods.

## Assumptions Made

We assumed that every Character could check for treasures in every Room multiple times, regardless of whether or not a treasure had been found there or not. We figured that sometimes we have to check the same location twice before we find our lost keys, and this seemed easier to code. However, based on the disproportionate amount of times Characters won by finding 10 treasures, this would be a good part of the code to improve. Perhaps we limit it to 10 treasures randomly distributed throughout the dungeon that the Characters can roll to find. This would make it more likely that the Characters have to venture to the 4th level of the dungeon to win and more likely that they encounter more of the Creatures (Seekers and Orbiters on the 4th level often see no action).

## Citations

Throughout the project we looked at many online resources for references, but none of the code was taken directly from a different source. Here are some of the more useful resources we used:

We learned about the `putAll()` method, necessary for creating the sub-maps of the Dungeon used for setting the Creature starting locations and movement from [Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?](https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/).

This Youtube series, while having different requirements from our project, was integral to feeling more confident about making a text-based adventure game - [Code with Huw: "Program a Text Adventure Game in Java](https://www.youtube.com/playlist?list=PLZHx5heVfgEvT5BD8TgLmGrr-V64pX7MD). No code from this series was actually implemented though.

Instructions on how to get a random element from a Hashtable (by converting either values or keys to an ArrayList first) came from this [Stack Overflow question](https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable). This is used for most Character and Creature movement behaviors and Creature starting locations.
