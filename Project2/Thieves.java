public class Thieves extends Characters{
    Thieves(int A,Dungeon map){
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.HuntBehavior = new TreasureHunt("Thief");
        this.FightBehavior = new Fight("Theif");
        super.ID = A;
        name = "Thief";
    }
}