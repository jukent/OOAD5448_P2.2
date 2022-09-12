public class Thieves extends Characters{
    public int fight(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
    }
    public int searchTreasure(){
        return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
    }
}