public class Thieves extends Characters {
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Construct Thieves with ID `A` and the Dungeon
     */
    Thieves(int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new Fight("Trained");
        this.HuntBehavior = new TreasureHunt("Careful");
        super.ID = A;
        name = "Thief";
    }
}
