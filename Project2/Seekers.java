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
            //ArrayList<Characters> characters_in_room = getCharactersInRoom(exit_room);
            exit_rooms.add(exit_room);
        }

        // List of character locations
        //ArrayList<Room> character_locations = new ArrayList<Room>();
        //for (Characters c: CharacterList) {
        //    Room c_room = c.getLocation();
        //    character_locations.add(c_room);
        //}

        // Compare lists -- this could be a utility fx
        // Worried about the equality of objects here
        // Hoping that code will recognize identical Room objects 
        // but I'm not clear on if we are generating new Room objects with identical attributes anywhere
        // If so we can compare room string names `.equal()` as an easy fix
        ArrayList<Room> room_intersections = new ArrayList<>();
        for (Room r: exit_rooms) {
            if (character_locations.contains(r)) {
                room_intersections.add(r);
            }
        }

        // Move based on interesections
        if (room_intersections.size() == 0 ) {
            // If no intersection, don't move
            this.setLocation(this.getLocation());
        } else if (room_intersections.size() == 1) {
            // If one intersection, move there
            Room new_room = room_intersections.get(0);
            this.setLocation(new_room);
        } else {
            // If multiple intersections, choose one randomly
            Random random = new Random();
            int random_index = random.nextInt(room_intersections.size());
        
            Room new_room = room_intersections.get(random_index);
            this.setLocation(new_room);
        }
    }
}
