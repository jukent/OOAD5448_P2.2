public abstract class Creatures {
    public int ID = 0;

    // Location stored Room object accessed by name (level-row-column)
    // Level Range [0-4], Column range [1-3], Row Range [1-3]
    Dungeon dungeon;
    protected Room Location;

    String name = new String("Creature");
    Fight FightBehavior = new Fight("Creature");

    protected int HP = 1;
    protected int MoveCount = 1;


    /**
     *  This method displays a Creature's status.
     */
    public void showStatus() {}


    /**
     * @return int
     * 
     * This method calls a Creature's fight behavior 
     * and returns their "fight roll" as an integer.
     */
    public int fight() {
        return FightBehavior.fight();
    }


    /**
     * Template function for specific move directions
     * to be overwritten by more specific behavior.
     */
    public void move() {}


    /**
     * @param room: Room
     * 
     * Manually set Creature's location without influence from other Rooms
     */
    public void setLocation(Room room) {
        this.Location = room;
    }


    /**
     * @return Room
     * 
     * This method returns a Creature's location.
     */
    public Room getLocation() {
        return this.Location;
    }


    /**
     * @param n: int
     * 
     * This method decreases a Creature's health by the integer 'n'.
     */
    public void loseHealth(int n) {
        this.HP = this.HP - n;
    }


    /**
     * @return int
     * 
     * This method returns a Creature's health.
     */
    public int getHealth() {
        return this.HP;
    }


    /**
     * @return String
     * 
     * This method returns a Creature's name.
     */
    public String getName() {
        return this.name;
    }


    /**
     * The abstract method sets a Creature's starting room
     * to be overwritten with more specific Creature behavior.
     */
    protected void setStartingRoom() {}
}
