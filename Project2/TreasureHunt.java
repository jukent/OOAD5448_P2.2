public class TreasureHunt {
    
    protected String TreasureType;

    public TreasureHunt(String Type){
        //This fight class represents an example of encapsulation. 
        //This class hides the treasure hunt behavior for each character.
        //Since this varies so much, we encapsulate it in its own
        //class. This is just like the Strategy design pattern. 
        //"Encapsulate what varies" 
        TreasureType = Type;

    }

    public int searchTreasure(){
        if(TreasureType == "Character"){
            return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);
        }
        if(TreasureType == "Thief"){
            return DiceRolls.rollDice(6)+DiceRolls.rollDice(6)+1;
        }
        else{return DiceRolls.rollDice(6)+DiceRolls.rollDice(6);}

    }
}
