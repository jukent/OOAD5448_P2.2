public class Brawlers extends Characters { 
    // Example of inheritance
    
    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Brawler constructor must be passed in an integer ID 'A' and the Dungeon.
     * Brawler is constructed with starting room.
     */
    Brawlers(int A, Dungeon map) {
        super.ID = A;
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new Fight("Brawler");
        name = "Brawler";
    }
}
