public class Brawlers extends Characters{
    Brawlers(int A,Dungeon map){
        super.ID = A;
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new Fight("Brawler");
        name = "Brawler";
    }
}
