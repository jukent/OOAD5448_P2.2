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
        System.out.println(current_room.getName()); // DEBUG

        ArrayList<String>exits = current_room.getExits();
        System.out.println(exits); // DEBUG

        // List of nearby rooms
        // Convert Room-Name-Strings to Rooms
        Dungeon dungeon = new Dungeon();
        ArrayList<Room> populated_exits = new ArrayList<>();
        for (String x: exits) {
            Room exit_room = dungeon.getRoom(x);
            ArrayList<Characters> characters_in_room = exit_room.getCharactersInRoom();
            System.out.println(exit_room.getName()); // DEBUG
            System.out.println(characters_in_room); // DEBUG
            if (characters_in_room.size() > 0) {
                // If character in room add it to possible exit_rooms
                populated_exits.add(exit_room);
            }
        }

        // Move based on interesections
        if (populated_exits.size() == 0 ) {
            // If no intersection, don't move
            System.out.println("No nearby characters, not moving");
            this.setLocation(this.getLocation());
        } else if (populated_exits.size() == 1) {
            // If one intersection, move there
            System.out.println("One nearby characters moving");
            Room new_room = populated_exits.get(0);
            this.setLocation(new_room);
        } else {
            // If multiple intersections, choose one randomly
            System.out.println("Many nearby characters, moving");
            Random random = new Random();
            int random_index = random.nextInt(populated_exits.size());
        
            Room new_room = populated_exits.get(random_index);
            this.setLocation(new_room);
        }

        System.out.println(this.getLocation().getName());
    }
}
