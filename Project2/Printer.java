import java.util.ArrayList;

public class Printer {

    Dungeon dungeon;

    Printer(Dungeon dungeon) {
        this.dungeon = dungeon;
    }


    private String getOccupancyString(Room room){
        ArrayList<Characters> characters_in_room = room.getCharactersInRoom();
        String char_string = new String();
        for (Characters c:characters_in_room) {
            char_string += c.getName();
            char_string += " ";
        }

        ArrayList<Creatures> creatures_in_room = room.getCreaturesInRoom();
        String creature_string = new String();
        for (Creatures c:creatures_in_room) {
            creature_string += c.getName();
            creature_string += " ";
        }
        String occupancy_string = new String(room.getName() + ": " + char_string + " : " + creature_string);
        return occupancy_string;
    }


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


    private void printLevel (Integer level) {
        System.out.println("Level " + level);
        for (int r = 0; r <= 2; ++r) {
            printRowString(level, r);
        }
    }

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
}