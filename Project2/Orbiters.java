import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Orbiters extends Creatures {
    // Example of inheritance

    String direction = "clockwise"; // Need to add a default

    Orbiters(int A, Dungeon map) {
        super.ID = A;
        this.dungeon = map;
        name = "Orbiter";
        setStartingRoom(); // Set starting room
        this.direction = setDirection(); // Clockwise or Counterclockwise
    }

    /* (non-Javadoc)
     * @see Creatures#move()
     * 
     * Replace abstract Creature movement with Orbiter movement
     * Checks if a Character is in the room, if so stays
     * If not, moves (clockwise or counterclockwise)
     */
    @Override
    public void move(){
        Room current_room = this.getLocation();

        Integer level = current_room.getLevel();
        Integer row = current_room.getRow();
        Integer column = current_room.getColumn();

        if (direction.equals("clockwise")) {
            // Orbit Clockwise
            if (row == 0 && column == 0) {
                String next_room_name = new String("(" + level + "-0-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 0 && column == 1) {
                String next_room_name = new String("(" + level + "-0-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 0 && column == 2) {
                String next_room_name = new String("(" + level + "-1-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 1 && column == 2) {
                String next_room_name = new String("(" + level + "-2-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 2) {
                String next_room_name = new String("(" + level + "-2-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 1) {
                String next_room_name = new String("(" + level + "-2-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 1 && column == 0) {
                String next_room_name = new String("(" + level + "-0-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            }
        } else {
            // Orbit Counterclockwise;
            if (row == 0 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 1 && column == 0) {
                String next_room_name = new String("(" + level + "-2-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-2-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 1) {
                String next_room_name = new String("(" + level + "-2-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 2 && column == 2) {
                String next_room_name = new String("(" + level + "-1-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 1 && column == 2) {
                String next_room_name = new String("(" + level + "-0-2)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 0 && column == 2) {
                String next_room_name = new String("(" + level + "-0-1)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            } else if (row == 0 && column == 1) {
                String next_room_name = new String("(" + level + "-0-0)");
                Room new_room = dungeon.getRoom(next_room_name);
                this.setLocation(new_room);
            }
        }
    }


    /**
     * Randomly generate starting room for orbiters from any exterior room on any level
     */
    protected void setStartingRoom() {
        // Get map of possible rooms pointing to identical Room objects as in main dungeon.
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possible_room_map.remove("(0-1-1)"); // remove entrace room
        possible_room_map.remove("(1-1-1)"); // remove 1st floor center room
        possible_room_map.remove("(2-1-1)"); // remove 2nd floor center room
        possible_room_map.remove("(3-1-1)"); // remove 3rd floor center room
        possible_room_map.remove("(4-1-1)"); // remove 4th floor center room
                
        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        ArrayList<Room> starting_rooms = new ArrayList<Room>(possible_room_map.values());
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);

        // Start there
        this.setLocation(new_room);
    }

    /**
     * @return String
     * 
     * Randomly return "clockwise" or "counterclockwise" to set Orbiter direction
     */
    private String setDirection() {
        ArrayList<String> directions = new ArrayList<String>();
        directions.add("clockwise");
        directions.add("counterclockwise");

        Random random = new Random();
        int random_index = random.nextInt(2);

        String direction = directions.get(random_index);
        return direction;
    }
}
