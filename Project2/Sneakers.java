public class Sneakers extends Characters { 
    // Example of inheritance


    /**
     * @param A: int
     * @param map: Dungeon
     * 
     * Construct Sneakers with ID `A` and the Dungeon
     */
    Sneakers(int A, Dungeon map) {
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new Fight("Stealth");
        this.HuntBehavior = new TreasureHunt("Quick");
        super.ID = A;
        name = "Sneaker";
    }
}
