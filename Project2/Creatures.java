import java.util.ArrayList;

public abstract class Creatures {
    public int ID = 0;

    //room location stored Room object accessed by name (level-row-column)
    //Level Range [0-4], Column range [1-3], Row Range [1-3]
    Dungeon dungeon = new Dungeon();
    private Room Location = dungeon.getRoom("1-1-1");

    protected int HP = 1;
    protected int MoveCount = 1;

    public void showStatus(){}
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
    }
    //Template function for specific move directions
    public void move(){}

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

    // Help me rename this function to something better
    // Also using a dummy arraylist that should be populated with character locations from a method outside this class (with access to all characters)
    public boolean checkCharacterInRoom(Room room) {
        ArrayList<Room> character_locations = new ArrayList<Room>(); //characters.getCharacterLocations();
        if (character_locations.contains(room)) {
            return true;
        } else {
            return false;
        }
    }
}