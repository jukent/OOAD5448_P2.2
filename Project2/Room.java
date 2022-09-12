import java.util.Hashtable;

public class Room {

    private int level, row, column; // the level, row, and column coordinates for this room - Integers
    private String name; // the name  of this room, i.e. "(2-0-0)" - String
    private Hashtable<String, String> exits; // valid exit directions from this room mapped to neighboring room names in a Hashtable
                                            // i.e. ["south": "(2-1-0)"", "east": "(2-0-1)"] 

    /**
     * @param Level: Integer
     * @param Row: Integer
     * @param Column: Integer
     * 
     * Constructs the room object based on its level, row, and column coordinates.
     * Rooms have level, row, and column coordinates, 
     * as well as a name generated from these coordinates,
     * and a mapping of valid standard exit directions and room numbers 
     * (blinkers and abnormal movement pattern excepted)
     */
    public Room(int level, int row, int column) {
        // constructor
        this.level = level;
        this.row = row;
        this.column = column;
        
        this.name = new String("(" + level + "-" + row + "-" + column + ")");

        Hashtable<String, String> exits = findExits();
        this.exits = exits;
    }


    /**
     * @return exit: Hashtable<String, String> (cardinal direction, neighboring room name)
     * 
     * This method finds the valid standard exits for each rooom based on its 
     * level, row, and column coordinates.
     * 
     * At first I thought about having a separate Exit class (though that name is kind of confusing -- exiting the game?)
     * to add the doorways and stairwells to the map. But it doesn't seem necessary.
     * 
     * Since, we already know all valid rooom names,
     * we can generate exit connections when each rooom is generated
     * without needing to wait for an object containing all of the rooms to exist.
     */
    private Hashtable<String, String> findExits() {
        Hashtable<String, String> exits = new Hashtable<String, String>();

        if (this.level == 0) {
            // If in starting room (0-1-1), only stairs down
            String neighboring_room = new String("(0-1-1)");
            exits.put("down", neighboring_room);
        } else {
            // If already in the dungeon . . .
            if (this.row != 0) {
                // If not in north-most row of level, door to north
                Integer new_row = this.row - 1;
                String neighboring_room = new String("(" + this.level + "-" + new_row + "-" + this.column + ")");
                exits.put("north", neighboring_room);
            }
            if (this.row != 2) {
                // If not in south-most row of level, door to south
                Integer new_row = this.row + 1;
                String neighboring_room = new String("(" + this.level + "-" + new_row + "-" + this.column + ")");
                exits.put("south", neighboring_room);
            }
            if (this.column != 0) {
                // If not in west-most row of level, door to west
                Integer new_column = this.column - 1;
                String neighboring_room = new String("(" + this.level + "-" + this.row + "-" + new_column + ")");
                exits.put("west", neighboring_room);
            }
            if (this.column != 2) {
                // If not in east-most row of level, door to east
                Integer new_column = this.column + 1;
                String neighboring_room = new String("(" + this.level + "-" + this.row + "-" + new_column + ")");
                exits.put("east", neighboring_room);
            }
            if (this.column == 1 && this.row == 1) {
                // If in center room of level, there is a staircase up and or down
                if (this.level != 1) {
                    // Cannot exit back to starting room from level 1
                    // If not on level 1, stairs up
                    Integer new_level= this.level - 1;
                    String neighboring_room = new String("(" + new_level + "-" + this.row + "-" + this.column + ")");
                    exits.put("up", neighboring_room);
                }
                if (this.level != 4) { 
                    // If not on bottom level, stairs down
                    // Currently bottom level is hard coded to be 4, hoping to change this
                    Integer new_level = this.level + 1;
                    String neighboring_room = new String("(" + new_level + "-" + this.row + "-" + this.column + ")");
                    exits.put("down", neighboring_room);
                }
            }
        }
        return exits;
    }


    /**
     * @return name: String
     * 
     * Let other classes grab a room's name
     */
    public String getName() {
        return name;
    }


    /**
     * @return exits: Hashtable<String, String> (cardinal directions, neighboring room names)
     * 
     * Let other classess access a room's neighboring rooms (and in what direction if we need that)
     */
    public Hashtable<String, String> getExits() {
        return exits;
    }

    
    /**
     * @return level: Integer
     * 
     * Let other classess easily access a room's level
     * Might be needed for creatures whose movement may be limited to one level.
     */
    public Integer getLevel() {
        return level;
    }
}
