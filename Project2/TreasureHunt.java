public class TreasureHunt {
    
    protected String TreasureType;

    public TreasureHunt(String Type){
        TreasureType = Type;

    }

    public int searchTreasure(){
        if(TreasureType == "Character"){
            return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
        }
        if(TreasureType == "Thieve"){
            return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
        }
        else{return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}

    }
}
