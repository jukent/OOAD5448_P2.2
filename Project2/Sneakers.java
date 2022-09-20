public class Sneakers extends Characters{//Example of inheritance
    Sneakers(int A,Dungeon map){
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        this.FightBehavior = new Fight("Sneaker");
        super.ID = A;
        name = "Sneaker";
    }
}