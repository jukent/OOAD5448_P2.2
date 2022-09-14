public class Thieves extends Characters{
    Thieves(int A,Dungeon map){
        //this.dungeon = map;
        super.ID = A;
        name = "Thief";
    }
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
    }
    public int searchTreasure(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
    }
}