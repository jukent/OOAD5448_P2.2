import java.util.ArrayList;
import java.util.Scanner;

public class Printer {

    Dungeon dungeon;
    String OutputType; // OneScreen,ShowAll,ShowEnding
    private Scanner A = new java.util.Scanner(System.in);

    /**
     * @param dungeon: Dungeon
     * @param Output: String
     * 
     * Construct the printer
     */
    Printer(Dungeon dungeon,String Output) {
        this.dungeon = dungeon;
        this.OutputType = Output;
    }


    /**
     * Print the Dungeon and its occupancy.
     */
    public void printDungeon() {
        // Level 0 
        System.out.println("Level 0");
        Room starting_room = dungeon.getRoom("(0-1-1)");
        String occupancy_string = getOccupancyString(starting_room);
        System.out.println(occupancy_string);

        // Levels 1, 2, 3, 4
        for (int l = 1; l <= 4; ++l) {
            printLevel(l);
        }
    }


    /**
     * A pause method between turns asking the player to continue.
     */
    public void pause(){
        if (OutputType == "OneScreen") {
            System.out.println("Press Enter To Continue...");
            A.nextLine();
        }
    }


    /**
     * @param room: Room
     * @return String
     * 
     * This method gets the string for displaying occupancy in each Room.
     */
    private String getOccupancyString(Room room) {
        // Characters in Room
        ArrayList<Characters> characters_in_room = room.getCharactersInRoom();
        String char_string = new String();
        for (Characters c:characters_in_room) {
            char_string += c.getName();
            char_string += " ";
        }

        // Creatures in Room
        ArrayList<Creatures> creatures_in_room = room.getCreaturesInRoom();
        String creature_string = new String();
        for (Creatures c:creatures_in_room) {
            creature_string += c.getName();
            creature_string += " ";
        }

        // Full Room Occupancy String
        String occupancy_string = new String(room.getName() + ": " + char_string + " : " + creature_string);
        return occupancy_string;
    }


    /**
     * @param level: int
     * @param row:: int
     * 
     * This method prints a row of the Dungeon and its occupancy.
     */
    private void printRowString (Integer level, Integer row) {
        ArrayList<Room> row_rooms = new ArrayList<Room>();
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-0)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-1)"));
        row_rooms.add(dungeon.getRoom("(" + level + "-" + row + "-2)"));
        String row_string = new String();
        for (Room r:row_rooms) {
            row_string += getOccupancyString(r);
            row_string += "    ";
        }
        System.out.println(row_string);
    }


    /**
     * @param level: int
     * 
     * This method prints a level of the Dungeon and its occupancy.
     */
    private void printLevel (Integer level) {
        System.out.println("Level " + level);
        for (int r = 0; r <= 2; ++r) {
            printRowString(level, r);
        }
    }
}
