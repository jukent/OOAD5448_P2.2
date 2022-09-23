public class Runners extends Characters { 
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Constructs a runner with ID `A` and the Dungeon.
     */
    Runners (int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        super.ID = A;
        super.MoveCount = 2;
        this.FightBehavior = new Fight("Untrained");
        this.HuntBehavior = new TreasureHunt("Quick");
        name = "Runner";
    }
}
