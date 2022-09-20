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

To run the game, execute `Main.java` within the project. The terminal will then prompt you to press 'Enter' to begin the game, and continuosly to begin the next turn.

## Results

Captured output for a single simulated game is in `SingleGameRun.txt` <br/>
Final summary captured output from 30 runs is in `MultipleGameRun.txt` <br/>

The results of the 30-game runs can be summarized as: <br/>
Characters won by finding 10 Treasures X times <br/>
Characters won by defeating all creatures X times <br/>
Characters lost by all being defeated X times <br/>

From these results we can see that the game needs further tuning. 

## Identified OO Elements

**Inheritance** 

**Polymorphism** 

**Cohesion** 

**Identity**

**Encapsulation**

**Abstraction**

## Changes to UML Diagram

The UML we developed during our planning phase went pretty far for setting up a successful game. We did not predict every "set" and "get" method that we would need to call, so that was a change. Also, we were unsure if Characters would need to store their location and if Rooms would need to store their occupancy. In the end we did because it helped with the Seekers movement pattern and with the dungeon rendering each turn, but we stored the Room occupancy as 2 ArrayLists, one of Characters and one of Creatures, as opposed to one main list of "Entities". There were several helper functions that we created for code readability, private methods only used within a method that was represented in the UML. This is prominent in the 'Printer' Class, where it felt cleanest and easiest to follow if we parsed getting the String for each room, printing a dungeon row, printing a dungeon level, and printing the entire dungeon into separate methods.

## Assumptions Made

We assumed that every Character could check for treasures in every Room multiple times, regardless of whether or not a treasure had been found there or not. We figured that sometimes we have to check the same location twice before we find our lost keys, and this seemed easier to code. However, based on the disproportionate amount of times Characters won by finding 10 treasures, this would be a good part of the code to improve. Perhaps we limit it to 10 treasures randomly distributed throughout the dungeon that the Characters can roll to find. This would make it more likely that the Characters have to venture to the 4th level of the dungeon to win and more likely that they encounter more of the Creatures (Seekers and Orbiters on the 4th level often see no action).

## Citations

Throughout the project we looked at many online resources for references, but none of the code was taken directly from a different source. Here are some of the more useful resources we used:
