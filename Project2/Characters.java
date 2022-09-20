import java.util.ArrayList;
import java.util.Random;

public abstract class Characters {
    public int ID = 0;
    // Example of abstraction - Characters all have their information grouped together
    // This was planned in the initial UML design, and was made abstract
    // because it allows a user to interact with characters without
    // knowing the full details.


    // Room location stored Room object accessed by name (level-row-column)
    // Level Range [0-4], Column range [1-3], Row Range [1-3]
    Dungeon dungeon;
    protected Room Location;

    String name = new String("Character");
    TreasureHunt HuntBehavior = new TreasureHunt("Character");
    Fight FightBehavior = new Fight("Character");

    protected int HP = 3;
    protected int TreasureCount = 0;
    protected int MoveCount = 1;


    /**
     * This method prints the Character status: ID, location, HP, and treasure.
     */
    public void showStatus() {
        System.out.print("Character: ");
        System.out.print(ID);
        System.out.print("Location: ");
        System.out.print(Location);
        System.out.print("Health: ");
        System.out.print(HP);
        System.out.print("Treasure: ");
        System.out.print(TreasureCount);
    }


    /**
     * @return int
     * 
     * This abstract method calls a Character's fight behavior 
     * and returns their "fight roll" as an integer.
     */
    public int fight() {
        return FightBehavior.fight();
    }


    /**
     * @return int
     * 
     * This abstract method calls a Character's treasure hunting behavior
     * and returns their "treasure roll" as an integer.
     */
    public int searchTreasure() {
        return HuntBehavior.searchTreasure();
    }

    
    /**
     * The template method controlls Character random movement.
     */
    public void move() {
        // Find ArrayList of current room's exits
        Room current_room = this.getLocation();
        ArrayList<String>exits = current_room.getExits();

        // Select one of the exit rooms by random number generation
        Random random = new Random();
        int random_index = random.nextInt(exits.size());

        // Find the room associated with the random index
        String new_room_name = exits.get(random_index);
        Room new_room = dungeon.getRoom(new_room_name);
    
        // Move there
        this.setLocation(new_room);
    }


    /**
     * @param room: Room
     * 
     * This method manually sets Characters location without influence from other Rooms.
     */
    public void setLocation(Room room) {
        this.Location = room;
    }


    /**
     * @return Room
     * 
     * This method returns a Characters location.
     */
    public Room getLocation() {
        return this.Location;
    }


    /**
     * @param n: int
     * 
     * This method decreases a Character's health by the integer 'n'.
     */
    public void loseHealth(int n) {
        this.HP = this.HP - n;
    }


    /**
     * @return int
     * 
     * This method returns a Character's health.
     */
    public int getHealth() {
        return this.HP;
    }


    /**
     * @return int
     * 
     * This method returns how much treasure a Character has found.
     */
    public int getTreasure() {
        return this.TreasureCount;
    }


    /**
     * This method increases how much treasure a Character has found by one.
     */
    public void gainTreasure() {
        this.TreasureCount++;
    }


    /**
     * @return String
     * 
     * This method returns a Character's name.
     */
    public String getName() {
        return this.name;
    }
}
