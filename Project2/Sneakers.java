public class Sneakers extends Characters{
    public int fight(){
        if (DiceRolls.rollDice(2)== 1){
            return 0;
        }
        else{
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}
    }
}