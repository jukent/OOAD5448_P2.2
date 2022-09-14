public class Brawlers extends Characters{
    Brawlers(int A,Dungeon map){
        super.ID = A;
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        name = "Brawler";
    }
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+2;
    }
}
