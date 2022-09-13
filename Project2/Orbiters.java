import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Orbiters extends Creatures{

    String direction = "clockwise"; // Need to add a default

    Orbiters(int A){
        super.ID = A;
        setStartingRoom(); // Set starting room
        this.direction = setDirection(); // Clockwise or Counterclockwise
    }

    /**
     * Randomly generate starting room for orbiters from any exterior room on any level
     */
    private void setStartingRoom() {
        //Blinkers start anywhere on the 4th level

        // Get map of possible rooms
        Dungeon dungeon = new Dungeon();
        Hashtable<String, Room> possible_room_map = dungeon.getMap();
        possible_room_map.remove("0-1-1"); // remove entrace room
        possible_room_map.remove("1-1-1"); // remove 1st floor center room
        possible_room_map.remove("2-1-1"); // remove 2nd floor center room
        possible_room_map.remove("3-1-1"); // remove 3rd floor center room
        possible_room_map.remove("4-1-1"); // remove 4th floor center room
                
        // Randomly select one of the rooms
        ArrayList<Room> starting_rooms = new ArrayList<Room>(possible_room_map.values());
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);
        this.setLocation(new_room);
    }

    // Can move clockwise or counterclockwise
    private String setDirection() {
        ArrayList<String> directions = new ArrayList<String>();
        directions.add("clockwise");
        directions.add("counterclockwise");

        Random random = new Random();
        int random_index = random.nextInt(2);

        String direction = directions.get(random_index);
        return direction;
    }

    /* (non-Javadoc)
     * @see Creatures#move()
     * 
     * Replace abstract creature movement with Orbiter movement
     * Checks if a character is in the room, if so stays
     * If not, moves (clockwise or counterclockwise)
     * 
     * This one is trickier
     */
    @Override
    public void move(){
        Room current_room = this.getLocation();
        Integer level = current_room.getLevel();
        Integer row = current_room.getRow();
        Integer column = current_room.getColumn();

        Dungeon dungeon = new Dungeon();

        Room new_room = this.getLocation(); // Initialize a room
        ArrayList<Characters> characters_in_room = getCharactersInRoom(current_room);
        if (characters_in_room.size() > 0) {
            // Only move if character not in room
            new_room = new_room; // No effect
        } else if (direction.equals("clockwise")) {
            // Orbit Clockwise
            // I can't think of a better way to do this than manually.
            if (row == 0 && column == 0) {
                String next_room_name = new String("(" + level + "-0-1)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 0 && column == 1) {
                String next_room_name = new String("(" + level + "-0-2)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 0 && column == 2) {
                String next_room_name = new String("(" + level + "-1-2)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 1 && column == 2) {
                String next_room_name = new String("(" + level + "-2-2)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 2) {
                String next_room_name = new String("(" + level + "-2-1)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 1) {
                String next_room_name = new String("(" + level + "-2-0)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 1 && column == 0) {
                String next_room_name = new String("(" + level + "-0-0)");
                new_room = dungeon.getRoom(next_room_name);
            }
        } else {
            // Orbit Counterclockwise;
            if (row == 0 && column == 0) {
                String next_room_name = new String("(" + level + "-1-0)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 1 && column == 0) {
                String next_room_name = new String("(" + level + "-2-0)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 0) {
                String next_room_name = new String("(" + level + "-2-1)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 1) {
                String next_room_name = new String("(" + level + "-2-2)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 2 && column == 2) {
                String next_room_name = new String("(" + level + "-1-2)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 1 && column == 2) {
                String next_room_name = new String("(" + level + "-0-2)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 0 && column == 2) {
                String next_room_name = new String("(" + level + "-0-1)");
                new_room = dungeon.getRoom(next_room_name);
            } else if (row == 0 && column == 1) {
                String next_room_name = new String("(" + level + "-0-0)");
                new_room = dungeon.getRoom(next_room_name);
            }
        }
        this.setLocation(new_room);
    }
}
