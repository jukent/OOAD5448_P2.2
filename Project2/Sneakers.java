public class Sneakers extends Characters{
    Sneakers(int A,Dungeon map){
        this.dungeon = map;
        this.Location = dungeon.getRoom("(0-1-1)");
        super.ID = A;
        name = "Sneaker";
    }
    public int fight(){
        if (DiceRolls.rollDice(2)== 1){
            return -1;
        }
        else{
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}
    }
}