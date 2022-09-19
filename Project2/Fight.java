public class Fight {
        
    protected String FightType;

    public Fight(String Type){
        FightType = Type;

    }

    public int fight(){
        if(FightType == "Character"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}
        if(FightType == "Thieve"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;}
        if(FightType == "Brawler"){return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+2;}
        else{return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}
    }
}

