import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Blinkers extends Creatures{
    Blinkers(int A){
        super.ID = A;

    //Blinkers start anywhere on the 4th level
    getStartingRoom();
    }

    private void getStartingRoom() {
        //Blinkers start anywhere on the 4th level

        // Get list of possible starting rooms
        ArrayList<Room> starting_rooms = new ArrayList<Room>();

        for (int r = 0; r < 2; ++r) { // row
            for (int c = 0; c < 2; ++c) { // column
                Room room = new Room(4, r, c);   
                starting_rooms.add(room);   
            }
        }
                
        // Randomly select one of the rooms
        Random random = new Random();
        int random_index = random.nextInt(starting_rooms.size());

        Room new_room = starting_rooms.get(random_index);
        this.setLocation(new_room);
    }


    // Only move if character not in room
    public void move(){
        Room current_room = this.getLocation();

        boolean check = checkCharacterInRoom(current_room);
        if (check == true) {
            this.setLocation(this.getLocation());
        } else {
            // Get map of possible rooms
            Dungeon dungeon = new Dungeon();
            Hashtable<String, Room> possible_room_map = dungeon.getMap();
            possible_room_map.remove("0-1-1"); // remove entrace room
            possible_room_map.remove(current_room.getName()); // remove current room

            // Randomly select one of the rooms
            ArrayList<Room> possible_rooms = new ArrayList<Room>(possible_room_map.values());

            Random random = new Random();
            int random_index = random.nextInt(possible_rooms.size());

            Room new_room = possible_rooms.get(random_index);
            
            // Move there
            this.setLocation(new_room);
        }
    }
}