import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public abstract class Characters {
    public int ID = 0;

    // creatures are all aware of where characters are
    // so they can know if they're in the same room
    ArrayList<Creatures> CreatureList;

    //room location stored Room object accessed by name (level-row-column)
    //Level Range [0-4], Column range [1-3], Row Range [1-3]
    Dungeon dungeon;
    protected Room Location = dungeon.getRoom("0-1-1");

    protected int HP = 3;
    protected int TreasureCount = 0;
    protected int MoveCount = 1;

    public void showStatus(){
        System.out.print("Character: ");
        System.out.print(ID);
        System.out.print("Location: ");
        System.out.print(Location);
        System.out.print("Health: ");
        System.out.print(HP);
        System.out.print("Treasure: ");
        System.out.print(TreasureCount);
    }

    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }

    public int searchTreasure(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }

    //Template function for specific move directions
    public void move() {
        // Random movement
        Room current_room = this.getLocation();
        ArrayList<String>exits = current_room.getExits();

        Random random = new Random();
        int random_index = random.nextInt(exits.size());

        String new_room_name = exits.get(random_index);
        Room new_room = dungeon.getRoom(new_room_name);
        
        this.setLocation(new_room);
    }

    //Manually set characters location
    //No influence from other rooms
    //Manually set characters location
    //No influence from other rooms
    public void setLocation(Room room){
        this.Location = room;
    }

    //Manually set characters location
    //No influence from other rooms
    public Room getLocation(){
        return this.Location;
    }

    public void loseHealth(int n){
        HP = HP -n;
    }
    public int getHealth(){
        return HP;
    }
    public int getTreasure(){
        return TreasureCount;
    }
    public void gainTreasure(){
        this.TreasureCount++;
    }

    public ArrayList<Creatures> getCreaturesInRoom(Room room) {
        ArrayList<Creatures> creatures_in_room = new ArrayList<>();
        for (Creatures c:CreatureList) {
            Room creature_location = c.getLocation();
            if (creature_location == room) {
                creatures_in_room.add(c);
            } 
        }
        return creatures_in_room;  
    }
}



