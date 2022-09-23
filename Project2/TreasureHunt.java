public class TreasureHunt {
    
    protected String TreasureType;
    public int NeededScore;


    /**
     * @param Type: String
     * 
     * This method calls treasure hunts based on type of behavior.
     */
    public TreasureHunt(String Type) {
        // This class represents an example of encapsulation. 
        // This class hides the treasure hunt behavior for each character.
        // Since this varies so much, we encapsulate it in its own
        // class. This is just like the Strategy design pattern. 
        // "Encapsulate what varies" 
        TreasureType = Type;
        if (TreasureType == "Careful") {
            NeededScore = 7;}
        if (TreasureType == "Quick") {
            NeededScore = 9;}
        if (TreasureType == "Careless") {
            NeededScore = 10;}
    }


    /**
     * @return int
     * 
     * This method searches for treasure by rolling dice.
     */
    public int searchTreasure() {
        if (TreasureType == "Careful") {
            return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}
        if (TreasureType == "Quick") {
            if (DiceRolls.rollDice(3)==1){return -1;}
            else{return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}}
        if (TreasureType == "Careless") {
                return DiceRolls.rollDice(6) + DiceRolls.rollDice(6) + 1;}
        else { return DiceRolls.rollDice(6) + DiceRolls.rollDice(6);}
    }
}
