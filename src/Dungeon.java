package src;

import java.util.Hashtable;

public class Dungeon {
    private Hashtable<String, Room> map; //the map of this dungeon - Hashtable of room names mapped to Rooms
    

    /**
     * Constructs the Dungeon object which contains a map of all rooms
     * 
     * Possibly set num_levels to an input parameter so that this can be more easily extended
     */
    public Dungeon() {
        // constructor
        int num_levels = 4;

        this.map = generateMap(num_levels);
    }

        
    /**
     * @param num_levels: Integer 
     * @return map: Hashtable<String, Room> (room names, room object)
     * 
     * Generates the Rooms for the dungeon for any number of levels ('num_levels'),
     * with 3 rooms per level after the first level (which has only one room).
     * 
     * The Rooms are stored in a Hashtable map with room names as keys pointing to Room objects as values.
     */
    private Hashtable<String, Room> generateMap(int num_levels) {
        this.map = new Hashtable<String, Room>();

        //int num_levels = 4;

        for (int l = 0; l < num_levels; ++l) {     
            if (l == 0) {
                // On level 0, there is only one room
                Room room = new Room(l, 1, 1);
                map.put(room.getName(), room);
            } else {
                // On all other levels, there are 3x3 rooms
                for (int r = 0; r < 2; ++r) { // row
                    for (int c = 0; c < 2; ++c) { // column
                        Room room = new Room(l, r, c);       
                        map.put(room.getName(), room);        
                    }
                }
            }
        }
        return map;
    }


    /**
     * @return map: Hashtable<String, Room> (room name, Room)
     * 
     * Expose the map to other Classes
     */
    public Hashtable<String, Room> getMap() {
        return map;
    }


    /**
     * @param room_name: String
     * @return room: Room
     * 
     * Let other classes get a Room object based on its name.
     */
    public Room getRoom(String room_name) {
        Room room = this.map.get(room_name);
        return room;
    }
}
