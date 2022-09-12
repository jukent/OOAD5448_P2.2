Hello!

Notes;
Java Version: Java 18

Notes on Movement:
In the game engine process player turn, it will call
character.move() to move. Any limitations in move should
be set in the .move function either in the abstract class
or in any subclasses that specifically change movement. 