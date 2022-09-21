import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Blinkers extends Creatures { 
    // Example of inheritance
    
    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Blinkers constructor must be passes an ID integer 'A' and the Dungeon
     * Blinkers are assigned a starting room at construction.
     */
    Blinkers(int A, Dungeon map) {
        super.ID = A;
        this.dungeon = map;
        name = "Blinker";

        //Blinkers start anywhere on the 4th level
        setStartingRoom();
    }


    /* (non-Javadoc)
     * @see Creatures#move()
     * 
     * Replace abstract creature movement with Blinker movement
     * Checks if a character is in the room, if so stays
     * If not, blinks to another random room in the dungeon
     */
    @Override
    public void move(){
        Room current_room = this.getLocation();

        // Get a new hashtable to store possible rooms called 'possible_room_map'
        // Hashtable values point to a subset of the Room objects in the main dungeon map
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possible_room_map.remove("0-1-1"); // Remove entrace room
        possible_room_map.remove(current_room.getName()); // Remove current room

        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        // Convert values to an ArrayList of Room so that Rooms can grabbed via index
        ArrayList<Room> possible_rooms = new ArrayList<Room>(possible_room_map.values());

        // Get random index from 0 (inclusive) to the length of possible rooms (exclusive)
        Random random = new Random();
        int random_index = random.nextInt(possible_rooms.size());

        // Grab a Room from the random index
        Room new_room = possible_rooms.get(random_index);
            
        // Move there
        this.setLocation(new_room);
    }


    /* (non-Javadoc)
     * @see Creatures#setStartingRoom()
     * 
     * Randomly generate starting room for blinker from any room on 4th level
     */
    @Override
    protected void setStartingRoom() {

        // Generate an ArrayList of possible starting rooms
        // Generating a fresh list in a for-looped seemed simpler than removing every room not on the 4th level 
        // as done in the `move()` method
        // ArrayList values point to identical Room objects as in the main dungeon map
        ArrayList<Room> starting_rooms = new ArrayList<Room>();

        for (int r = 0; r < 2; ++r) { // row
            for (int c = 0; c < 2; ++c) { // column
                String room_name = new String("(4-" + r + "-" + c + ")"); 
                starting_rooms.add(dungeon.getRoom(room_name));   
            }
        }
                
        // Randomly select one of the rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);

        // Start there
        this.setLocation(new_room);
    }
}
