public class Sneakers extends Characters{
    Sneakers(int A){
        super.ID = A;
    }
    public int fight(){
        if (DiceRolls.rollDice(2)== 1){
            return -1;
        }
        else{
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}
    }
}