public abstract class Creatures {
    public int ID = 0;

    //room location stored Room object accessed by name (level-row-column)
    //Level Range [0-4], Column range [1-3], Row Range [1-3]
    Dungeon dungeon;
    private Room Location = dungeon.getRoom("1-1-1");

    String name = new String("Creature");

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

    public String getName() {
        return name;
    }
}