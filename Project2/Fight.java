public class Fight {
        
    protected String FightType;

    public Fight(String Type){
        FightType = Type;
    }

    public int fight(){
        if(FightType == "Character"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}
        if(FightType == "Creature"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}
        if(FightType == "Thief"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}
        if(FightType == "Brawler"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+2;}
        if(FightType == "Sneaker"){
            if (DiceRolls.rollDice(2)== 1){
                return -1;
            }
            else{
            return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}}
        else{return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}
    }
}

