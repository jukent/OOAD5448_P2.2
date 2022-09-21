import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Seekers extends Creatures {
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Construct a Seeker with ID `A` and the Dungeon.
     */
    Seekers(int A, Dungeon map) {
        this.dungeon = map;
        super.ID = A;
        setStartingRoom();
        name = "Seeker";
    }


    /**
     * Randomly generate starting room for orbiters from any exterior room on any level
     */
    protected void setStartingRoom() {
        // Get new map of possible Rooms
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap()); // Learned method from Geeks for Geeks: "How to Copy Map Content to Another Hashtable in Java?(https://www.geeksforgeeks.org/how-to-copy-map-content-to-another-hashtable-in-java/)
        possible_room_map.remove("(0-1-1)"); // Remove entrace room
                
        // Randomly select one of the Rooms - learned from Stack Overflow question (https://stackoverflow.com/questions/38248381/pick-a-random-element-from-a-hashtable)
        ArrayList<Room> starting_rooms = new ArrayList<Room>(possible_room_map.values());
        
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);

        // Start there
        this.setLocation(new_room);
    }


    /* (non-Javadoc)
     * @see Creatures#move()
     * 
     * Seekers move by staying still and waiting for a Character to be in a nearby Room
     * Then they move to the room with the Character.
     */
    @Override
    public void move(){
        Room current_room = this.getLocation();

        // List of nearby rooms
        ArrayList<String>exits = current_room.getExits();
        
        // Populate an ArrayList of populated nearby rooms
        ArrayList<Room> populated_exits = new ArrayList<>();
        for (String x: exits) {
            // Convert Exit Room-Name Strings to Rooms 
            Room exit_room = dungeon.getRoom(x);
            // Check if a Character is in the Exit Room
            ArrayList<Characters> characters_in_room = exit_room.getCharactersInRoom();
            if (characters_in_room.size() > 0) {
                // If character in room add it to possible exit_rooms
                populated_exits.add(exit_room);
            }
        }

        // Move based on interesections
        if (populated_exits.size() == 0 ) {
            // If no intersection, don't move
            this.setLocation(this.getLocation());
        } else if (populated_exits.size() == 1) {
            // If one intersection, move there
            Room new_room = populated_exits.get(0);
            this.setLocation(new_room);
        } else {
            // If multiple intersections, choose one randomly
            Random random = new Random();
            int random_index = random.nextInt(populated_exits.size());
        
            Room new_room = populated_exits.get(random_index);
            this.setLocation(new_room);
        }
    }
}
