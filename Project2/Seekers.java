import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Seekers extends Creatures{

    ArrayList<Characters> CharacterList; // Seekers get to know where characters are
    Seekers(int A,Dungeon map, ArrayList<Characters> CharacterList){
        this.dungeon = map;
        super.ID = A;
        setStartingRoom();
        name = "Seeker";
    }

    /**
     * Randomly generate starting room for orbiters from any exterior room on any level
     */
    protected void setStartingRoom() {
        //Seekers start anywhere in dungeon

        // Get new map of possible rooms
        Hashtable<String, Room> possible_room_map = new Hashtable<String, Room>();
        possible_room_map.putAll(dungeon.getMap());
        possible_room_map.remove("0-1-1"); // remove entrace room
                
        // Randomly select one of the rooms
        ArrayList<Room> starting_rooms = new ArrayList<Room>(possible_room_map.values());
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);
        this.setLocation(new_room);
    }

    /* (non-Javadoc)
     * @see Creatures#move()
     */
    @Override
    public void move(){
        Room current_room = this.getLocation();

        ArrayList<String>exits = current_room.getExits();

        // List of nearby rooms
        // Convert Room-Name-Strings to Rooms
        Dungeon dungeon = new Dungeon();
        ArrayList<Room> exit_rooms = new ArrayList<>();
        for (String x: exits) {
            Room exit_room = dungeon.getRoom(x);
            if (exit_room.getCharactersInRoom().size() > 0) {
                // If character in room add it to possible exit_rooms
                exit_rooms.add(exit_room);
            }
        }

        // Move based on interesections
        if (exit_rooms.size() == 0 ) {
            // If no intersection, don't move
            this.setLocation(this.getLocation());
        } else if (exit_rooms.size() == 1) {
            // If one intersection, move there
            Room new_room = exit_rooms.get(0);
            this.setLocation(new_room);
        } else {
            // If multiple intersections, choose one randomly
            Random random = new Random();
            int random_index = random.nextInt(exit_rooms.size());
        
            Room new_room = exit_rooms.get(random_index);
            this.setLocation(new_room);
        }
    }
}
